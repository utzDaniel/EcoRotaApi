package ecorota.api.controller.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapaTrajetoRequest {

    @Min(value = 1, message = "O campo deve ser maior ou igual a 1!")
    @Max(value = 43, message = "O campo deve ser menor ou igual a 43!")
    private Long partida;

    @Min(value = 1, message = "O campo deve ser maior ou igual a 1!")
    @Max(value = 43, message = "O campo deve ser menor ou igual a 43!")
    private Long destino;
}
