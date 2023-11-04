package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeslocamentoResponse {

    private double distancia;
    private double tempo;
    private double carbono;
    private TrajetoResponse trajeto;

}
