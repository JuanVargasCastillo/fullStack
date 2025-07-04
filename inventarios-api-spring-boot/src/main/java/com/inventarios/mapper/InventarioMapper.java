package com.inventarios.mapper;

import com.inventarios.controllers.InventarioController;
import com.inventarios.dto.InventarioHateoasDTO;
import com.inventarios.models.Inventario;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InventarioMapper {

    private static final String API_GATEWAY_PREFIX = "http://localhost:8888/api/proxy";

    public InventarioHateoasDTO toHateoasDTO(Inventario inventario) {
        InventarioHateoasDTO dto = new InventarioHateoasDTO();

        dto.setIdInventario(inventario.getIdInventario());
        dto.setIdProducto(inventario.getIdProducto());
        dto.setStockDisponible(inventario.getStockDisponible());
        dto.setUbicacionBodega(inventario.getUbicacionBodega());

        // Crear los links base usando WebMvcLinkBuilder
        Link selfLink = linkTo(methodOn(InventarioController.class)
                .obtenerInventarioHateoas(inventario.getIdProducto()))
                .withSelfRel();

        Link ajustarLink = linkTo(methodOn(InventarioController.class)
                .ajustarInventario(null))
                .withRel("ajustar");

        Link nuevoLink = linkTo(methodOn(InventarioController.class)
                .registrarMovimiento(null))
                .withRel("nuevo");

        // Reemplazar el host/puerto base por el del API Gateway
        dto.add(selfLink.withHref(API_GATEWAY_PREFIX + extractPath(selfLink.getHref())));
        dto.add(ajustarLink.withHref(API_GATEWAY_PREFIX + extractPath(ajustarLink.getHref())));
        dto.add(nuevoLink.withHref(API_GATEWAY_PREFIX + extractPath(nuevoLink.getHref())));

        return dto;
    }

    // Función auxiliar para extraer el path a partir del href completo (por ejemplo, /api/inventarios/...)
    private String extractPath(String href) {
        int index = href.indexOf("/api/");
        return index != -1 ? href.substring(index) : href;
    }
}
