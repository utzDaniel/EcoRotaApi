package ecorota.api.dto.request.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCriarRequest extends UsuarioBaseRequest {

    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    private String login;

    @NotNull(message = "Não deve ser nulo")
    @NotBlank(message = "Não deve estar em branco")
    private String senha;

    public UsuarioCriarRequest(String nome, String login, String senha) {
        super.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
