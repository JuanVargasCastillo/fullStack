package com.ventas.repository;

import com.ventas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, String> {}
