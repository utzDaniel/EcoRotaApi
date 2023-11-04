package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrajetoResponse {

    private String nomeTransporte;
    private boolean ponto;
    private int numero;
    private String nomeLinha;
    private double passagem;
    private List<TransporteHorariosResponse> horarios;

}
