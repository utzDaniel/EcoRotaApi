package ecorota.api.controller.dto.request.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class UsuarioAtualizarRequest extends UsuarioBaseRequest {

    @Getter
    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    private String senha;

}
