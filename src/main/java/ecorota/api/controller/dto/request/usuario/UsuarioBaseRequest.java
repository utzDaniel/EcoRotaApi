package ecorota.api.controller.dto.request.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class UsuarioBaseRequest {

    @Getter
    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    protected String nome;

}
