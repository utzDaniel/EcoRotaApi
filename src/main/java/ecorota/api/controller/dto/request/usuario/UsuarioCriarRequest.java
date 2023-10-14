package ecorota.api.controller.dto.request.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCriarRequest extends UsuarioBaseRequest {

    @Getter
    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    private String email;

    @Getter
    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    private String senha;

    public UsuarioCriarRequest(String nome, String email, String senha) {
        super.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}
