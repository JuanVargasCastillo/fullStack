package com.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

}