package com.soportes.services;

import com.soportes.models.Ticket;
import com.soportes.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SoporteService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket crearTicket(Ticket ticket) {
        if (ticket.getFechaCreacion() == null) {
            ticket.setFechaCreacion(LocalDateTime.now());
        }
        return ticketRepository.save(ticket);
    }

    public List<Ticket> obtenerTicketsPorCliente(Integer idUsuario) {
        return ticketRepository.findByIdUsuario(idUsuario);
    }

    public boolean actualizarEstado(Integer id, String nuevoEstado) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setEstado(nuevoEstado);
            if (nuevoEstado.equalsIgnoreCase("resuelto")) {
                ticket.setFechaResolucion(LocalDateTime.now());
            }
            ticketRepository.save(ticket);
            return true;
        } else {
            return false;
        }
    }

    // ✅ Método para HATEOAS: obtener un ticket por ID
    public Ticket obtenerTicketPorId(Integer id) {
        return ticketRepository.findById(id).orElse(null);
    }

    // ✅ Método para HATEOAS: obtener todos los tickets
    public List<Ticket> obtenerTodos() {
        return ticketRepository.findAll();
    }
}
