package com.inventarios.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible;

    @Column(name = "ubicacion_bodega", nullable = false)
    private String ubicacionBodega;
}
