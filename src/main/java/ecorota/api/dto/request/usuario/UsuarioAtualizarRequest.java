package ecorota.api.dto.request.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAtualizarRequest extends UsuarioBaseRequest {

    @NotNull(message = "Não deve ser nulo")
    private Long id;
}
