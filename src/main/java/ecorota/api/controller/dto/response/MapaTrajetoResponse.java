package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapaTrajetoResponse {

    private Long partida;
    private Long destino;
    private List<OpcoesResponse> opcoes;

}
