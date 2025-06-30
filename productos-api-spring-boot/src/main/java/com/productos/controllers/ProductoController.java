package com.productos.controllers;

import com.productos.dto.ProductoDTO;
import com.productos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Integer id, @RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //METODOS HATEOAS

    //METODO HATEOAS para buscar por ID
    @GetMapping("/hateoas/{id}")
    public ProductoDTO obtenerHATEOAS(@PathVariable Integer id) {
        ProductoDTO dto = service.obtenerPorId(id);
        
        //links urls de la misma API
        dto.add(linkTo(methodOn(ProductoController.class).obtenerHATEOAS(id)).withSelfRel());
        dto.add(linkTo(methodOn(ProductoController.class).obtenerTodosHATEOAS()).withRel("todos"));
        dto.add(linkTo(methodOn(ProductoController.class).eliminar(id)).withRel("eliminar"));

        //link HATEOAS para API Gateway "A mano"
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withSelfRel());
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Modificar HATEOAS").withType("PUT"));
        dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Eliminar HATEOAS").withType("DELETE"));

        return dto;
    }

    //METODO HATEOAS para listar todos los productos utilizando HATEOAS
    @GetMapping("/hateoas")
    public List<ProductoDTO> obtenerTodosHATEOAS() {
        List<ProductoDTO> lista = service.listar();

        for (ProductoDTO dto : lista) {
            //link url de la misma API
            dto.add(linkTo(methodOn(ProductoController.class).obtenerHATEOAS(dto.getId())).withSelfRel());

            //link HATEOAS para API Gateway "A mano"
            dto.add(Link.of("http://localhost:8888/api/proxy/productos").withRel("Get todos HATEOAS"));
            dto.add(Link.of("http://localhost:8888/api/proxy/productos/" + dto.getId()).withRel("Crear HATEOAS").withType("POST"));
        }

        return lista;
    }


}