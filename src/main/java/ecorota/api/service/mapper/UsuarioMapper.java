package ecorota.api.service.mapper;

import ecorota.api.controller.dto.response.PreferenciaResponse;
import ecorota.api.controller.dto.response.UsuarioResponse;
import ecorota.api.repository.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public final class UsuarioMapper {

    public UsuarioResponse parse(Usuario usuario) {
        var pref = usuario.getPreferencia();
        var rps = new PreferenciaResponse(pref.isOnibusAtivo(),pref.isMetroAtivo(), pref.isBicicletaAtivo(), pref.getOpcaoTrajeto().getId());
        return new UsuarioResponse(usuario.getNome(), usuario.getEmail(), rps);
    }
}
