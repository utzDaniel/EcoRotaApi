package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoResponse {

    private int distancia;
    private int tempo;
    private int carbono;
    private double dinheiro;

}
