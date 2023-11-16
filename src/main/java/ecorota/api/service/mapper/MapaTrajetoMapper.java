package ecorota.api.service.mapper;

import ecorota.api.controller.dto.request.MapaTrajetoRequest;
import ecorota.api.controller.dto.response.*;
import ecorota.api.enun.Transporte;
import ecorota.api.service.domain.Coordenada;
import ecorota.api.service.domain.TrajetoMapa;
import ecorota.api.service.util.CalcularTrajeto;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MapaTrajetoMapper {

    public MapaTrajetoResponse parse(List<TrajetoMapa> listTrajetoMapa, List<Coordenada> coordenadas, MapaTrajetoRequest request, boolean isBicicletaAtivo) {
        var transporte = isBicicletaAtivo ? Transporte.BICICLETA : Transporte.CAMINHADA;
        coordenadas.sort(Comparator.comparingInt(Coordenada::getOrdem));
        List<OpcoesResponse> opcoes = parse(listTrajetoMapa, coordenadas, transporte);
        return new MapaTrajetoResponse(request.getPartida(), request.getDestino(), opcoes);
    }

    public List<OpcoesResponse> parse(List<TrajetoMapa> listTrajetoMapa, List<Coordenada> coordenadas, Transporte transporte) {
        var opcoes = new ArrayList<OpcoesResponse>();
        opcoes.add(opcao1(coordenadas, transporte));
        if (Objects.isNull(listTrajetoMapa) || listTrajetoMapa.isEmpty()) return opcoes;

        Map<Integer, Double> linhas = listTrajetoMapa.stream()
                .filter(TrajetoMapa::getPonto)
                .collect(Collectors.toMap(TrajetoMapa::getNumLinha, TrajetoMapa::getPassagem,
                        (valorExistente, novoValor) -> novoValor));

        if (linhas.isEmpty()) return opcoes;

        if (linhas.size() == 1) {
            opcoes.add(opcao2(listTrajetoMapa, coordenadas, transporte));
        } else {
            var minPass = linhas.values().stream().min(Double::compareTo).orElse(0.0d);
            var linha1 = listTrajetoMapa.stream()
                    .filter(v -> v.getPassagem() == minPass)
                    .map(TrajetoMapa::getNumLinha)
                    .findFirst().get();
            var list1 = listTrajetoMapa.stream().filter(v -> v.getNumLinha() == linha1).toList();
            opcoes.add(opcao2(list1, coordenadas, transporte));

            var maxPass = linhas.values().stream().max(Double::compareTo).orElse(minPass);
            var linha2 = listTrajetoMapa.stream()
                    .filter(v -> v.getPassagem() == maxPass && v.getNumLinha() != linha1)
                    .map(TrajetoMapa::getNumLinha)
                    .findFirst().get();
            var list2 = listTrajetoMapa.stream().filter(v -> v.getNumLinha() == linha2).toList();
            opcoes.add(opcao2(list2, coordenadas, transporte));
        }
        return opcoes;
    }

    public OpcoesResponse opcao1(List<Coordenada> coordenadas, Transporte transporte) {
        List<DeslocamentoResponse> deslocamento = deslocamentoOpcao1(coordenadas, transporte);
        RecursoResponse recurso = recursoOpcao1(coordenadas, transporte);
        return new OpcoesResponse(deslocamento, recurso, false);
    }

    public List<DeslocamentoResponse> deslocamentoOpcao1(List<Coordenada> coordenadas, Transporte transporte) {
        List<DeslocamentoResponse> deslocamento = new ArrayList<>();
        var desl = new DeslocamentoResponse(transporte.getIdTrajeto(), transporte.getNome(), -1, recursoOpcao1(coordenadas, transporte), points(coordenadas));
        deslocamento.add(desl);
        return deslocamento;
    }

    public RecursoResponse recursoOpcao1(List<Coordenada> coordenadas, Transporte transporte) {
        int distancia = CalcularTrajeto.distanciaEmMetros(coordenadas.size());
        int tempo = CalcularTrajeto.tempoEmMinutos(transporte, distancia);
        int carbono = CalcularTrajeto.emissaoCO2(transporte, tempo);
        double dinheiro = 0.0d;
        return new RecursoResponse(distancia, tempo, carbono, dinheiro);
    }

    public OpcoesResponse opcao2(List<TrajetoMapa> listTrajetoMapa, List<Coordenada> coordenadas, Transporte transporte) {
        List<DeslocamentoResponse> deslocamento = deslocamentoOpcao2(listTrajetoMapa, coordenadas, transporte);
        RecursoResponse recurso = recursoOpcao2(deslocamento, listTrajetoMapa.get(0).getPassagem());
        return new OpcoesResponse(deslocamento, recurso, false);
    }

    public List<DeslocamentoResponse> deslocamentoOpcao2(List<TrajetoMapa> listTrajetoMapa, List<Coordenada> coordenadas, Transporte transporte) {
        List<DeslocamentoResponse> deslocamento = new ArrayList<>();
        List<PointResponse> inicioPoints = new ArrayList<>();
        List<PointResponse> meioPoints = new ArrayList<>();
        List<PointResponse> fimPoints = new ArrayList<>();

        for (Coordenada v : coordenadas) {
            if (meioPoints.isEmpty()) {
                var ponto = listTrajetoMapa.stream().filter(e -> v.getId() == e.getIdMapa() && e.getPonto())
                        .findFirst().orElse(null);
                if (Objects.isNull(ponto)) {
                    inicioPoints.add(new PointResponse(v.getX(), v.getY()));
                } else {
                    meioPoints.add(new PointResponse(v.getX(), v.getY()));
                }
            } else {
                var ponto = listTrajetoMapa.stream().filter(e -> v.getId() == e.getIdMapa())
                        .findFirst().orElse(null);
                if (Objects.isNull(ponto)) {
                    fimPoints.add(new PointResponse(v.getX(), v.getY()));
                } else {
                    meioPoints.add(new PointResponse(v.getX(), v.getY()));
                }
            }
        }

        var desl1 = new DeslocamentoResponse(transporte.getIdTrajeto(), transporte.getNome(), -1, null, inicioPoints);
        deslocamento.add(desl1);

        var desl2 = new DeslocamentoResponse(1L, Transporte.ONIBUS.getNome(), listTrajetoMapa.get(0).getNumLinha(), null, meioPoints);
        deslocamento.add(desl2);

        if (!fimPoints.isEmpty()) {
            var desl3 = new DeslocamentoResponse(transporte.getIdTrajeto(), transporte.getNome(), -1, null, fimPoints);
            deslocamento.add(desl3);
        }

        return deslocamento;
    }

    public RecursoResponse recursoOpcao2(List<DeslocamentoResponse> deslList, double passagem) {
        var list = new ArrayList<RecursoResponse>();
        deslList.forEach(v -> {
            int distancia = CalcularTrajeto.distanciaEmMetros(v.getPoints().size());
            var transporte = Transporte.getInstance(v.getNome());
            int tempo = CalcularTrajeto.tempoEmMinutos(transporte, distancia);
            int carbono = CalcularTrajeto.emissaoCO2(transporte, tempo);
            double dinheiro = transporte.getId() == 1L ? passagem : 0.0d;
            var rec = new RecursoResponse(distancia, tempo, carbono, dinheiro);
            v.setRecurso(rec);
            list.add(rec);
        });
        int distanciaTotal = list.stream().mapToInt(RecursoResponse::getDistancia).sum();
        int tempoTotal = list.stream().mapToInt(RecursoResponse::getTempo).sum();
        int carbonoTotal = list.stream().mapToInt(RecursoResponse::getCarbono).sum();
        double dinheiroTotal = list.stream().mapToDouble(RecursoResponse::getDinheiro).sum();
        return new RecursoResponse(distanciaTotal, tempoTotal, carbonoTotal, dinheiroTotal);
    }

    public List<PointResponse> points(List<Coordenada> coordenadas) {
        return coordenadas.stream().map(v -> new PointResponse(v.getX(), v.getY())).toList();
    }

}
