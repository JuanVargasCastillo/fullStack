package com.gateway.jwt.repository;

import com.gateway.jwt.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario); // ✅ Usa el nombre exacto del atributo
}
