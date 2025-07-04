package com.ventas.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO extends RepresentationModel<VentaDTO> {
    private Long idVenta; // <--- Este campo estaba faltando en el constructor
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;
    private BigDecimal total;
    private List<DetalleVentaDTO> detalles;
}

