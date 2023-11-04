package ecorota.api.repository.entity;

import ecorota.api.enun.Transporte;
import ecorota.api.service.util.TransporteConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Trajeto")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "trajetos")
public final class Trajeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Convert(converter = TransporteConverter.class)
    @Column(name = "id_tpt", nullable = false)
    private Transporte transporte;

    @Column(name = "pto", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ponto;

    @ManyToOne
    @JoinColumn(name = "id_map")
    private Mapa mapa;

    @ManyToOne
    @JoinColumn(name = "id_tpt_lin")
    private TransporteLinha transporteLinha;

}
