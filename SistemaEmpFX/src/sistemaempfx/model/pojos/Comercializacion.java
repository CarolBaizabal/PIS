/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.model.pojos;

/**
 *
 * @author Alicia
 */
public class Comercializacion {
    private Integer idComercializacion;
    private String fechaCreacion;
    private Integer idUsuario;
    private String fechaInicioBusqueda;
    private String fechaFinalBusqueda;
    private String observaciones;
    private String metal;
    private Integer idDetalleComercializacion;

    public Comercializacion() {
    }

    public Comercializacion(Integer idComercializacion, String fechaCreacion, Integer idUsuario, String fechaInicioBusqueda, String fechaFinalBusqueda, String observaciones, String metal, Integer idDetalleComercializacion) {
        this.idComercializacion = idComercializacion;
        this.fechaCreacion = fechaCreacion;
        this.idUsuario = idUsuario;
        this.fechaInicioBusqueda = fechaInicioBusqueda;
        this.fechaFinalBusqueda = fechaFinalBusqueda;
        this.observaciones = observaciones;
        this.metal = metal;
        this.idDetalleComercializacion = idDetalleComercializacion;
    }

    public Integer getIdComercializacion() {
        return idComercializacion;
    }

    public void setIdComercializacion(Integer idComercializacion) {
        this.idComercializacion = idComercializacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFechaInicioBusqueda() {
        return fechaInicioBusqueda;
    }

    public void setFechaInicioBusqueda(String fechaInicioBusqueda) {
        this.fechaInicioBusqueda = fechaInicioBusqueda;
    }

    public String getFechaFinalBusqueda() {
        return fechaFinalBusqueda;
    }

    public void setFechaFinalBusqueda(String fechaFinalBusqueda) {
        this.fechaFinalBusqueda = fechaFinalBusqueda;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public Integer getIdDetalleComercializacion() {
        return idDetalleComercializacion;
    }

    public void setIdDetalleComercializacion(Integer idDetalleComercializacion) {
        this.idDetalleComercializacion = idDetalleComercializacion;
    }
    
    
}
