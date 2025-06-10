package com.envios.repository;

import com.ventas.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {}
