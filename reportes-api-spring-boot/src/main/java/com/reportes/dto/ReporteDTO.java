package com.reportes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
    private Integer id;
    private String tipoReporte;
    private LocalDate fechaGeneracion;
    private String descripcion;
    private String jsonDatos;
    private Boolean activo;
    private String categoria;
    private String nombre;
    private Double precioUnitario;
}
