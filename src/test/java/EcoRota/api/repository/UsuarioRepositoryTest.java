package EcoRota.api.repository;

import ecorota.api.entity.Usuario;
import ecorota.api.enun.Role;
import ecorota.api.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Buscar o usuario a partir do login")
    void buscarLogin() {
        var a15 = "a15";
        var usuario = criarUsuario(a15, a15, a15, Role.USER);
        var usuarioBase = usuarioRepository.buscarLogin(usuario.getLogin());
        assertThat(usuarioBase).isEqualTo(usuario);
    }


    private Usuario criarUsuario(String nome, String login, String senha, Role role) {
        var usuario = new Usuario(null, nome, login, senha, role);
        em.persist(usuario);
        return usuario;
    }
}