package ecorota.api.repository.entity;

import ecorota.api.enun.OpcaoTrajeto;
import ecorota.api.service.util.OpcaoTrajetoConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class Preferencia implements Serializable {

    @Serial
    private static final long serialVersionUID = 2405172041950251807L;

    @Setter
    @Column(name = "onb_atv", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean onibusAtivo;

    @Setter
    @Column(name = "mtr_atv", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean metroAtivo;

    @Setter
    @Column(name = "bcc_atv", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean bicicletaAtivo;

    @Setter
    @Convert(converter = OpcaoTrajetoConverter.class)
    @Column(name = "id_opc_tjt", nullable = false)
    private OpcaoTrajeto opcaoTrajeto;

}
