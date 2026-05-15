package dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class usuarioNombre {

     private Long id;

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "El correo debe tener formato válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

}
