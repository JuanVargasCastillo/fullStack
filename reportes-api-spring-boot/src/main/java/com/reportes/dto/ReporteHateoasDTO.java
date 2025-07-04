package com.reportes.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReporteHateoasDTO extends RepresentationModel<ReporteHateoasDTO> {
    private Integer idReporte;
    private String tipoReporte;
    private LocalDate fechaGeneracion;
    private String descripcion;
    private String jsonDatos;
}
