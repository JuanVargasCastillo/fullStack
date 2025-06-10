package com.ventas.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idventa;

    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Column(name = "fecha_venta")
    @Temporal(TemporalType.DATE)
    private Date fechaVenta;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;
}
