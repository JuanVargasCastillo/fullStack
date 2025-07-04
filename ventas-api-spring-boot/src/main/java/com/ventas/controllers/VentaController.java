package com.ventas.controllers;

import com.ventas.dto.*;
import com.ventas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody VentaDTO ventaDTO) {
        return ResponseEntity.ok(ventaService.crearVenta(ventaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerVenta(id));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> ventasPorCliente(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(ventaService.obtenerVentasPorCliente(clienteId));
    }

    @PostMapping("/cupones/validar")
    public ResponseEntity<?> validarCupon(@RequestBody CuponValidacionDTO cupon) {
        return ResponseEntity.ok(ventaService.validarCupon(cupon));
    }

    //HATEOAS
    @GetMapping("/hateoas/{id}")
    public VentaDTO obtenerVentaHateoas(@PathVariable Long id) {
    VentaDTO dto = ventaService.obtenerVenta(id);

    // Reemplaza el host por el del GATEWAY
    String baseUrl = "http://localhost:8888/api/proxy/ventas";

    dto.add(Link.of(baseUrl + "/hateoas/" + id).withSelfRel());
    dto.add(Link.of(baseUrl + "/hateoas/cliente/" + dto.getIdCliente()).withRel("ventas-cliente"));
    dto.add(Link.of(baseUrl).withRel("crear").withType("POST"));

    return dto;
    }

    @GetMapping("/hateoas/cliente/{clienteId}")
    public List<VentaDTO> ventasHateoasPorCliente(@PathVariable Integer clienteId) {
    List<VentaDTO> lista = ventaService.obtenerVentasPorCliente(clienteId);

    String baseUrl = "http://localhost:8888/api/proxy/ventas";

    for (VentaDTO dto : lista) {
        if (dto.getIdVenta() != null) {
            dto.add(Link.of(baseUrl + "/hateoas/" + dto.getIdVenta()).withSelfRel());
        }
    }

    return lista;
    }

    @GetMapping("/hateoas")
    public List<VentaDTO> listarVentasHateoas() {
    List<VentaDTO> ventas = ventaService.obtenerTodas();
    String baseUrl = "http://localhost:8888/api/proxy/ventas";

    for (VentaDTO dto : ventas) {
        if (dto.getIdVenta() != null) {
            dto.add(Link.of(baseUrl + "/hateoas/" + dto.getIdVenta()).withSelfRel());
            dto.add(Link.of(baseUrl + "/hateoas/cliente/" + dto.getIdCliente()).withRel("ventas-cliente"));
        }
    }

    return ventas;
    }


}
