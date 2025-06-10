package com.ventas.repository;

import com.ventas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {}