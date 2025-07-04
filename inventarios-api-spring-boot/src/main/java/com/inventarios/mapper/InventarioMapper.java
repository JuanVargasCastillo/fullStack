package com.inventarios.mapper;

import com.inventarios.controllers.InventarioController;
import com.inventarios.dto.InventarioHateoasDTO;
import com.inventarios.models.Inventario;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioMapper {

    public InventarioHateoasDTO toHateoasDTO(Inventario inventario) {
        InventarioHateoasDTO dto = new InventarioHateoasDTO();

        dto.setIdInventario(inventario.getIdInventario());
        dto.setIdProducto(inventario.getIdProducto());
        dto.setStockDisponible(inventario.getStockDisponible());
        dto.setUbicacionBodega(inventario.getUbicacionBodega());

        // Enlace al recurso actual
        dto.add(linkTo(methodOn(InventarioController.class)
                .obtenerInventarioHateoas(inventario.getIdProducto()))
                .withSelfRel());

        // Enlace para ajustar stock
        dto.add(linkTo(methodOn(InventarioController.class)
                .ajustarInventario(null))
                .withRel("ajustar"));

        // Enlace para registrar nuevo movimiento
        dto.add(linkTo(methodOn(InventarioController.class)
                .registrarMovimiento(null))
                .withRel("nuevo"));

        return dto;
    }
}