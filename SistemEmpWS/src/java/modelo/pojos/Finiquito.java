/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import java.util.Date;

/**
 *
 * @author Alicia
 */
public class Finiquito {
    private Integer idFiniquito;
    private Integer idEmp;
    private Integer idContrato;
    private Date fechaCreacion;
    private String usuario;
    private Float interes;
    private Float importeAlmacenaje;
    private Float subtotal;
    private Float iva;
    private Float total;

    public Finiquito() {
    }

    public Finiquito(Integer idFiniquito, Integer idEmp, Integer idContrato, Date fechaCreacion, String usuario, Float interes, Float importeAlmacenaje, Float subtotal, Float iva, Float total) {
        this.idFiniquito = idFiniquito;
        this.idEmp = idEmp;
        this.idContrato = idContrato;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.interes = interes;
        this.importeAlmacenaje = importeAlmacenaje;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
    }

    public Integer getIdFiniquito() {
        return idFiniquito;
    }

    public void setIdFiniquito(Integer idFiniquito) {
        this.idFiniquito = idFiniquito;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Float getInteres() {
        return interes;
    }

    public void setInteres(Float interes) {
        this.interes = interes;
    }

    public Float getImporteAlmacenaje() {
        return importeAlmacenaje;
    }

    public void setImporteAlmacenaje(Float importeAlmacenaje) {
        this.importeAlmacenaje = importeAlmacenaje;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
    
}
