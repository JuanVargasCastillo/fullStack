package com.vendedores.dto;

import com.vendedores.models.SucursalAsignada;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendedorHateoasDTO extends RepresentationModel<VendedorHateoasDTO> {
    private Integer idVendedor;
    private Integer idUsuario;
    private String nombreCompleto;
    private String rut;
    private String direccion;
    private String telefono;
    private SucursalAsignada sucursalAsignada;
}
