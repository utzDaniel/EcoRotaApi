package ecorota.api.controller.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeslocamentoRequest {

    @Min(value = 1, message = "O campo deve ser maior ou igual a 1!")
    private Long idTrajeto;

    @Valid
    private RecursoRequest recurso;
}
