package com.inventarios.controllers;

import com.inventarios.dto.AjusteStockDTO;
import com.inventarios.dto.InventarioDTO;
import com.inventarios.dto.InventarioHateoasDTO;
import com.inventarios.models.Inventario;
import com.inventarios.services.InventarioService;
import com.inventarios.mapper.InventarioMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private InventarioMapper inventarioMapper;

    // Endpoint normal: obtener por ID producto
    @GetMapping("/producto/{id}")
    public ResponseEntity<?> obtenerInventarioPorProducto(@PathVariable("id") Long idProducto) {
        return inventarioService.obtenerPorIdProducto(idProducto)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint normal: ajustar stock
    @PutMapping("/ajuste")
    public ResponseEntity<?> ajustarInventario(@RequestBody AjusteStockDTO ajuste) {
        try {
            Inventario actualizado = inventarioService.ajustarStock(ajuste.getIdProducto(), ajuste.getCantidad());
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint normal: registrar movimiento
    @PostMapping("/movimiento")
    public ResponseEntity<?> registrarMovimiento(@RequestBody InventarioDTO dto) {
        Inventario nuevo = inventarioService.registrarMovimiento(dto);
        return ResponseEntity.ok(nuevo);
    }

    // ✅ Endpoint HATEOAS: obtener un inventario específico
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<InventarioHateoasDTO> obtenerInventarioHateoas(@PathVariable Long id) {
        Inventario inventario = inventarioService.obtenerPorId(id);
        if (inventario == null) {
            return ResponseEntity.notFound().build();
        }

        InventarioHateoasDTO dto = inventarioMapper.toHateoasDTO(inventario);
        dto.add(linkTo(methodOn(InventarioController.class).obtenerInventarioHateoas(id)).withSelfRel());
        dto.add(linkTo(methodOn(InventarioController.class).obtenerTodosInventarioHateoas()).withRel("todos"));

        return ResponseEntity.ok(dto);
    }

    // ✅ Endpoint HATEOAS: obtener todos los inventarios
    @GetMapping("/hateoas")
    public List<InventarioHateoasDTO> obtenerTodosInventarioHateoas() {
        List<Inventario> lista = inventarioService.obtenerTodos();
        return lista.stream().map(inv -> {
            InventarioHateoasDTO dto = inventarioMapper.toHateoasDTO(inv);
            return dto;
        }).collect(Collectors.toList());
    }
}
