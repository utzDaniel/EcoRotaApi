package ecorota.api.service.mapper;

import ecorota.api.controller.dto.response.*;
import ecorota.api.repository.entity.*;
import org.springframework.stereotype.Component;

@Component
public final class HistoricoMapper {

    public HistoricoResponse parse(UsuariosHistorico hist) {
        var partida = parse(hist.getLocalPartida());
        var destino = parse(hist.getLocalDestino());
        var opcoes = hist.getOpcoesDeslocamentos().stream().map(this::parse).toList();
        return new HistoricoResponse(partida, destino, opcoes, hist.getDataInclusao());
    }

    private OpcoesDeslocamentoResponse parse(OpcoesDeslocamento opc) {
        var deslocamento = opc.getDeslocamento().stream().map(this::parse).toList();
        return new OpcoesDeslocamentoResponse(deslocamento, opc.isEscolhida());
    }

    public LocalResponse parse(Local local) {
        return new LocalResponse((int) local.getId(), local.getNome());
    }

    public DeslocamentoResponse parse(Deslocamento dlc) {
        return new DeslocamentoResponse(dlc.getDistancia(), dlc.getTempo(), dlc.getEmissaoCarbono(), parse(dlc.getTrajeto()));
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
