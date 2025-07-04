package com.soportes.mapper;

import com.soportes.models.Ticket;
import com.soportes.dto.TicketHateoasDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

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

        // Base del API Gateway
        String baseUrl = "http://localhost:8888/api/proxy/soporte";

        // Enlaces HATEOAS usando manualmente la URL del Gateway
        dto.add(Link.of(baseUrl + "/hateoas/tickets/" + ticket.getId()).withSelfRel());
        dto.add(Link.of(baseUrl + "/tickets/cliente/" + ticket.getIdUsuario()).withRel("todos"));
        dto.add(Link.of(baseUrl + "/tickets/" + ticket.getId() + "/estado").withRel("actualizar"));

        return dto;
    }
}
