package com.vendedores.repository;

import com.vendedores.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; 

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
    List<Vendedor> findBySucursalAsignadaId(Integer id);
}

