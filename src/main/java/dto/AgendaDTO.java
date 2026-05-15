package dto;

import com.agenda.app.model.EstadoAgenda;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDTO {

    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El horario es obligatorio")
    private LocalDateTime horario;

    private EstadoAgenda estado;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    // Nombre del usuario para mostrar en respuestas
    private String usuarioNombre;
}

