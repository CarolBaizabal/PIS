/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.model.pojos;

public class Refrendo {
     private Integer idRefrendo;
    private Integer idEmp;
    private Integer idContrato;
    private String fechaCreacion;
    private String usuario;
    private Float interes;
    private Float almacenaje;
    private Float subtotal;
    private Float iva;
    private Float total;
    private String estatus;

    public Refrendo() {
    }

    public Refrendo(Integer idRefrendo, Integer idEmp, Integer idContrato, String fechaCreacion, String usuario, Float interes, Float almacenaje, Float subtotal, Float iva, String estatus) {
        this.idRefrendo = idRefrendo;
        this.idEmp = idEmp;
        this.idContrato = idContrato;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.interes = interes;
        this.almacenaje = almacenaje;
        this.subtotal = subtotal;
        this.iva = iva;
        this.estatus = estatus;
    }

    public Refrendo(Integer idRefrendo, Integer idEmp, Integer idContrato, String fechaCreacion, String usuario, Float interes, Float almacenaje, Float subtotal, Float iva, Float total, String estatus) {
        this.idRefrendo = idRefrendo;
        this.idEmp = idEmp;
        this.idContrato = idContrato;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.interes = interes;
        this.almacenaje = almacenaje;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estatus = estatus;
    }
    
    

    public Integer getIdRefrendo() {
        return idRefrendo;
    }

    public void setIdRefrendo(Integer idRefrendo) {
        this.idRefrendo = idRefrendo;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
    
}

