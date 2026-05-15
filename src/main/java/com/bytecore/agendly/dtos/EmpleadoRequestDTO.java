package com.bytecore.agendly.dtos;

import jakarta.validation.constraints.*;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoRequestDTO { // es lo que el cliente manda al API
    
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotNull(message = "El número de documento no puede estar vacío")
    private Long numeroDocumento;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;
    
}
