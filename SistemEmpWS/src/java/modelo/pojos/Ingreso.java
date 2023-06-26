package modelo.pojos;

public class Ingreso {
    private Integer idIngreso;
    private Integer cantidad;
    private String motivo;
    private String observaciones;
    private String fechaCreacion;
    private String usuario;
    private String fechaModificacion;
    private String usuarioA;
    private Integer idCategoria;
    private String estatus;

    public Ingreso() {
    }

    public Ingreso(Integer idIngreso, Integer cantidad, String motivo, String observaciones, String fechaCreacion, String usuario, String fechaModificacion, String usuarioA, Integer idCategoria, String estatus) {
        this.idIngreso = idIngreso;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.observaciones = observaciones;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.fechaModificacion = fechaModificacion;
        this.usuarioA = usuarioA;
        this.idCategoria = idCategoria;
        this.estatus = estatus;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
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

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsuarioA() {
        return usuarioA;
    }

    public void setUsuarioA(String usuarioA) {
        this.usuarioA = usuarioA;
    }
    
    @Override
    public String toString() {
        return motivo;
    }
    
    
}
