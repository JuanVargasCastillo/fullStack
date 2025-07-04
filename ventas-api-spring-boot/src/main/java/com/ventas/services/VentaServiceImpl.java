package com.ventas.services;

import com.ventas.dto.VentaDTO;
import com.ventas.dto.CuponValidacionDTO;
import com.ventas.models.Venta;
import com.ventas.models.Cupon;
import com.ventas.repository.CuponRepository;
import com.ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Override
    public VentaDTO crearVenta(VentaDTO dto) {
        Venta venta = new Venta(null, dto.getIdCliente(), dto.getIdVendedor(), dto.getFechaVenta(), dto.getTotal());
        ventaRepository.save(venta);
        return dto;
    }

    @Override
    public VentaDTO obtenerVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        return new VentaDTO(venta.getIdCliente(), venta.getIdVendedor(), venta.getFechaVenta(), venta.getTotal(), null);
    }

    @Override
    public List<VentaDTO> obtenerVentasPorCliente(Integer clienteId) {
        return ventaRepository.findAll().stream()
                .filter(v -> v.getIdCliente().equals(clienteId))
                .map(v -> new VentaDTO(v.getIdCliente(), v.getIdVendedor(), v.getFechaVenta(), v.getTotal(), null))
                .collect(Collectors.toList());
    }

    @Override
    public boolean validarCupon(CuponValidacionDTO cuponDTO) {
        return cuponRepository.findById(cuponDTO.getCodigo())
                .map(Cupon::isActivo)
                .orElse(false);
    }
}
