/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.model.pojos;

/**
 *
 * @author Carol Celina Pacheco
 */
public class Rol {
    private Integer idRol;
    private String nombreRol;

    public Rol() {
    }

    public Rol(Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
       return this.nombreRol;
    }
  
    
}
