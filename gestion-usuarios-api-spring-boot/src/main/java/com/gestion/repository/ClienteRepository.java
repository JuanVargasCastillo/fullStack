package com.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}