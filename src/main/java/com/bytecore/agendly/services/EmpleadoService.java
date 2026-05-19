package com.bytecore.agendly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytecore.agendly.dtos.CronogramaResponseDTO;
import com.bytecore.agendly.dtos.EmpleadoCronogramaDTO;
import com.bytecore.agendly.dtos.EmpleadoRequestDTO;
import com.bytecore.agendly.dtos.EmpleadoResponseDTO;
import com.bytecore.agendly.entitis.Empleado;
import com.bytecore.agendly.repositories.CronogramaRepository;
import com.bytecore.agendly.repositories.EmpleadoRepository;

import jakarta.transaction.Transactional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CronogramaRepository cronogramaRepository; 

    @Transactional
    public EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO requestDTO) {
        Empleado empleado = new Empleado();
        empleado.setNombre(requestDTO.getNombre());
        empleado.setNumeroDocumento(requestDTO.getNumeroDocumento());
        empleado.setEmail(requestDTO.getEmail());
        Empleado saved = empleadoRepository.save(empleado);
        return toDTO(saved); // En programación esto se llama el principio DRY — c. (no repetir)""
    }

    @Transactional
    public List<EmpleadoResponseDTO> findAll() {
        return empleadoRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public Optional<EmpleadoResponseDTO> findById(Long id) {
        Optional<Empleado> optional = empleadoRepository.findById(id);
        if (optional.isPresent()) {
            return Optional.of(toDTO(optional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<EmpleadoResponseDTO> update(Long id, EmpleadoRequestDTO dto) {
        Optional<Empleado> optional = empleadoRepository.findById(id);
        if (optional.isPresent()) {
            Empleado empleado = optional.get();
            empleado.setNombre(dto.getNombre());
            empleado.setNumeroDocumento(dto.getNumeroDocumento());
            empleado.setEmail(dto.getEmail());
            return Optional.of(toDTO(empleadoRepository.save(empleado)));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean delete(Long id) {
        if (empleadoRepository.existsById(id)) {
            empleadoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private EmpleadoResponseDTO toDTO(Empleado empleado) {
        return new EmpleadoResponseDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getNumeroDocumento(),
                empleado.getEmail());
    }

    @Transactional   // abre transaccion si falla roolback
    public EmpleadoCronogramaDTO finByIdConCronogramas(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("empleado no encontrado"));
        List<CronogramaResponseDTO> cronogramas = cronogramaRepository.findByEmpleadoId(id)  // list llama el metodo que agregamos en cronogramaRepository y devuelve una lista de entidades
                .stream() // convierte la entidad en un flujo para poder procesarla una por una 
                .map(c -> new CronogramaResponseDTO( // mac por cada cronograma (c) de la lista lo convierte a cronogramaResponseDTo 
                        c.getId(),
                        c.getDescripcion(),  
                        c.getHora(),
                        c.getFecha(),
                        c.getEstado(),
                        c.getEmpleado().getId(),  // accede al objeto empleado dengtro del cronograma para sacar el nombre 
                        c.getEmpleado().getNombre()))

                .toList(); // convierteel flujo devuelta a una lista 

        return new EmpleadoCronogramaDTO(   
                empleado.getId(),
                empleado.getNombre(),
                empleado.getEmail(),
                empleado.getNumeroDocumento(),               
                cronogramas);  // arma el dto final con los datos de empleado y la lista de cronogramas que acabamos de construir lo devuleve como respuesta 

    }

}