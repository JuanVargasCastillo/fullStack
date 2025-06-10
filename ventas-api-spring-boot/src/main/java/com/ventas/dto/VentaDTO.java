package com.ventas.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

public class VentaDTO {
    private Integer idCliente;
    private Integer idVendedor;
    private Date fechaVenta;
    private BigDecimal total;
    private List<DetalleVentaDTO> detalles;
}

