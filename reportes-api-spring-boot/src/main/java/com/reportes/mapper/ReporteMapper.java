package com.reportes.mapper;

import com.reportes.controllers.ReporteController;
import com.reportes.dto.ReporteHateoasDTO;
import com.reportes.models.Reporte;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ReporteMapper {

    public ReporteHateoasDTO toHateoasDTO(Reporte reporte) {
        ReporteHateoasDTO dto = new ReporteHateoasDTO();
        dto.setIdReporte(reporte.getIdReporte());
        dto.setTipoReporte(reporte.getTipoReporte());
        dto.setFechaGeneracion(reporte.getFechaGeneracion());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setJsonDatos(reporte.getJsonDatos());

        // Agregar enlaces según tipo de reporte
        dto.add(linkTo(methodOn(ReporteController.class).getReportePorId(reporte.getIdReporte())).withSelfRel());

        return dto;
    }
}
