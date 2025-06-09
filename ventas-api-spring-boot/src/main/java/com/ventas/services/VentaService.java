package com.ventas.services;

import com.Ventas.models.Venta;
import com.Ventas.dto.VentaDTO;
import com.Ventas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    @Autowired
    private VentaRepository VentaRepository;

    private VentaDTO toDTO(Venta Venta) {
        return new VentaDTO(
                Venta.getId(),
                Venta.getNombre(),
                Venta.getDescripcion(),
                Venta.getPrecioUnitario(),
                Venta.getCategoria(),
                Venta.getActivo()
        );
    }

    private Venta toEntity(VentaDTO dto) {
        Venta Venta = new Venta();
        Venta.setId(dto.getId()); // importante para actualizar
        Venta.setNombre(dto.getNombre());
        Venta.setDescripcion(dto.getDescripcion());
        Venta.setPrecioUnitario(dto.getPrecioUnitario());
        Venta.setCategoria(dto.getCategoria());
        Venta.setActivo(dto.getActivo());
        return Venta;
    }

    public VentaDTO crear(VentaDTO dto) {
        Venta Venta = toEntity(dto);
        return toDTO(VentaRepository.save(Venta));
    }

    public List<VentaDTO> listar() {
        return VentaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VentaDTO obtenerPorId(Integer id) {
        Venta Venta = VentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrado"));
        return toDTO(Venta);
    }

    public VentaDTO actualizar(Integer id, VentaDTO dto) {
        Venta existente = VentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        existente.setCategoria(dto.getCategoria());
        existente.setActivo(dto.getActivo());

        return toDTO(VentaRepository.save(existente));
    }

    public void eliminar(Integer id) {
        VentaRepository.deleteById(id);
    }
}