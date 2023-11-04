package ecorota.api.service;

import ecorota.api.controller.dto.response.HistoricoResponse;
import ecorota.api.repository.HistoricoRepository;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.service.mapper.HistoricoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private HistoricoMapper mapper;

    public Page<HistoricoResponse> listar(Pageable paginacao, Usuario usuario) {
        return this.historicoRepository.findAllByUsuarioId(usuario.getId(), paginacao)
                .map(v -> mapper.parse(v));
    }

}
