package com.reportes.repository;

import com.reportes.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    List<Reporte> findByTipoReporte(String tipoReporte);
}
