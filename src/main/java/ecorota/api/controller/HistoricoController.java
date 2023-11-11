package ecorota.api.controller;

import ecorota.api.controller.dto.response.HistoricoResponse;
import ecorota.api.enun.Role;
import ecorota.api.repository.entity.Usuario;
import ecorota.api.service.HistoricoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    @Secured(value = Role.USUARIO)
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    public HistoricoResponse listar(@AuthenticationPrincipal Usuario usuario) {
        return this.historicoService.listar(usuario);
    }

}
