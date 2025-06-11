package com.inventarios.services;

import com.inventarios.dto.InventarioDTO;
import com.inventarios.models.Inventario;
import com.inventarios.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public Optional<Inventario> obtenerPorIdProducto(Long idProducto) {
        return inventarioRepository.findByIdProducto(idProducto);
    }

    public Inventario ajustarStock(Long idProducto, int cantidad) {
        Inventario inventario = inventarioRepository.findByIdProducto(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        inventario.setStockDisponible(inventario.getStockDisponible() + cantidad);
        return inventarioRepository.save(inventario);
    }

    public Inventario registrarMovimiento(InventarioDTO dto) {
        Inventario nuevo = new Inventario();
        nuevo.setIdProducto(dto.getIdProducto());
        nuevo.setStockDisponible(dto.getStockDisponible());
        nuevo.setUbicacionBodega(dto.getUbicacionBodega());

        return inventarioRepository.save(nuevo);
    }
}
