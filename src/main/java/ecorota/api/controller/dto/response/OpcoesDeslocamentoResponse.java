package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpcoesDeslocamentoResponse {

    private List<DeslocamentoResponse> deslocamento;
    private boolean escolhida;

}
