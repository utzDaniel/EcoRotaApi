package ecorota.api.service.factory;

import ecorota.api.controller.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.enun.OpcaoTrajeto;
import ecorota.api.enun.Role;
import ecorota.api.repository.entity.Preferencia;
import ecorota.api.repository.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioFactory {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario create(UsuarioCriarRequest req) {
        var preferencia = new Preferencia(true, true, false, OpcaoTrajeto.SUSTENTAVEL);
        return new Usuario(null, req.getNome(), req.getEmail(), criptografarSenha(req.getSenha()), Role.USER, true, null, preferencia);
    }

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean isSenha(String atualReq, String atualBase) {
        return passwordEncoder.matches(atualReq, atualBase);
    }
}
