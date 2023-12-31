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
public class Egreso {
    private Integer idEgreso;
    private Integer cantidad;
    private String motivo;
    private String observaciones;
    private String fechaCreacion;
    private String usuario;
    private String fechaModificacion;
    private String usuarioA;
    private String estatus;

    public Egreso() {
    }

    public Egreso(Integer idEgreso, Integer cantidad, String motivo, String observaciones, String fechaCreacion, String usuario, String fechaModificacion, String usuarioA, String estatus) {
        this.idEgreso = idEgreso;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.fechaModificacion = fechaModificacion;
        this.usuarioA = usuarioA;
        this.estatus = estatus;
    }

    public Integer getIdEgreso() {
        return idEgreso;
    }

    public void setIdEgreso(Integer idEgreso) {
        this.idEgreso = idEgreso;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getUsuarioA() {
        return usuarioA;
    }

    public void setUsuarioA(String usuarioA) {
        this.usuarioA = usuarioA;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    
    @Override
    public String toString() {
        return motivo;
    }
    
    
}