package com.clientes.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Integer idCliente;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
}