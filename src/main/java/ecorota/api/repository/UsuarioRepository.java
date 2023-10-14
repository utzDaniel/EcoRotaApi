package ecorota.api.repository;

import ecorota.api.repository.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);

    @Modifying
    @Query(value = """
            update
                usuario as u
            set
                u.nome = :nome,
                u.senha = :senha
            where
                u.email = :email
            """, nativeQuery = true)
    void update(@Param("email") String email, @Param("nome") String nome, @Param("senha") String senha);

}
