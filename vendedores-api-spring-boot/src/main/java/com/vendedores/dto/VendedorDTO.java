package com.vendedores.dto;

import com.vendedores.models.SucursalAsignada;
import lombok.Data;

@Data
public class VendedorDTO {
    private Integer idVendedor;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;

    private SucursalAsignada sucursalAsignada; // ✅ campo agregado
}
