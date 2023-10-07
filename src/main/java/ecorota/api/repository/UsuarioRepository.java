package ecorota.api.repository;

import ecorota.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    @Query("""
            select
                u
            from
                Usuario as u
            where
                u.login = :login
            """)
    UserDetails buscarLogin(String login);
}
