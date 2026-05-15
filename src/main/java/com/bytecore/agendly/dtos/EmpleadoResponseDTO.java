package com.bytecore.agendly.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoResponseDTO { //es lo que el API devuelve al cliente

    private Long id;
    private String nombre;
    private Long numeroDocumento;
    private String email;
}