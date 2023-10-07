package ecorota.api.dto.request.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class UsuarioBaseRequest {

    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    protected String nome;

}
