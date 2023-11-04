package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoResponse {

    private LocalResponse partida;
    private LocalResponse destino;
    private List<OpcoesDeslocamentoResponse> opcoes;
    private LocalDateTime data;

}
