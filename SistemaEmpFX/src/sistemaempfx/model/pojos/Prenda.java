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
public class Prenda {
    private Integer idPrendas;
   private String nombre;
   private Integer idEmp;
   private String categoria;
   private Integer numPiezas;
   private String serie;
   private String modelo;
   private String subcategoria;
   private String descripcion;
   private String metal;
   private Float peso;
   private Float kilataje;
   private Float prestamo;
   private Float precioComercializacion;
   private Float precioVenta;
   private String estatus;
   private Integer idComercializacion;
   private String fechaCreacion;
   private String fechaComercializacion;
   private String fechaVenta;
   private String usuario;

    public Prenda() {
    }

    public Prenda(Integer idPrendas, String nombre, Integer idEmp, String categoria, Integer numPiezas, String serie, String modelo, String subcategoria, String descripcion, String metal, Float peso, Float kilataje, Float prestamo, Float precioComercializacion, Float precioVenta, String estatus, Integer idComercializacion, String fechaCreacion, String fechaComercializacion, String fechaVenta, String usuario) {
        this.idPrendas = idPrendas;
        this.nombre = nombre;
        this.idEmp = idEmp;
        this.categoria = categoria;
        this.numPiezas = numPiezas;
        this.serie = serie;
        this.modelo = modelo;
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
        this.metal = metal;
        this.peso = peso;
        this.kilataje = kilataje;
        this.prestamo = prestamo;
        this.precioComercializacion = precioComercializacion;
        this.precioVenta = precioVenta;
        this.estatus = estatus;
        this.idComercializacion = idComercializacion;
        this.fechaCreacion = fechaCreacion;
        this.fechaComercializacion = fechaComercializacion;
        this.fechaVenta = fechaVenta;
        this.usuario = usuario;
    }

    public Integer getIdPrendas() {
        return idPrendas;
    }

    public void setIdPrendas(Integer idPrendas) {
        this.idPrendas = idPrendas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getNumPiezas() {
        return numPiezas;
    }

    public void setNumPiezas(Integer numPiezas) {
        this.numPiezas = numPiezas;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getKilataje() {
        return kilataje;
    }

    public void setKilataje(Float kilataje) {
        this.kilataje = kilataje;
    }

    public Float getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Float prestamo) {
        this.prestamo = prestamo;
    }

    public Float getPrecioComercializacion() {
        return precioComercializacion;
    }

    public void setPrecioComercializacion(Float precioComercializacion) {
        this.precioComercializacion = precioComercializacion;
    }

    public Float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

    public String getFechaComercializacion() {
        return fechaComercializacion;
    }

    public void setFechaComercializacion(String fechaComercializacion) {
        this.fechaComercializacion = fechaComercializacion;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
}
