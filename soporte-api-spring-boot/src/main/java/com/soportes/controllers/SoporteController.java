package com.soportes.controllers;

import com.soportes.models.Ticket;
import com.soportes.services.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @PostMapping("/tickets")
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        return soporteService.crearTicket(ticket);
    }

    @GetMapping("/tickets/cliente/{id}")
    public List<Ticket> obtenerTicketsPorCliente(@PathVariable("id") Integer idUsuario) {
        return soporteService.obtenerTicketsPorCliente(idUsuario);
    }

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
}
