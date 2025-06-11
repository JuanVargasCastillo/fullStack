package com.inventarios.controllers;

import com.inventarios.dto.AjusteStockDTO;
import com.inventarios.dto.InventarioDTO;
import com.inventarios.models.Inventario;
import com.inventarios.services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> obtenerInventarioPorProducto(@PathVariable("id") Long idProducto) {
        return inventarioService.obtenerPorIdProducto(idProducto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Método PUT modificado para recibir JSON en el body
    @PutMapping("/ajuste")
    public ResponseEntity<?> ajustarInventario(@RequestBody AjusteStockDTO ajuste) {
        try {
            Inventario actualizado = inventarioService.ajustarStock(ajuste.getIdProducto(), ajuste.getCantidad());
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/movimiento")
    public ResponseEntity<?> registrarMovimiento(@RequestBody InventarioDTO dto) {
        Inventario nuevo = inventarioService.registrarMovimiento(dto);
        return ResponseEntity.ok(nuevo);
    }
}
