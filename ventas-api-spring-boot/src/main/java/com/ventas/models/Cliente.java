package com.ventas.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
}
