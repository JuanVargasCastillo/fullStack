package com.reportes.services;

import com.reportes.dto.ReporteDTO;
import com.reportes.models.Reporte;
import com.reportes.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    private ReporteDTO toDTO(Reporte r) {
        return new ReporteDTO(
            r.getId(),
            r.getTipoReporte(),
            r.getFechaGeneracion(),
            r.getDescripcion(),
            r.getJsonDatos(),
            r.getActivo(),
            r.getCategoria(),
            r.getNombre(),
            r.getPrecioUnitario()
        );
    }

    public List<ReporteDTO> obtenerVentasPorPeriodo() {
        return reporteRepository.findByTipoReporte("ventas").stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteDTO> obtenerTopVendedores() {
        return reporteRepository.findByTipoReporte("top_vendedores").stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReporteDTO> obtenerInventarioCritico() {
        return reporteRepository.findByTipoReporte("inventario_critico").stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
