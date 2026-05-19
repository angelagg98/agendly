package com.bytecore.agendly.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytecore.agendly.dtos.CronogramaRequestDTO;
import com.bytecore.agendly.dtos.CronogramaResponseDTO;
import com.bytecore.agendly.services.CronogramaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cronogramas")

public class CronogramaController {

    @Autowired
    private CronogramaService cronogramaService;

    @PostMapping
    public ResponseEntity<CronogramaResponseDTO> crear(@Valid @RequestBody CronogramaRequestDTO requestDTO) {
        CronogramaResponseDTO creado = cronogramaService.crear(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<CronogramaResponseDTO>> findAll() {
        List<CronogramaResponseDTO> cronogramas = cronogramaService.findAll();
        return ResponseEntity.ok(cronogramas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CronogramaResponseDTO> findById(@PathVariable Long id) {
        CronogramaResponseDTO cronograma = cronogramaService.findById(id).orElse(null);
        if (cronograma != null) {
            return ResponseEntity.ok(cronograma);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CronogramaResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CronogramaRequestDTO requestDTO) {
        CronogramaResponseDTO actualizado = cronogramaService.update(id, requestDTO).orElse(null);
        if (actualizado != null) {
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (cronogramaService.delete(id)) {
            return ResponseEntity.noContent().build();
        }return ResponseEntity.noContent().build();
    }

 

}
