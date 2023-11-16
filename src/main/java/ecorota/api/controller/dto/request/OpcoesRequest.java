package ecorota.api.controller.dto.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpcoesRequest {

    @Valid
    private List<DeslocamentoRequest> deslocamento;

    private boolean selecionado;
}
