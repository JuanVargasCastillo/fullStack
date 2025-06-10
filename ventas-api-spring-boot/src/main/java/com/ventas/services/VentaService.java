package com.ventas.services;

import com.ventas.models;
import com.ventas.dto.VentaDTO;
import com.ventas.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired private VentaRepository ventaRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private ProductoRepository productoRepo;

    public Venta crearVentaDesdeDTO(VentaRequestDTO dto) {
        Venta venta = new Venta();
        venta.setFecha(LocalDate.now());
        venta.setCliente(clienteRepo.findById(dto.getClienteId()).orElseThrow());

        List<DetalleVenta> detalles = dto.getDetalles().stream().map(detalleDto -> {
            DetalleVenta d = new DetalleVenta();
            d.setProducto(productoRepo.findById(detalleDto.getProductoId()).orElseThrow());
            d.setCantidad(detalleDto.getCantidad());
            d.setPrecioUnitario(detalleDto.getPrecioUnitario());
            d.setVenta(venta);
            return d;
        }).collect(Collectors.toList());

        venta.setDetalles(detalles);
        venta.setTotal(detalles.stream().mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario()).sum());

        return ventaRepo.save(venta);
    }

    public Optional<Venta> obtenerVenta(Long id) {
        return ventaRepo.findById(id);
    }

    public List<Venta> listarVentas() {
        return ventaRepo.findAll();
    }

    public void eliminarVenta(Long id) {
        ventaRepo.deleteById(id);
    }
}