package com.bytecore.agendly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bytecore.agendly.dtos.EmpleadoCronogramaDTO;
import com.bytecore.agendly.dtos.EmpleadoRequestDTO;
import com.bytecore.agendly.dtos.EmpleadoResponseDTO;
import com.bytecore.agendly.services.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    public ResponseEntity<EmpleadoResponseDTO> crear(@Valid @RequestBody EmpleadoRequestDTO requestDTO) { // el
                                                                                                          // @Requestbody
                                                                                                          // comvierte
                                                                                                          // el JSON a
                                                                                                          // objeto
        EmpleadoResponseDTO creado = empleadoService.crearEmpleado(requestDTO); // llama al service
        return ResponseEntity.status(HttpStatus.CREATED).body(creado); // comvierte el body o el objeto a un JSON
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>> findAll() {
        List<EmpleadoResponseDTO> empleados = empleadoService.findAll();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> findById(@PathVariable Long id) {
        EmpleadoResponseDTO empleado = empleadoService.findById(id).orElse(null);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponseDTO> update(@PathVariable Long id,
            @Valid @RequestBody EmpleadoRequestDTO requestDTO) { // @Valid antes de ejecutar el metodo cumpla las reglas
                                                                 // que define el RequestDTO
        // @PathVariable extrae el valor que viene en la url

        EmpleadoResponseDTO actualizado = empleadoService.update(id, requestDTO).orElse(null);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado); // ResponseEntity.ok atajo para devolver el 200 ok con un body
                                                   // equivalente a Actualizado
        } else {
            return ResponseEntity.notFound().build(); // buid: se usa cuando la respuesta no tiene body. solo el codigo
                                                      // HTTP
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (empleadoService.delete(id)) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/cronogramas")
    public ResponseEntity<EmpleadoCronogramaDTO> findByIdConCronogramas(@PathVariable Long id) {
        EmpleadoCronogramaDTO resultado = empleadoService.finByIdConCronogramas(id);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/nombre")
    public ResponseEntity<List<EmpleadoCronogramaDTO>> buscarPorNombre(
            @RequestParam String nombre) {
        List<EmpleadoCronogramaDTO> resultado = empleadoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/documento")
    public ResponseEntity<List<EmpleadoResponseDTO>> buscarPorDocumento(
            @RequestParam Long numeroDocumento) {

        // Llama al service pasando el número
        List<EmpleadoResponseDTO> resultado = empleadoService.buscarPorNumeroDocumento(numeroDocumento);
        return ResponseEntity.ok(resultado);
    }

}
