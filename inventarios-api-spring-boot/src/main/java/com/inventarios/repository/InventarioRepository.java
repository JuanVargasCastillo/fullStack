package com.inventarios.repository;

import com.inventarios.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByIdProducto(Long idProducto);
}
