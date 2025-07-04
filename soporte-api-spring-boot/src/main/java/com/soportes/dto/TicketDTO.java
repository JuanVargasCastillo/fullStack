package com.soportes.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class TicketDTO extends RepresentationModel<TicketDTO> {
    private Integer id;
    private Integer idCliente;
    private String tipoTicket;
    private String descripcion;
    private String estado;
}
