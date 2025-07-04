package com.ventas.repository;

import com.ventas.models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    // ✅ Agregado para obtener ventas por cliente
    List<Venta> findByIdCliente(Integer idCliente);
}
