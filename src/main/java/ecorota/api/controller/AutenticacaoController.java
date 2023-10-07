package ecorota.api.controller;

import ecorota.api.dto.request.AutenticacaoRequest;
import ecorota.api.dto.response.TokenJWTResponse;
import ecorota.api.entity.Usuario;
import ecorota.api.mapper.TokenJWTMapper;
import ecorota.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTResponse> login(@RequestBody @Valid AutenticacaoRequest request) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerar((Usuario) authentication.getPrincipal());
        var resp = new TokenJWTMapper().parse(token);
        return ResponseEntity.ok().body(resp);
    }
}
