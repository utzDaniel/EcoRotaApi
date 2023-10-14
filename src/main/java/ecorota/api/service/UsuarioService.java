package ecorota.api.service;

import ecorota.api.controller.dto.request.usuario.UsuarioAtualizarRequest;
import ecorota.api.controller.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.controller.dto.response.CriarResponse;
import ecorota.api.controller.dto.response.UsuarioResponse;
import ecorota.api.infra.exception.SenhaImcompletaException;
import ecorota.api.repository.UsuarioRepository;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.service.factory.UsuarioFactory;
import ecorota.api.service.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @Transactional
    public CriarResponse cadastrar(UsuarioCriarRequest request) {
        var usuario = UsuarioFactory.create(request);
        usuarioRepository.save(usuario);
        var resp = usuarioMapper.parse(usuario);
        return new CriarResponse(usuario.getId(), resp);
    }

    @Transactional
    public UsuarioResponse atualizar(UsuarioAtualizarRequest request, Usuario usuario) {
        var usuarioBase = usuarioRepository.findByEmail(usuario.getEmail());
        var alterarSenha = validarCampoSenhas(request.getNovaSenha(), request.getNovaSenhaRepetida());

        if (alterarSenha && (!request.getNovaSenha().equals(request.getNovaSenhaRepetida()))) {
            throw new SenhaImcompletaException("Senhas não conferem, tente novamente");
        }

        if (!UsuarioFactory.isSenha(request.getSenhaAtual(), usuarioBase.getPassword())) {
            throw new SenhaImcompletaException("Senha atual não confere, tente novamente");
        }

        var senha = alterarSenha ? UsuarioFactory.criptografarSenha(request.getNovaSenha()) : usuarioBase.getPassword();

        usuarioRepository.update(usuario.getEmail(), request.getNome(), senha);
        usuario.setNome(request.getNome());
        return usuarioMapper.parse(usuario);
    }

    public UsuarioResponse buscar(Usuario usuario) {
        return usuarioMapper.parse(usuario);
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

    private boolean validarCampoSenhas(String senhaNova, String senhaNovaRepetida) {
        var campoSenha = Objects.isNull(senhaNova) || senhaNova.isEmpty();
        var campoSenhaRepetida = Objects.isNull(senhaNovaRepetida) || senhaNovaRepetida.isEmpty();
        return !campoSenha && !campoSenhaRepetida;
    }
}
