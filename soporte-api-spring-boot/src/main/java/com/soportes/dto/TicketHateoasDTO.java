package com.soportes.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
public class TicketHateoasDTO extends RepresentationModel<TicketHateoasDTO> {
    private Integer id;
    private Integer idUsuario;
    private String tipoTicket;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaResolucion;
}
