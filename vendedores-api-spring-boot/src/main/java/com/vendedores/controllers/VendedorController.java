package com.vendedores.controllers;

import com.vendedores.dto.VendedorDTO;
import com.vendedores.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;   

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Crear nuevo vendedor
    @PostMapping
    public ResponseEntity<VendedorDTO> crearVendedor(@RequestBody VendedorDTO dto) {
        return ResponseEntity.ok(vendedorService.crearVendedor(dto));
    }

    // Obtener vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vendedorService.obtenerVendedorPorId(id));
    }

    // Actualizar vendedor por ID
    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> actualizarVendedor(@PathVariable Integer id, @RequestBody VendedorDTO dto) {
        return ResponseEntity.ok(vendedorService.actualizarVendedor(id, dto));
    }

    // Obtener vendedores por sucursal
    @GetMapping("/sucursal/{id}")
    public ResponseEntity<List<VendedorDTO>> listarPorSucursal(@PathVariable Integer id) {
        return ResponseEntity.ok(vendedorService.listarPorSucursal(id));
    }

    // Listar todos los vendedores
    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listarTodos() {
        return ResponseEntity.ok(vendedorService.listarTodos());
    }

    // Eliminar vendedor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVendedor(@PathVariable Integer id) {
        boolean eliminado = vendedorService.eliminarVendedor(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}