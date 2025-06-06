package com.gestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestion.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
