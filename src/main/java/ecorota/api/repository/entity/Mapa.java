package ecorota.api.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity(name = "Mapa")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "mapa")
public final class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pos_x", nullable = false)
    private int posicaoX;

    @Column(name = "pos_y", nullable = false)
    private int posicaoY;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lcl", referencedColumnName = "id")
    private Local local;

    @OneToMany(mappedBy = "mapa", cascade = CascadeType.ALL)
    private List<Trajeto> trajetos;

}
