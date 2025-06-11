package com.ventas.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cupon")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cupon {

    @Id
    private String codigo;

    @Column(precision = 5, scale = 2)
    private BigDecimal descuento;

    private boolean activo;
}
