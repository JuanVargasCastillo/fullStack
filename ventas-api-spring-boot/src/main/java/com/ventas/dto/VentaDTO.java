package com.ventas.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

public class VentaDTO {

@Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VentaRequestDTO {
        private Long clienteId;
        private List<DetalleVentaDTO> detalles;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VentaResponseDTO {
        private Long id;
        private LocalDate fecha;
        private ClienteDTO cliente;
        private Double total;
        private List<DetalleVentaDTO> detalles;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetalleVentaDTO {
        private Long productoId;
        private Integer cantidad;
        private Double precioUnitario;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClienteDTO {
        private Long id;
        private String nombre;
        private String email;
    }
}
