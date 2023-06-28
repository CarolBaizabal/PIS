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
public class Empe {
        private Integer idEmp;
    private Integer idCliente;
    private String fechaCreacion;
    private String observaciones;
    private String usuario;
    private Integer idContrato;
    private String fechaActualizacion;
    private Float interesPorcentaje;
    private Float interes;
    private Float almacenaje;
    private Float almacenajePorcentaje;
    private Integer periodos;
    private Integer diasPeriodos;
    private Float iva;
    private Float tasaComercializaion;
    private String estatus;

    public Empe() {
    }

    public Empe(Integer idEmp, Integer idCliente, String fechaCreacion, String observaciones, String usuario, Integer idContrato, String fechaActualizacion, Float interes, Float almacenaje, Integer periodos, Integer diasPeriodos, Float iva, Float tasaComercializaion, String estatus) {
        this.idEmp = idEmp;
        this.idCliente = idCliente;
        this.fechaCreacion = fechaCreacion;
        this.observaciones = observaciones;
        this.usuario = usuario;
        this.idContrato = idContrato;
        this.fechaActualizacion = fechaActualizacion;
        this.interes = interes;
        this.almacenaje = almacenaje;
        this.periodos = periodos;
        this.diasPeriodos = diasPeriodos;
        this.iva = iva;
        this.tasaComercializaion = tasaComercializaion;
        this.estatus=estatus;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public Float getAlmacenaje() {
        return almacenaje;
    }

    public void setAlmacenaje(Float almacenaje) {
        this.almacenaje = almacenaje;
    }

    public Integer getPeriodos() {
        return periodos;
    }

    public void setPeriodos(Integer periodos) {
        this.periodos = periodos;
    }

    public Integer getDiasPeriodos() {
        return diasPeriodos;
    }

    public void setDiasPeriodos(Integer diasPeriodos) {
        this.diasPeriodos = diasPeriodos;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getTasaComercializaion() {
        return tasaComercializaion;
    }

    public void setTasaComercializaion(Float tasaComercializaion) {
        this.tasaComercializaion = tasaComercializaion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Float getInteresPorcentaje() {
        return interesPorcentaje;
    }

    public void setInteresPorcentaje(Float interesPorcentaje) {
        this.interesPorcentaje = interesPorcentaje;
    }

    public Float getAlmacenajePorcentaje() {
        return almacenajePorcentaje;
    }

    public void setAlmacenajePorcentaje(Float almacenajePorcentaje) {
        this.almacenajePorcentaje = almacenajePorcentaje;
    }
    
    
}
