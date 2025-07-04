package com.inventarios.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioHateoasDTO extends RepresentationModel<InventarioHateoasDTO> {
    private Long idInventario;
    private Long idProducto;
    private Integer stockDisponible;
    private String ubicacionBodega;
}