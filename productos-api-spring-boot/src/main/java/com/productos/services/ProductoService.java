package com.productos.services;

import com.productos.models.Producto;
import com.productos.dto.ProductoDTO;
import com.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDTO toDTO(Producto producto) {
        return new ProductoDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecioUnitario(),
                producto.getCategoria(),
                producto.getActivo()
        );
    }

    private Producto toEntity(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId()); // importante para actualizar
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.getActivo());
        return producto;
    }

    public ProductoDTO crear(ProductoDTO dto) {
        Producto producto = toEntity(dto);
        return toDTO(productoRepository.save(producto));
    }

    public List<ProductoDTO> listar() {
        return productoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ProductoDTO obtenerPorId(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(producto);
    }

    public ProductoDTO actualizar(Integer id, ProductoDTO dto) {
        Producto existente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existente.setNombre(dto.getNombre());
        existente.setDescripcion(dto.getDescripcion());
        existente.setPrecioUnitario(dto.getPrecioUnitario());
        existente.setCategoria(dto.getCategoria());
        existente.setActivo(dto.getActivo());

        return toDTO(productoRepository.save(existente));
    }

    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
}