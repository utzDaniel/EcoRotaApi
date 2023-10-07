package ecorota.api.factory;

import ecorota.api.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.entity.Usuario;
import ecorota.api.enun.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario create(UsuarioCriarRequest req) {
        var senhaCriptografada = passwordEncoder.encode(req.getSenha());
        return new Usuario(null, req.getNome(), req.getLogin(), senhaCriptografada, Role.USER);
    }
}
