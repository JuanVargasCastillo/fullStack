package com.vendedores.controllers;

import com.vendedores.dto.VendedorDTO;
import com.vendedores.dto.VendedorHateoasDTO;
import com.vendedores.mapper.VendedorMapper;
import com.vendedores.models.Vendedor;
import com.vendedores.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @Autowired
    private VendedorMapper vendedorMapper;

    // Crear nuevo vendedor
    @PostMapping
    public ResponseEntity<VendedorDTO> crearVendedor(@RequestBody VendedorDTO dto) {
        return ResponseEntity.ok(vendedorService.crearVendedor(dto));
    }

    // Obtener vendedor por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendedorDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(vendedorService.obtenerVendedorPorId(id));
    }

    // Actualizar vendedor por ID
    @PutMapping("/{id}")
    public ResponseEntity<VendedorDTO> actualizarVendedor(@PathVariable Integer id, @RequestBody VendedorDTO dto) {
        return ResponseEntity.ok(vendedorService.actualizarVendedor(id, dto));
    }

    // Obtener vendedores por sucursal
    @GetMapping("/sucursal/{id}")
    public ResponseEntity<List<VendedorDTO>> listarPorSucursal(@PathVariable Integer id) {
        return ResponseEntity.ok(vendedorService.listarPorSucursal(id));
    }

    // Listar todos los vendedores
    @GetMapping
    public ResponseEntity<List<VendedorDTO>> listarTodos() {
        return ResponseEntity.ok(vendedorService.listarTodos());
    }

    // Endpoint HATEOAS - obtener uno
    @GetMapping("/hateoas/{id}")
    public ResponseEntity<VendedorHateoasDTO> obtenerVendedorHateoas(@PathVariable Integer id) {
        VendedorDTO dto = vendedorService.obtenerVendedorPorId(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        Vendedor vendedor = vendedorService.convertirDTOaEntidad(dto);
        VendedorHateoasDTO hateoasDTO = vendedorMapper.toHateoasDTO(vendedor);
        return ResponseEntity.ok(hateoasDTO);
    }

    // Endpoint HATEOAS - listar todos
    @GetMapping("/hateoas")
    public ResponseEntity<List<VendedorHateoasDTO>> listarTodosHateoas() {
        List<VendedorDTO> lista = vendedorService.listarTodos();
        List<VendedorHateoasDTO> resultado = lista.stream()
                .map(dto -> vendedorMapper.toHateoasDTO(vendedorService.convertirDTOaEntidad(dto)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }

    // Endpoint HATEOAS - listar por sucursal
    @GetMapping("/hateoas/sucursal/{id}")
    public ResponseEntity<List<VendedorHateoasDTO>> listarPorSucursalHateoas(@PathVariable Integer id) {
        List<VendedorDTO> lista = vendedorService.listarPorSucursal(id);
        List<VendedorHateoasDTO> resultado = lista.stream()
                .map(dto -> vendedorMapper.toHateoasDTO(vendedorService.convertirDTOaEntidad(dto)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultado);
    }
}
