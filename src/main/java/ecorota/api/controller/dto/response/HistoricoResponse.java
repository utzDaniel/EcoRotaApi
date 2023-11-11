package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoResponse {

    private HistoricoRecursoResponse distancia;
    private HistoricoRecursoResponse tempo;
    private HistoricoRecursoResponse carbono;
    private HistoricoRecursoResponse dinheiro;
    private List<HistoricoItemResponse> item;

}
