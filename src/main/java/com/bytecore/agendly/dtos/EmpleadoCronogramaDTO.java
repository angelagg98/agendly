package com.bytecore.agendly.dtos;

import java.util.List;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoCronogramaDTO {

private Long id; 
private String nombre;
private String email;
private Long numeroDocumento;
private List<CronogramaResponseDTO> cronogramas; // llama la lista de cronogramapor empleado

}
