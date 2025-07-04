package com.vendedores.mapper;

import com.vendedores.controllers.VendedorController;
import com.vendedores.dto.VendedorHateoasDTO;
import com.vendedores.models.Vendedor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class VendedorMapper {

    public VendedorHateoasDTO toHateoasDTO(Vendedor vendedor) {
        VendedorHateoasDTO dto = new VendedorHateoasDTO();

        dto.setIdVendedor(vendedor.getIdVendedor());
        dto.setIdUsuario(vendedor.getIdUsuario());
        dto.setNombreCompleto(vendedor.getNombreCompleto());
        dto.setRut(vendedor.getRut());
        dto.setDireccion(vendedor.getDireccion());
        dto.setTelefono(vendedor.getTelefono());
        dto.setSucursalAsignada(vendedor.getSucursalAsignada());

        // Agregar enlaces HATEOAS
        dto.add(linkTo(methodOn(VendedorController.class).obtenerVendedorHateoas(vendedor.getIdVendedor())).withSelfRel());
        dto.add(linkTo(methodOn(VendedorController.class).listarTodosHateoas()).withRel("todos"));
        dto.add(linkTo(methodOn(VendedorController.class).listarPorSucursalHateoas(
            vendedor.getSucursalAsignada() != null ? vendedor.getSucursalAsignada().getId() : 0)).withRel("porSucursal"));

        return dto;
    }
}
