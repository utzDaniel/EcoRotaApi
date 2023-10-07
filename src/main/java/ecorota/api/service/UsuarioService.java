package ecorota.api.service;

import ecorota.api.dto.request.usuario.UsuarioAtualizarRequest;
import ecorota.api.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.dto.response.CriarResponse;
import ecorota.api.dto.response.UsuarioResponse;
import ecorota.api.factory.UsuarioFactory;
import ecorota.api.mapper.UsuarioMapper;
import ecorota.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioFactory usuarioFactory;

    @Transactional
    public CriarResponse cadastrar(UsuarioCriarRequest request) {
        var usuario = usuarioFactory.create(request);
        usuarioRepository.save(usuario);
        var resp = usuarioMapper.parse(usuario);
        return new CriarResponse(usuario.getId(), resp);
    }

    @Transactional
    public UsuarioResponse atualizar(UsuarioAtualizarRequest request) {
        var usuario = usuarioRepository.getReferenceById(request.getId());
        usuario.setNome(request.getNome());
        return usuarioMapper.parse(usuario);
    }

    public UsuarioResponse buscar(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return usuarioMapper.parse(usuario);
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
