package com.bytecore.agendly.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CronogramaRequestDTO {

    @NotBlank(message = "La descripcion no puede estar vacía")
    private String descripcion;

    @NotBlank(message = "La hora no puede estar vacía")
    private String hora;

    @NotBlank(message = "La fecha no puede estar vacía")
    private String fecha;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotNull(message = "El id del empleado no puede estar vacío")
    private Long empleadoId;
}