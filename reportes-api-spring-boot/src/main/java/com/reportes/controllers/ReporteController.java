package com.reportes.controllers;

import com.reportes.dto.ReporteDTO;
import com.reportes.dto.ReporteHateoasDTO;
import com.reportes.models.Reporte;
import com.reportes.services.ReporteService;
import com.reportes.mapper.ReporteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ReporteMapper reporteMapper;

    // ✅ Endpoints normales
    @GetMapping("/ventas/periodo")
    public List<ReporteDTO> getVentasPorPeriodo() {
        return reporteService.obtenerVentasPorPeriodo();
    }

    @GetMapping("/vendedores/top")
    public List<ReporteDTO> getTopVendedores() {
        return reporteService.obtenerTopVendedores();
    }

    @GetMapping("/inventario/critico")
    public List<ReporteDTO> getInventarioCritico() {
        return reporteService.obtenerInventarioCritico();
    }

    // ✅ Endpoints con HATEOAS
    @GetMapping("/hateoas/todos")
    public List<ReporteHateoasDTO> getTodosHateoas() {
        return reporteService.obtenerTodos()
                .stream()
                .map(reporteMapper::toHateoasDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/hateoas/{id}")
    public ReporteHateoasDTO getReportePorId(@PathVariable Integer id) {
        Reporte reporte = reporteService.obtenerPorId(id);
        return reporteMapper.toHateoasDTO(reporte);
    }
}
