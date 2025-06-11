package com.ventas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;
    private BigDecimal total;
    private List<DetalleVentaDTO> detalles;
}


