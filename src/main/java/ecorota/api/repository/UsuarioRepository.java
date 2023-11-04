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
                usuarios as u
            set
                u.nom = :nome,
                u.sen = :senha
            where
                u.eml = :email
            """, nativeQuery = true)
    void update(@Param("email") String email, @Param("nome") String nome, @Param("senha") String senha);

    @Modifying
    @Query(value = """
            update
                usuarios as u
            set
                u.onb_atv = :onibus,
                u.mtr_atv = :metro,
                u.bcc_atv = :bicicleta,
                u.id_opc_tjt = :opcao
            where
                u.eml = :email
            """, nativeQuery = true)
    void updatePreferencia(@Param("email") String email,
                           @Param("onibus") boolean onibusAtivo,
                           @Param("metro") boolean metroAtivo,
                           @Param("bicicleta") boolean bicicletaAtivo,
                           @Param("opcao") int opcaoTrajeto);
}
