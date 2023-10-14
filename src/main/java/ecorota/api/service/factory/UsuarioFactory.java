package ecorota.api.service.factory;

import ecorota.api.controller.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.enun.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario create(UsuarioCriarRequest req) {
        return new Usuario(null, req.getNome(), req.getEmail(), this.senha(req.getSenha()), Role.USER, true, null);
    }

    public String senha(String senha) {
        return this.passwordEncoder.encode(senha);
    }
}
