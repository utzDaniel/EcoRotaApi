package ecorota.api.service.mapper;

import ecorota.api.controller.dto.response.LocalResponse;
import ecorota.api.controller.dto.response.MapaResponse;
import ecorota.api.controller.dto.response.TrajetoResponse;
import ecorota.api.controller.dto.response.TransporteHorariosResponse;
import ecorota.api.repository.entity.Local;
import ecorota.api.repository.entity.Mapa;
import ecorota.api.repository.entity.Trajeto;
import ecorota.api.repository.entity.TransporteHorarios;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class MapaMapper {

    public MapaResponse parse(Mapa mapa) {
        var rspLocal = parse(mapa.getLocal());
        List<TrajetoResponse> rspTrajeto = mapa.getTrajetos().stream().map(this::parse).toList();
        return new MapaResponse(mapa.getPosicaoX(), mapa.getPosicaoY(), rspLocal, rspTrajeto);
    }

    public LocalResponse parse(Local local) {
        return new LocalResponse((int) local.getId(), local.getNome());
    }

    public TrajetoResponse parse(Trajeto trajeto) {
        var linha = trajeto.getTransporteLinha();
        var rspHorarios = linha.getHorarios().stream().map(this::parse).toList();
        return new TrajetoResponse(trajeto.getTransporte().getNome(), trajeto.isPonto(), linha.getNumero(), linha.getNome(), linha.getPassagem(), rspHorarios);
    }

    public TransporteHorariosResponse parse(TransporteHorarios horarios) {
        return new TransporteHorariosResponse((int) horarios.getId(), horarios.getData());
    }

}
