package ecorota.api.service;

import ecorota.api.controller.dto.request.usuario.UsuarioAtualizarRequest;
import ecorota.api.controller.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.controller.dto.response.CriarResponse;
import ecorota.api.controller.dto.response.UsuarioResponse;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.service.factory.UsuarioFactory;
import ecorota.api.service.mapper.UsuarioMapper;
import ecorota.api.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var senha = usuarioFactory.senha(request.getSenha());
        usuarioRepository.update(usuarioLogado.getUsername(), request.getNome(), senha);
        usuarioLogado.setNome(request.getNome());
        return usuarioMapper.parse(usuarioLogado);
    }

    public UsuarioResponse buscar() {
        var usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioMapper.parse(usuarioLogado);
    }

    public UsuarioResponse buscarPorId(Long id) {
        var usuario = usuarioRepository.getReferenceById(id);
        return usuarioMapper.parse(usuario);
    }

    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream()
                .map(u -> usuarioMapper.parse(u))
                .toList();
    }

    @Transactional
    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

}
