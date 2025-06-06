package com.gestion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CrearUsuarioRequest {
    private String nombreUsuario;
    private String email;
    @JsonProperty("contrasena")
    private String contrasena;
    private String rol;
    private String estado;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
    private String areaVentas;
}
