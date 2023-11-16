package ecorota.api.controller;

import ecorota.api.controller.dto.response.DominioResponse;
import ecorota.api.service.DominioService;
import ecorota.api.service.MapaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dominio")
public class DominioController {

    @Autowired
    private DominioService dominioService;

    @GetMapping("/opcao-trajeto")
    public ResponseEntity<List<DominioResponse>> listarOpcaoTrajeto() {
        var list = dominioService.listarOpcaoTrajeto();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/transporte")
    public ResponseEntity<List<DominioResponse>> listarTransporte() {
        var list = dominioService.listarTransporte();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/local")
    public ResponseEntity<List<DominioResponse>> listarLocal() {
        var list = dominioService.listarLocal();
        return ResponseEntity.ok(list);
    }

}
