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
public class CatalogoPrenda {

    private Integer idCatalogo;
    private String nombre;
    private String categoria;
    private String estatus;
    private Integer idCategoria;

    public CatalogoPrenda() {
    }

    public CatalogoPrenda(Integer idCatalogo, String nombre, String categoria, String estatus) {
        this.idCatalogo = idCatalogo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estatus = estatus;
    }

    public CatalogoPrenda(Integer idCatalogo, String nombre, String categoria, String estatus, Integer idCategoria) {
        this.idCatalogo = idCatalogo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estatus = estatus;
        this.idCategoria = idCategoria;
    }

    public Integer getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(Integer idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
