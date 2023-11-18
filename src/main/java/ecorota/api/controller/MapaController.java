package ecorota.api.controller;

import ecorota.api.controller.dto.request.MapaTrajetoRequest;
import ecorota.api.controller.dto.response.MapaResponse;
import ecorota.api.controller.dto.response.MapaTrajetoResponse;
import ecorota.api.enun.Role;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.service.MapaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mapa")
public class MapaController {

    @Autowired
    private MapaService mapaService;

    @GetMapping
    @Secured(value = Role.USUARIO)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<List<MapaResponse>> buscar() {
        var mapa = mapaService.buscar();
        return ResponseEntity.ok(mapa);
    }

    @PostMapping("/trajeto")
    @Secured(value = Role.USUARIO)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public ResponseEntity<MapaTrajetoResponse> buscarTrajeto(@RequestBody @Valid MapaTrajetoRequest request, @AuthenticationPrincipal Usuario usuario) {
        var resp = mapaService.buscarTrajeto(request, usuario);
        return ResponseEntity.ok(resp);
    }

}
