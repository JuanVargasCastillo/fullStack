package com.inventarios.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {
    private Long idProducto;
    private Integer stockDisponible;
    private String ubicacionBodega;
}
