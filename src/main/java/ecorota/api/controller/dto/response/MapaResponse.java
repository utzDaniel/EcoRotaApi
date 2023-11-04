package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapaResponse {

    private int posicaoX;
    private int posicaoY;
    private LocalResponse local;
    private List<TrajetoResponse> trajetos;

}
