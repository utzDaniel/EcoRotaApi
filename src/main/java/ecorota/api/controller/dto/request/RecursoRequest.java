package ecorota.api.controller.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoRequest {

    @Min(value = 0, message = "O campo deve ser maior ou igual a 0!")
    private int distancia;

    @Min(value = 0, message = "O campo deve ser maior ou igual a 0!")
    private int tempo;

    @Min(value = 0, message = "O campo deve ser maior ou igual a 0!")
    private int carbono;

}
