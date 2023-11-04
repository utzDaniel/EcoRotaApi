package ecorota.api.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity(name = "TransporteLinha")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "transporte_linha")
public final class TransporteLinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "num", nullable = false)
    private int numero;

    @Column(name = "nom", nullable = false)
    private String nome;

    @Column(name = "psg", nullable = false)
    private double passagem;

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL)
    private List<TransporteHorarios> horarios;

}
