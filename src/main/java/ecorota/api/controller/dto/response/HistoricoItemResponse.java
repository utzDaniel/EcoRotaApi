package ecorota.api.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoItemResponse {

    private LocalDateTime data;
    private String partida;
    private String destino;
    private Double distancia;
    private Double tempo;
    private Double carbono;
    private Double dinheiro;

}
