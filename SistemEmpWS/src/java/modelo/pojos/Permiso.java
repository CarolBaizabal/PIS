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
public class Permiso {
    private Integer idPermiso;
    private String rol;
    private Integer idRol;
    private Boolean estatus;
    private String permiso;
    private Integer clavePermiso;

    public Permiso() {
    }

    public Permiso(Integer idPermiso, String rol, Integer idRol, Boolean estatus, String permiso, Integer clavePermiso) {
        this.idPermiso = idPermiso;
        this.rol = rol;
        this.idRol = idRol;
        this.estatus = estatus;
        this.permiso = permiso;
        this.clavePermiso = clavePermiso;
    }

    public Integer getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Integer idPermiso) {
        this.idPermiso = idPermiso;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(Boolean estatus) {
        this.estatus = estatus;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    public Integer getClavePermiso() {
        return clavePermiso;
    }

    public void setClavePermiso(Integer clavePermiso) {
        this.clavePermiso = clavePermiso;
    }
    
    
}
