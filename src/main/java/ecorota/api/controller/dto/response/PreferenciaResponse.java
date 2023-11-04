package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferenciaResponse {

    private boolean onibusAtivo;
    private boolean metroAtivo;
    private boolean bicicletaAtivo;
    private int opcaoTrajeto;
}
