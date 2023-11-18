package ecorota.api.service;

import ecorota.api.controller.dto.response.DominioResponse;
import ecorota.api.controller.dto.response.MapaLocalResponse;
import ecorota.api.controller.dto.response.PointResponse;
import ecorota.api.enun.OpcaoTrajeto;
import ecorota.api.enun.Transporte;
import ecorota.api.repository.LocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DominioService {

    @Autowired
    private LocaRepository localRepository;


    public List<DominioResponse> listarOpcaoTrajeto() {
        return Arrays.stream(OpcaoTrajeto.values())
                .map(op ->
                        new DominioResponse(op.getId(), op.getNome())
                ).toList();
    }

    public List<DominioResponse> listarTransporte() {
        return Arrays.stream(Transporte.values())
                .filter(v -> v.getId() != 4)
                .map(op ->
                        new DominioResponse(op.getId(), op.getNome())
                ).toList();
    }

    public List<MapaLocalResponse> listarLocal() {
        return this.localRepository.buscarMapaLocal()
                .stream()
                .map(op -> new MapaLocalResponse(op.getId(), op.getNome(), new PointResponse(op.getX(), op.getY()))
                ).toList();
    }
}
