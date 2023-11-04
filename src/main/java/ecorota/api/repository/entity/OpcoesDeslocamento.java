package ecorota.api.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity(name = "OpcoesDeslocamento")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "opcoes_deslocamento")
public final class OpcoesDeslocamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usr_htc")
    private UsuariosHistorico usuariosHistorico;

    @OneToMany(mappedBy = "opcoesDeslocamento")
    private List<Deslocamento> deslocamento;

    @Column(name = "scl")
    private boolean escolhida;

}
