package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoRecursoResponse {

    private String nome;
    private Double soma;
    private String medida;

}
