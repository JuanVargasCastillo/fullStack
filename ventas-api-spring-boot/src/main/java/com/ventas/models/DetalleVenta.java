package com.ventas.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Venta venta;

    @ManyToOne
    private Producto producto;

    private Integer cantidad;
    private Double precioUnitario;
}
