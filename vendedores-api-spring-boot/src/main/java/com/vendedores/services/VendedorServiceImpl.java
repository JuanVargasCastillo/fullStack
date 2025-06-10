package com.vendedores.services;

import com.vendedores.dto.VendedorDTO;
import com.vendedores.models.Vendedor;
import com.vendedores.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; 
import java.util.stream.Collectors;

@Service
public class VendedorServiceImpl implements VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public VendedorDTO crearVendedor(VendedorDTO dto) {
        Vendedor vendedor = toEntity(dto);
        Vendedor saved = vendedorRepository.save(vendedor);
        return toDTO(saved);
    }

    @Override
    public VendedorDTO obtenerVendedorPorId(Integer id) {
        Optional<Vendedor> optional = vendedorRepository.findById(id);
        return optional.map(this::toDTO).orElse(null);
    }

    @Override
    public VendedorDTO actualizarVendedor(Integer id, VendedorDTO dto) {
        Optional<Vendedor> optional = vendedorRepository.findById(id);
        if (optional.isPresent()) {
            Vendedor vendedor = optional.get();
            vendedor.setIdUsuario(dto.getIdUsuario());
            vendedor.setNombreCompleto(dto.getNombreCompleto());
            vendedor.setRut(dto.getRut());
            vendedor.setDireccion(dto.getDireccion());
            vendedor.setTelefono(dto.getTelefono());
            Vendedor actualizado = vendedorRepository.save(vendedor);
            return toDTO(actualizado);
        }
        return null;
    }

    @Override
    public List<VendedorDTO> listarPorSucursal(Integer idSucursal) {
        return vendedorRepository.findBySucursalAsignadaId(idSucursal)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares para mapear entre entidad y DTO
    private VendedorDTO toDTO(Vendedor vendedor) {
        VendedorDTO dto = new VendedorDTO();
        dto.setIdVendedor(vendedor.getIdVendedor());
        dto.setIdUsuario(vendedor.getIdUsuario());
        dto.setNombreCompleto(vendedor.getNombreCompleto());
        dto.setRut(vendedor.getRut());
        dto.setDireccion(vendedor.getDireccion());
        dto.setTelefono(vendedor.getTelefono());
        return dto;
    }

    private Vendedor toEntity(VendedorDTO dto) {
        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(dto.getIdVendedor());
        vendedor.setIdUsuario(dto.getIdUsuario());
        vendedor.setNombreCompleto(dto.getNombreCompleto());
        vendedor.setRut(dto.getRut());
        vendedor.setDireccion(dto.getDireccion());
        vendedor.setTelefono(dto.getTelefono());
        return vendedor;
    }
}
