package com.bytecore.agendly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bytecore.agendly.dtos.CronogramaRequestDTO;
import com.bytecore.agendly.dtos.CronogramaResponseDTO;
import com.bytecore.agendly.entitis.Cronograma;
import com.bytecore.agendly.entitis.Empleado;
import com.bytecore.agendly.repositories.CronogramaRepository;
import com.bytecore.agendly.repositories.EmpleadoRepository;

import jakarta.transaction.Transactional;

@Service
public class CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Transactional
    public CronogramaResponseDTO crear(CronogramaRequestDTO dto) {
        Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        Cronograma cronograma = new Cronograma();
        cronograma.setDescripcion(dto.getDescripcion());
        cronograma.setHora(dto.getHora());
        cronograma.setFecha(dto.getFecha());
        cronograma.setEstado(dto.getEstado());
        cronograma.setEmpleado(empleado);
        Cronograma saved = cronogramaRepository.save(cronograma);
        return toDTO(saved);
    }

    @Transactional
    public List<CronogramaResponseDTO> findAll() {
        return cronogramaRepository.findAll().stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional
    public Optional<CronogramaResponseDTO> findById(Long id) {
        Optional<Cronograma> optional = cronogramaRepository.findById(id);
        if (optional.isPresent()) {
            return Optional.of(toDTO(optional.get()));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public Optional<CronogramaResponseDTO> update(Long id, CronogramaRequestDTO dto) {
        Optional<Cronograma> optional = cronogramaRepository.findById(id);
        if (optional.isPresent()) {
            Empleado empleado = empleadoRepository.findById(dto.getEmpleadoId())
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
            Cronograma cronograma = optional.get();
            cronograma.setDescripcion(dto.getDescripcion());
            cronograma.setHora(dto.getHora());
            cronograma.setFecha(dto.getFecha());
            cronograma.setEstado(dto.getEstado());
            cronograma.setEmpleado(empleado);
            return Optional.of(toDTO(cronogramaRepository.save(cronograma)));
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    public boolean delete(Long id) {
        if (cronogramaRepository.existsById(id)) {
            cronogramaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private CronogramaResponseDTO toDTO(Cronograma cronograma) {
        return new CronogramaResponseDTO(
                cronograma.getId(),
                cronograma.getDescripcion(),
                cronograma.getHora(),
                cronograma.getFecha(),
                cronograma.getEstado(),
                cronograma.getEmpleado().getId(),
                cronograma.getEmpleado().getNombre());
    }
}