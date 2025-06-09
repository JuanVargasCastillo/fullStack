package com.clientes.services;

import com.clientes.dto.ClienteDTO;
import com.clientes.models.Cliente;
import com.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteDTO guardar(ClienteDTO dto) {
        Cliente cliente = toEntity(dto);
        Cliente saved = repository.save(cliente);
        return toDTO(saved);
    }

    public List<ClienteDTO> listar() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> obtenerPorId(Integer id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    public Optional<ClienteDTO> actualizar(Integer id, ClienteDTO dto) {
        return repository.findById(id).map(cliente -> {
            cliente.setIdUsuario(dto.getIdUsuario());
            cliente.setNombreCompleto(dto.getNombreCompleto());
            cliente.setRut(dto.getRut());
            cliente.setDireccion(dto.getDireccion());
            cliente.setTelefono(dto.getTelefono());
            return toDTO(repository.save(cliente));
        });
    }

    public boolean eliminar(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Métodos auxiliares
    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(cliente.getIdCliente());
        dto.setIdUsuario(cliente.getIdUsuario());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        dto.setRut(cliente.getRut());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());
        return dto;
    }

    private Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(dto.getIdCliente());
        cliente.setIdUsuario(dto.getIdUsuario());
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setRut(dto.getRut());
        cliente.setDireccion(dto.getDireccion());
        cliente.setTelefono(dto.getTelefono());
        return cliente;
    }
}