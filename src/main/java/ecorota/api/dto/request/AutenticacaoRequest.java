package ecorota.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AutenticacaoRequest {

    @NotBlank
    @NotNull
    private String login;
    @NotBlank
    @NotNull
    private String senha;

}
