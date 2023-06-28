/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pojos;

import java.util.Date;

    public class VentasRemates {
    private Integer idVentasRemates;
    private Float subtotal;
    private Float iva;
    private Float total;
    private String estatus;
    private String cliente;
    private String usuario;
    private String fechaVenta;
    private Integer totalPrendas;
    private String fechaCancelacion;
    private String usuarioCancelar;
    private String tipo;

    public VentasRemates() {
    }

    public VentasRemates(Integer idVentasRemates, Float subtotal, Float iva, Float total, String estatus, String cliente, String usuario, String fechaVenta, Integer totalPrendas, String fechaCancelacion, String usuarioCancelar, String tipo) {
        this.idVentasRemates = idVentasRemates;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.estatus = estatus;
        this.cliente = cliente;
        this.usuario = usuario;
        this.fechaVenta = fechaVenta;
        this.totalPrendas = totalPrendas;
        this.fechaCancelacion = fechaCancelacion;
        this.usuarioCancelar = usuarioCancelar;
        this.tipo = tipo;
    }

    public Integer getIdVentasRemates() {
        return idVentasRemates;
    }

    public void setIdVentasRemates(Integer idVentasRemates) {
        this.idVentasRemates = idVentasRemates;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getTotalPrendas() {
        return totalPrendas;
    }

    public void setTotalPrendas(Integer totalPrendas) {
        this.totalPrendas = totalPrendas;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public String getUsuarioCancelar() {
        return usuarioCancelar;
    }

    public void setUsuarioCancelar(String usuarioCancelar) {
        this.usuarioCancelar = usuarioCancelar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
}