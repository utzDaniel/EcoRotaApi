package ecorota.api.service;

import ecorota.api.controller.dto.request.HistoricoRequest;
import ecorota.api.controller.dto.response.CriarResponse;
import ecorota.api.controller.dto.response.HistoricoResponse;
import ecorota.api.repository.DeslocamentoRepository;
import ecorota.api.repository.HistoricoRepository;
import ecorota.api.repository.OpcoesDeslocamentoRepository;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.repository.entity.UsuariosHistorico;
import ecorota.api.service.mapper.HistoricoMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private OpcoesDeslocamentoRepository opcoesDeslocamentoRepository;
    @Autowired
    private DeslocamentoRepository deslocamentoRepository;

    @Autowired
    private HistoricoMapper mapper;

    public HistoricoResponse listar(Usuario usuario) {
        return this.mapper.parse(this.historicoRepository.findAllByUsuarioId(usuario.getId()));
    }

    @Transactional
    public CriarResponse gravar(HistoricoRequest request, Usuario usuario) {
        this.historicoRepository.insert(usuario.getId(), request.getPartida(), request.getDestino());
        var id = this.historicoRepository.getLastInsertedId();
        request.getOpcoes().forEach(v-> {
            this.opcoesDeslocamentoRepository.insert(id, v.isSelecionado());
            var idOp = this.opcoesDeslocamentoRepository.getLastInsertedId();
            v.getDeslocamento().forEach(d -> {
                var rec = d.getRecurso();
                var idTjt = d.getIdTrajeto() <= 0 ? null : d.getIdTrajeto();
                this.deslocamentoRepository.insert(rec.getDistancia(), rec.getTempo(), rec.getCarbono(), idOp, idTjt);
            });
        });
        List<UsuariosHistorico> list = new ArrayList<>();
        var historico = this.historicoRepository.findById((long) id).get();
        list.add(historico);
        var resp = mapper.parse(list);
        return new CriarResponse(historico.getId(), resp);
    }
}
