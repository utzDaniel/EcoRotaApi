package ecorota.api.service.mapper;

import ecorota.api.controller.dto.response.*;
import ecorota.api.repository.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public final class HistoricoMapper {

    public HistoricoResponse parse(List<UsuariosHistorico> list) {
        List<HistoricoItemResponse> itens = list.stream().map(this::parse).toList();
        HistoricoRecursoResponse distancia = getRecursoDistancia(itens);
        HistoricoRecursoResponse tempo = getRecursoTempo(itens);
        HistoricoRecursoResponse carbono = getRecursoCarbono(itens);
        HistoricoRecursoResponse dinheiro = getRecursoDinheiro(itens);
        return new HistoricoResponse(distancia, tempo, carbono, dinheiro, itens);
    }
    private HistoricoItemResponse parse(UsuariosHistorico hist) {
        var data = hist.getDataInclusao();
        var partida = hist.getLocalPartida().getNome();
        var destino = hist.getLocalDestino().getNome();
        var deslocamento = getDeslocamentoEscolhido(hist.getOpcoesDeslocamentos());
        var distancia = getDistancia(deslocamento);
        var tempo = getTempo(deslocamento);
        var carbono = getCarbono(deslocamento);
        var dinheiro = getDinheiro(deslocamento);
        return new HistoricoItemResponse(data, partida, destino, distancia, tempo, carbono, dinheiro);
    }

    private List<Deslocamento> getDeslocamentoEscolhido(List<OpcoesDeslocamento> opcoes) {
        return opcoes.stream()
                .filter(OpcoesDeslocamento::isEscolhida)
                .findFirst().get()
                .getDeslocamento();
    }

    private Double getDistancia(List<Deslocamento> desl) {
        return desl.stream()
                .mapToDouble(Deslocamento::getDistancia)
                .sum();
    }

    private Double getTempo(List<Deslocamento> desl) {
        return desl.stream()
                .mapToDouble(Deslocamento::getTempo)
                .sum();
    }

    private Double getCarbono(List<Deslocamento> desl) {
        return desl.stream()
                .mapToDouble(Deslocamento::getEmissaoCarbono)
                .sum();
    }

    private Double getDinheiro(List<Deslocamento> desl) {
        return desl.stream()
                .mapToDouble(this::getPassagem)
                .sum();
    }

    private Double getPassagem(Deslocamento desl) {
        var linha = desl.getTrajeto().getTransporteLinha();
        return Objects.isNull(linha) ? 0d : linha.getPassagem();
    }

    private HistoricoRecursoResponse getRecursoDinheiro(List<HistoricoItemResponse> item) {
        var soma = item.stream()
                .mapToDouble(HistoricoItemResponse::getDinheiro)
                .sum();
        return new HistoricoRecursoResponse("Dinheiro gasto", soma, "BRL");
    }

    private HistoricoRecursoResponse getRecursoCarbono(List<HistoricoItemResponse> item) {
        var soma = item.stream()
                .mapToDouble(HistoricoItemResponse::getCarbono)
                .sum();
        return new HistoricoRecursoResponse("Emissão de Carbono", soma, "kg");
    }
    private HistoricoRecursoResponse getRecursoDistancia(List<HistoricoItemResponse> item) {
        var soma = item.stream()
                .mapToDouble(HistoricoItemResponse::getDistancia)
                .sum();
        return new HistoricoRecursoResponse("Distancia percorrida", soma, "m");
    }
    private HistoricoRecursoResponse getRecursoTempo(List<HistoricoItemResponse> item) {
        var soma = item.stream()
                .mapToDouble(HistoricoItemResponse::getTempo)
                .sum();
        return new HistoricoRecursoResponse("Tempo gasto", soma, "min");
    }

}
