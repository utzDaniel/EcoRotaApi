package ecorota.api.service;

import ecorota.api.controller.dto.request.MapaTrajetoRequest;
import ecorota.api.controller.dto.response.MapaResponse;
import ecorota.api.controller.dto.response.MapaTrajetoResponse;
import ecorota.api.repository.MapaRepository;
import ecorota.api.repository.TrajetoRepository;
import ecorota.api.repository.entity.Mapa;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.service.domain.Coordenada;
import ecorota.api.service.domain.TrajetoMapa;
import ecorota.api.service.mapper.MapaMapper;
import ecorota.api.service.mapper.MapaTrajetoMapper;
import ecorota.api.service.util.Backtracking;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private TrajetoRepository trajetoRepository;

    @Autowired
    private MapaMapper mapper;

    @Autowired
    private MapaTrajetoMapper mapaTrajetoMapper;

    private int[][] matriz;

    @PostConstruct
    public void init() {
        var map = mapaRepository.findAll();
        var linha = map.stream()
                .mapToInt(Mapa::getPosicaoX)
                .max()
                .orElse(0);
        var coluna = map.stream()
                .mapToInt(Mapa::getPosicaoY)
                .max()
                .orElse(0);
        matriz = new int[linha + 1][coluna + 1];
        map.forEach(v -> matriz[v.getPosicaoX()][v.getPosicaoY()] = (int) v.getId());
    }


    public List<MapaResponse> buscar() {
        return this.mapaRepository.findAll()
                .stream()
                .map(v -> mapper.parse(v))
                .toList();
    }

    public MapaTrajetoResponse buscarTrajeto(MapaTrajetoRequest request, Usuario usuario) {
        var partida = mapaRepository.buscarMapaPorLocal(request.getPartida());
        var destino = mapaRepository.buscarMapaPorLocal(request.getDestino());
        var posInicial = new int[]{partida.getPosicaoX(), partida.getPosicaoY()};
        var posFinal = new int[]{destino.getPosicaoX(), destino.getPosicaoY()};
        var backtracking = new Backtracking(matriz, posInicial, posFinal);
        var coordenadas = backtracking.buscar();
        List<Integer> listIdMapa = coordenadas.stream().map(Coordenada::getId).toList();
        List<TrajetoMapa> listTrajetoMapa = null;
        if(usuario.getPreferencia().isOnibusAtivo()){
            listTrajetoMapa = trajetoRepository.buscarTrajetoPorMapa(listIdMapa);
        }
        return mapaTrajetoMapper.parse(listTrajetoMapa, coordenadas, request, usuario.getPreferencia().isBicicletaAtivo());
    }

}
