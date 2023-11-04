package ecorota.api.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity(name = "TransporteHorarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "transporte_horarios")
public final class TransporteHorarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dat", nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "id_tpt_lin")
    private TransporteLinha linha;
}
