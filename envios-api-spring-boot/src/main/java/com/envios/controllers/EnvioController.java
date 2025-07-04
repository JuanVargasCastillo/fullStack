package com.envios.controllers;

import com.envios.dto.*;
import com.envios.services.EnvioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.util.List;
import java.util.ArrayList;
import org.springframework.hateoas.Link;

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
    @GetMapping
    public ResponseEntity<String> mensajeDeEstado() {
        return ResponseEntity.ok("API de envíos operativa");
    }
 // HATEOAS
    @GetMapping("/hateoas/{id}")
    public EnvioDTO obtenerEnvioHateoas(@PathVariable Long id) {
    EnvioDTO dto = envioService.obtenerEnvio(id);

    // Reemplaza el host y el puerto por el del API Gateway
    String baseUrl = "http://localhost:8888/api/envios";  // Aquí defines el puerto 8888

    // Agregar enlaces HATEOAS
    dto.add(Link.of(baseUrl + "/hateoas/" + id).withSelfRel());
    dto.add(Link.of(baseUrl + "/hateoas").withRel("todos"));
    dto.add(Link.of(baseUrl + "/hateoas/estado/" + id).withRel("actualizar_estado"));

    return dto;
    }

    @GetMapping("/hateoas")
    public List<EnvioDTO> listarEnviosHateoas() {
    List<EnvioDTO> lista = new ArrayList<>();
    List<EnvioDTO> envios = envioService.listarTodos();  // Implementa este método en tu servicio

    // Definir el baseUrl para el API Gateway
    String baseUrl = "http://localhost:8888/api/envios";

    // Agregar enlaces HATEOAS para cada envío
    for (EnvioDTO dto : envios) {
        if (dto.getIdVenta() != null) {
            dto.add(Link.of(baseUrl + "/hateoas/" + dto.getIdVenta()).withSelfRel());
            dto.add(Link.of(baseUrl + "/hateoas/estado/" + dto.getIdVenta()).withRel("actualizar_estado"));
        }
        lista.add(dto);
    }

    return lista;
}
}

