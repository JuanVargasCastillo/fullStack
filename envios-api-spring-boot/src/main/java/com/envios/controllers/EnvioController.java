package com.envios.controllers;

import com.envios.dto.*;
import com.envios.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @PostMapping
    public ResponseEntity<?> crearEnvio(@RequestBody EnvioDTO envioDTO) {
        return ResponseEntity.ok(envioService.crearEnvio(envioDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerEnvio(@PathVariable Long id) {
        return ResponseEntity.ok(envioService.obtenerEnvio(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Long id, @RequestBody CambioEstadoEnvioDTO dto) {
        return ResponseEntity.ok(envioService.actualizarEstadoEnvio(id, dto.getNuevoEstado()));
    }
}
