package ecorota.api.controller.dto.request.usuario;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsuarioPreferenciaAtualizarRequest {

    private boolean onibusAtivo;
    private boolean metroAtivo;
    private boolean bicicletaAtivo;

    @Min(value = 1, message = "O campo deve ser maior ou igual a 1!")
    @Max(value = 3, message = "O campo deve ser menor ou igual a 3!")
    private int opcaoTrajeto;
}
