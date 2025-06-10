package com.vendedores.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sucursales_asignadas")
@Data
public class SucursalAsignada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreSucursal;

    private String direccion;
}
