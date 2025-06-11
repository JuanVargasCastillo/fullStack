package com.ventas.controllers;

import com.Ventas.dto.VentaDTO;
import com.Ventas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
