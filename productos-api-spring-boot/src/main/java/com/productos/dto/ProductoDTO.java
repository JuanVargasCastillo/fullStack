package com.productos.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer id;
    private String nombre;
    private String descripcion;
    private Double precioUnitario;
    private String categoria;
    private Boolean activo;
}