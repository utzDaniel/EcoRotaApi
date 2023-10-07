package ecorota.api.mapper;

import ecorota.api.dto.response.UsuarioResponse;
import ecorota.api.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public final class UsuarioMapper {

    public UsuarioResponse parse(Usuario usuario) {
        return new UsuarioResponse(usuario.getNome(), usuario.getLogin());
    }
}
