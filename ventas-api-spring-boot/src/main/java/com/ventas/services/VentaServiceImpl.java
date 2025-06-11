package com.ventas.services;

import com.ventas.dto.*;
import com.ventas.models.*;
import com.ventas.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private CuponRepository cuponRepository;

    @Override
    public VentaDTO crearVenta(VentaDTO dto) {
    Venta ventaInicial = new Venta(null, dto.getIdCliente(), dto.getIdVendedor(), dto.getFechaVenta(), dto.getTotal());
    Venta ventaGuardada = ventaRepository.save(ventaInicial);

    List<DetalleVenta> detalles = dto.getDetalles().stream().map(d -> {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdVenta(ventaGuardada.getIdventa());
        detalle.setIdProducto(d.getIdProducto());
        detalle.setCantidad(d.getCantidad());
        detalle.setPrecioUnitario(d.getPrecioUnitario());
        return detalle;
    }).collect(Collectors.toList());

    detalleVentaRepository.saveAll(detalles);

    return dto;
}


    @Override
    public VentaDTO obtenerVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        List<DetalleVenta> detalles = detalleVentaRepository.findAll()
            .stream()
            .filter(d -> d.getIdVenta().equals(id))
            .collect(Collectors.toList());

        List<DetalleVentaDTO> detallesDTO = detalles.stream().map(d -> {
            DetalleVentaDTO dto = new DetalleVentaDTO();
            dto.setIdProducto(d.getIdProducto());
            dto.setCantidad(d.getCantidad());
            dto.setPrecioUnitario(d.getPrecioUnitario());
            return dto;
        }).collect(Collectors.toList());

        return new VentaDTO(venta.getIdCliente(), venta.getIdVendedor(), venta.getFechaVenta(), venta.getTotal(), detallesDTO);
    }

    @Override
    public List<VentaDTO> obtenerVentasPorCliente(Integer clienteId) {
        List<Venta> ventas = ventaRepository.findAll()
            .stream()
            .filter(v -> v.getIdCliente().equals(clienteId))
            .collect(Collectors.toList());

        return ventas.stream().map(v -> new VentaDTO(
                v.getIdCliente(), v.getIdVendedor(), v.getFechaVenta(), v.getTotal(), new ArrayList<>()
        )).collect(Collectors.toList());
    }

    @Override
    public boolean validarCupon(CuponValidacionDTO cuponDTO) {
        return cuponRepository.findById(cuponDTO.getCodigo())
                .map(Cupon::isActivo)
                .orElse(false);
    }
}
