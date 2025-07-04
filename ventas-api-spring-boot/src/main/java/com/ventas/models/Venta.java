package com.ventas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ventas")  // plural para que coincida con la tabla
public class Venta {

    @Id
    @Column(name = "id_venta")  // coincide con la columna PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idventa;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @Column(name = "total", precision = 10, scale = 2)
    private BigDecimal total;
}
