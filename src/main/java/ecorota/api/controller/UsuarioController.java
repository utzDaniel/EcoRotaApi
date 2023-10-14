package ecorota.api.controller;

import ecorota.api.controller.dto.request.usuario.UsuarioAtualizarRequest;
import ecorota.api.controller.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.controller.dto.response.UsuarioResponse;
import ecorota.api.enun.Role;
import ecorota.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid UsuarioCriarRequest request, UriComponentsBuilder uriBuilder) {
        var resp = usuarioService.cadastrar(request);
        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(resp.getId()).toUri();
        return ResponseEntity.created(uri).body(resp.getResponse());
    }

    @PutMapping
    @Secured(value = Role.USUARIO)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody @Valid UsuarioAtualizarRequest request) {
        var resp = usuarioService.atualizar(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping()
    @Secured(value = Role.USUARIO)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<UsuarioResponse> buscar() {
        var usario = usuarioService.buscar();
        return ResponseEntity.ok(usario);
    }

    @GetMapping("/{id}")
    @Secured(value = Role.ADMINISTRADOR)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable @Valid Long id) {
        var usario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usario);
    }

    @GetMapping("/lista")
    @Secured(value = Role.ADMINISTRADOR)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<UsuarioResponse>> listar() {
        var usario = usuarioService.listar();
        return ResponseEntity.ok(usario);
    }

    @DeleteMapping("/{id}")
    @Secured(value = Role.ADMINISTRADOR)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<String> deletar(@PathVariable @Valid Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
