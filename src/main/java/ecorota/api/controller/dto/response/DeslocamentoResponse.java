package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeslocamentoResponse {

    private Long idTrajeto;
    private String nome;
    private int numero;
    private RecursoResponse recurso;
    List<PointResponse> points;

}
