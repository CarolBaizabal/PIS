/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

/**
 *
 * @author Alicia
 */
public class Comercializacion {
      private Integer idComercializacion;
    private String fechaCreacion;
    private String usuario;
    private String fechaInicioBusqueda;
    private String fechaFinalBusqueda;
    private String observaciones;
    private String metal;
    private Integer idContrato;
    private Integer idEmp;
    private String prenda;
    private Float precioComercializacion;

    public Comercializacion() {
    }

    public Comercializacion(Integer idComercializacion, String fechaCreacion, String usuario, String fechaInicioBusqueda, String fechaFinalBusqueda, String observaciones, String metal, Integer idContrato, Integer idEmp, String prenda, Float precioComercializacion) {
        this.idComercializacion = idComercializacion;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.fechaInicioBusqueda = fechaInicioBusqueda;
        this.fechaFinalBusqueda = fechaFinalBusqueda;
        this.observaciones = observaciones;
        this.metal = metal;
        this.idContrato = idContrato;
        this.idEmp = idEmp;
        this.prenda = prenda;
        this.precioComercializacion = precioComercializacion;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public String getPrenda() {
        return prenda;
    }

    public void setPrenda(String prenda) {
        this.prenda = prenda;
    }

    public Float getPrecioComercializacion() {
        return precioComercializacion;
    }

    public void setPrecioComercializacion(Float precioComercializacion) {
        this.precioComercializacion = precioComercializacion;
    }

   
}
