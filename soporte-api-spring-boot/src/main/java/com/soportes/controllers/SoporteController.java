package com.soportes.controllers;

import com.soportes.models.Ticket;
import com.soportes.services.SoporteService;
import com.soportes.dto.TicketHateoasDTO;
import com.soportes.mapper.TicketMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @Autowired
    private TicketMapper ticketMapper;  // <-- inyectamos aquí el mapper

    // Endpoint normal
    @PostMapping("/tickets")
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        return soporteService.crearTicket(ticket);
    }

    // Endpoint normal
    @GetMapping("/tickets/cliente/{id}")
    public List<Ticket> obtenerTicketsPorCliente(@PathVariable("id") Integer idUsuario) {
        return soporteService.obtenerTicketsPorCliente(idUsuario);
    }

    // Endpoint normal
    @PutMapping("/tickets/{id}/estado")
    public ResponseEntity<?> actualizarEstado(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        String estado = body.get("estado");
        if (estado == null || estado.isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'estado' es obligatorio");
        }
        boolean actualizado = soporteService.actualizarEstado(id, estado);
        if (actualizado) {
            return ResponseEntity.ok("Estado actualizado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Endpoint HATEOAS: Obtener un ticket con enlaces
    @GetMapping("/hateoas/tickets/{id}")
    public ResponseEntity<TicketHateoasDTO> obtenerTicketHateoas(@PathVariable Integer id) {
        Ticket ticket = soporteService.obtenerTicketPorId(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }

        TicketHateoasDTO dto = ticketMapper.toHateoasDTO(ticket);

        dto.add(linkTo(methodOn(SoporteController.class).obtenerTicketHateoas(id)).withSelfRel());
        dto.add(linkTo(methodOn(SoporteController.class).obtenerTodosHateoas()).withRel("todos"));
        dto.add(linkTo(methodOn(SoporteController.class).actualizarEstado(id, Map.of("estado", "resuelto"))).withRel("actualizarEstado"));

        return ResponseEntity.ok(dto);
    }

    // ✅ Endpoint HATEOAS: Listar todos los tickets con enlaces
    @GetMapping("/hateoas/tickets")
    public List<TicketHateoasDTO> obtenerTodosHateoas() {
        List<Ticket> tickets = soporteService.obtenerTodos();
        return tickets.stream().map(ticket -> {
            TicketHateoasDTO dto = ticketMapper.toHateoasDTO(ticket);  
            return dto;
        }).collect(Collectors.toList());
    }
}
