package ecorota.api.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity(name = "UsuariosHistorico")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios_historico")
public final class UsuariosHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_usr")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_lcl_prt")
    private Local localPartida;

    @ManyToOne
    @JoinColumn(name = "id_lcl_dst")
    private Local localDestino;

    @OneToMany(mappedBy = "usuariosHistorico")
    private List<OpcoesDeslocamento> opcoesDeslocamentos;

    @Column(name = "dat_inc")
    private LocalDateTime dataInclusao;

    public UsuariosHistorico(Usuario usuario, Local localPartida, Local localDestino, List<OpcoesDeslocamento> opcoesDeslocamentos, LocalDateTime dataInclusao) {
        this.usuario = usuario;
        this.localPartida = localPartida;
        this.localDestino = localDestino;
        this.opcoesDeslocamentos = opcoesDeslocamentos;
        this.dataInclusao = dataInclusao;
    }
}
