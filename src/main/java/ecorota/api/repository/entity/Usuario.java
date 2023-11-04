package ecorota.api.repository.entity;

import ecorota.api.enun.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "usuarios", indexes = {@Index(name = "idx_usuario_email", columnList = "eml")})
public final class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "nom", nullable = false)
    private String nome;

    @Column(name = "eml", unique = true, nullable = false)
    private String email;

    @Column(name = "sen", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Role role;

    @Column(name = "atv", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ativo;

    @Column(name = "cod_vrf", length = 6)
    private String codigoVerificador;

    @Embedded
    private Preferencia preferencia;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN)
            return List.of(new SimpleGrantedAuthority(Role.ADMINISTRADOR), new SimpleGrantedAuthority(Role.USUARIO));
        else return List.of(new SimpleGrantedAuthority(Role.USUARIO));
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}