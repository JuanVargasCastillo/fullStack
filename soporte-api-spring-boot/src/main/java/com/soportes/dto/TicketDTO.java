package com.soportes.dto;

import lombok.Data;

@Data
public class TicketDTO {
    private Integer idCliente;
    private Integer tipoSoporteId;
    private String descripcion;
}
