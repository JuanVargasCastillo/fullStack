package com.reportes.controllers;

import com.reportes.dto.ReporteDTO;
import com.reportes.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

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
}
