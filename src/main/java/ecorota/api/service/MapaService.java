package ecorota.api.service;

import ecorota.api.controller.dto.response.MapaResponse;
import ecorota.api.repository.MapaRepository;
import ecorota.api.service.mapper.MapaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapaService {

    @Autowired
    private MapaRepository mapaRepository;

    @Autowired
    private MapaMapper mapper;


    public List<MapaResponse> buscar() {
        return this.mapaRepository.findAll()
                .stream()
                .map(v -> mapper.parse(v))
                .toList();
    }


}
