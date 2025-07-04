package com.reportes.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.LocalDate;

@Entity
@Table(name = "reportes")
public class Reporte {

    @Id
    @Column(name = "id_reporte")
    private Integer idReporte;

    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @Column(name = "fecha_generacion")
    private LocalDate fechaGeneracion;

    private String descripcion;

    @Column(name = "json_datos")
    private String jsonDatos;

    // Getters y setters

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getJsonDatos() {
        return jsonDatos;
    }

    public void setJsonDatos(String jsonDatos) {
        this.jsonDatos = jsonDatos;
    }
}
