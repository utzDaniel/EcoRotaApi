package ecorota.api.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coordenada {

    private int id;
    private int x;
    private int y;
    private int ordem;
}
