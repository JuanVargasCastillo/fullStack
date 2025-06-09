package com.ventas.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Venta")
    private Integer id;

    private String nombre;
    private String descripcion;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    private String categoria;
    private Boolean activo;
}