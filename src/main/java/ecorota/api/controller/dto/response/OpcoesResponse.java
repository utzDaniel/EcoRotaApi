package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpcoesResponse {

    private List<DeslocamentoResponse> deslocamento;
    private RecursoResponse recurso;
    private boolean selecionado;
}
