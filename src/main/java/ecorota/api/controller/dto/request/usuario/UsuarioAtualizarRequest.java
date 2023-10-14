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
    private String novaSenha;

    @Getter
    private String novaSenhaRepetida;

    @Getter
    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    private String senhaAtual;

}
