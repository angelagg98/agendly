package com.bytecore.agendly.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CronogramaResponseDTO {

    private Long id;
    private String descripcion;
    private String hora;
    private String fecha;
    private String estado;
    private Long empleadoId;
    private String empleadoNombre;// para que el cliente sepa de quien es el cronograma
}