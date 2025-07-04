package com.ventas.services;

import com.ventas.dto.VentaDTO;
import com.ventas.dto.CuponValidacionDTO;

import java.util.List;

public interface VentaService {
    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO obtenerVenta(Long id);
    List<VentaDTO> obtenerVentasPorCliente(Integer clienteId);
    List<VentaDTO> obtenerTodas(); // ← Asegúrate de que esté aquí
    boolean validarCupon(CuponValidacionDTO cuponDTO);
}
