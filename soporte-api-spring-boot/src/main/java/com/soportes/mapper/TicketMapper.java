package com.soportes.mapper;

import com.soportes.controllers.SoporteController;
import com.soportes.models.Ticket;
import com.soportes.dto.TicketHateoasDTO;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TicketMapper {

    public TicketHateoasDTO toHateoasDTO(Ticket ticket) {
        TicketHateoasDTO dto = new TicketHateoasDTO();

        dto.setId(ticket.getId());
        dto.setIdUsuario(ticket.getIdUsuario());
        dto.setTipoTicket(ticket.getTipoTicket());
        dto.setDescripcion(ticket.getDescripcion());
        dto.setEstado(ticket.getEstado());
        dto.setFechaCreacion(ticket.getFechaCreacion());
        dto.setFechaResolucion(ticket.getFechaResolucion());

        // Enlace a sí mismo (ver el ticket actual)
        dto.add(linkTo(methodOn(SoporteController.class).obtenerTicketHateoas(ticket.getId())).withSelfRel());

        // Enlace para ver todos los tickets de ese cliente
        dto.add(linkTo(methodOn(SoporteController.class).obtenerTicketsPorCliente(ticket.getIdUsuario())).withRel("todos"));

        // Enlace para actualizar estado
        dto.add(linkTo(methodOn(SoporteController.class).actualizarEstado(ticket.getId(), null)).withRel("actualizar"));

        return dto;
    }
}
