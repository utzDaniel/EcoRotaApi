package ecorota.api.controller;

import ecorota.api.controller.dto.response.MapaResponse;
import ecorota.api.enun.Role;
import ecorota.api.service.MapaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
