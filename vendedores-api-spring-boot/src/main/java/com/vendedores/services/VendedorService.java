package com.vendedores.services;

import com.vendedores.dto.VendedorDTO;
import com.vendedores.models.Vendedor;

import java.util.List;

public interface VendedorService {

    VendedorDTO crearVendedor(VendedorDTO dto);

    VendedorDTO obtenerVendedorPorId(Integer id);

    VendedorDTO actualizarVendedor(Integer id, VendedorDTO dto);

    List<VendedorDTO> listarPorSucursal(Integer idSucursal);

    List<VendedorDTO> listarTodos();

    Vendedor convertirDTOaEntidad(VendedorDTO dto); // ✅ método necesario para HATEOAS
}
