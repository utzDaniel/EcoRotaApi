package ecorota.api.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "Deslocamento")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "deslocamento")
public final class Deslocamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dtc")
    private double distancia;

    @Column(name = "tmp")
    private double tempo;

    @Column(name = "ems_crb")
    private double emissaoCarbono;

    @ManyToOne
    @JoinColumn(name = "id_opc_dlc")
    private OpcoesDeslocamento opcoesDeslocamento;

    @ManyToOne
    @JoinColumn(name = "id_tjt")
    private Trajeto trajeto;

}
