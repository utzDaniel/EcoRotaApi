package ecorota.api.service.mapper;

import ecorota.api.controller.dto.response.UsuarioResponse;
import ecorota.api.repository.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public final class UsuarioMapper {

    public UsuarioResponse parse(Usuario usuario) {
        return new UsuarioResponse(usuario.getNome(), usuario.getEmail());
    }
}
