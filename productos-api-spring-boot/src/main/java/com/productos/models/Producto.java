package com.productos.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    private String nombre;
    private String descripcion;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    private String categoria;
    private Boolean activo;
}