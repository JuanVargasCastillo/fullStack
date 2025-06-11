package com.ventas.repository;

import com.ventas.models.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {}