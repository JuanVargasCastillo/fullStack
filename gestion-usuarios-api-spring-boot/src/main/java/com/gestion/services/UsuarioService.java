package com.gestion.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestion.config.UsuarioMapper;
import com.gestion.dto.CrearUsuarioRequest;
import com.gestion.dto.UsuarioDTO;
import com.gestion.models.Cliente;
import com.gestion.models.Usuario;
import com.gestion.models.Vendedor;
import com.gestion.repository.ClienteRepository;
import com.gestion.repository.UsuarioRepository;
import com.gestion.repository.VendedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final ClienteRepository clienteRepo;
    private final VendedorRepository vendedorRepo;
    private final PasswordEncoder passwordEncoder; 
    private final UsuarioMapper mapper;

    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepo.findAll().stream()
            .map(u -> new UsuarioDTO(u.getIdUsuario(), u.getNombreUsuario(), u.getEmail(), u.getRol(), u.getEstado()))
            .toList();
    }

    public Usuario crearUsuario(CrearUsuarioRequest req) {
        Usuario u = new Usuario();
        u.setNombreUsuario(req.getNombreUsuario());
        u.setEmail(req.getEmail());

        System.out.println("DEBUG >> contrasena: " + req.getContrasena());
        System.out.println(req);

        // ✅ Validar que la contraseña no sea nula ni vacía
        String rawPassword = req.getContrasena(); // ✅ correcto, coincide con el DTO
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new IllegalArgumentException("La clave es requerida");
        }
        u.setContrasena(passwordEncoder.encode(rawPassword));

        u.setRol(req.getRol());
        u.setEstado(req.getEstado());

        Usuario nuevoUsuario = usuarioRepo.save(u);

        switch (req.getRol().toLowerCase()) {
            case "cliente" -> {
                Cliente c = new Cliente();
                c.setIdCliente(null);
                c.setUsuario(nuevoUsuario);
                c.setNombreCompleto(req.getNombreCompleto());
                c.setRut(req.getRut());
                c.setDireccion(req.getDireccion());
                c.setTelefono(req.getTelefono());
                clienteRepo.save(c);
            }
            case "vendedor" -> {
                Vendedor v = new Vendedor();
                v.setIdVendedor(null);
                v.setUsuario(nuevoUsuario);
                v.setNombreCompleto(req.getNombreCompleto());
                v.setRut(req.getRut());
                v.setAreaVentas(req.getAreaVentas());
                vendedorRepo.save(v);
            }
            case "admin" -> {
                // No se necesita nada extra, solo se guarda como usuario
            }
            default -> throw new IllegalArgumentException("Rol no soportado: " + req.getRol());
        }

        return nuevoUsuario;
    }

    public UsuarioDTO buscarUsuarioPorId(Integer id) {
        Usuario usuario = usuarioRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Convertir Usuario a UsuarioDTO
        return mapper.usuarioToDto(usuario);
    }

    public UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setEmail(dto.getEmail());
        usuario.setEstado(dto.getEstado());
        usuario.setRol(dto.getRol());
        // agrega más campos si es necesario

        Usuario guardado = usuarioRepo.save(usuario);
        return mapper.usuarioToDto(guardado);
    }



    public void eliminarUsuario(Integer id) {
        usuarioRepo.deleteById(id);
    }
}
