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
    private static PasswordEncoder passwordEncoder;

    public static Usuario create(UsuarioCriarRequest req) {
        return new Usuario(null, req.getNome(), req.getEmail(), criptografarSenha(req.getSenha()), Role.USER, true, null);
    }

    public static String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public static boolean isSenha(String atualReq, String atualBase) {
        return passwordEncoder.matches(atualReq, atualBase);
    }
}
