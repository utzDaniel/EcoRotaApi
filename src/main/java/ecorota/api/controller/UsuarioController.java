package ecorota.api.controller;

import ecorota.api.dto.request.usuario.UsuarioAtualizarRequest;
import ecorota.api.dto.request.usuario.UsuarioCriarRequest;
import ecorota.api.dto.response.UsuarioResponse;
import ecorota.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    @Secured(value = {"ADMIN"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody @Valid UsuarioAtualizarRequest request) {
        var resp = usuarioService.atualizar(request);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    @Secured(value = {"ADMIN", "USER"})
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<UsuarioResponse> buscar(@PathVariable @Valid Long id) {
        var usario = usuarioService.buscar(id);
        return ResponseEntity.ok(usario);
    }

    @DeleteMapping("/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<String> deletar(@PathVariable @Valid Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
