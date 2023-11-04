package ecorota.api.service;

import ecorota.api.controller.dto.response.DominioResponse;
import ecorota.api.enun.OpcaoTrajeto;
import ecorota.api.enun.Transporte;
import ecorota.api.repository.LocalRepository;
import ecorota.api.repository.UsuarioRepository;
import ecorota.api.service.factory.UsuarioFactory;
import ecorota.api.service.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DominioService {

    @Autowired
    private LocalRepository localRepository;


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

    public List<DominioResponse> listarLocal() {
        return this.localRepository
                .findAll()
                .stream()
                .map(op -> new DominioResponse((int)op.getId(), op.getNome())
                ).toList();
    }
}
