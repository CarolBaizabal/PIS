package modelo.pojos;

/**
 *
 * @author Carol Celina Pacheco
 */
public class Cliente {
    private Integer idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String calle;
    private String cp;
    private String colonia;
    private String municipio;
    private String estado;
    private String idemex;
    private String fechaNacimiento;
    private String curp;
    private String rfc;
    private String telefono;
    private String correo;
    private String estatus;
    private String comentarios;
    private String sexo;
    private String fechaCreacion;
    private String usuario;
    private String fechaActualizacion; 
    private String usuarioA;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String nombre, String apellidoPaterno, String apellidoMaterno, String calle, String cp, String colonia, String municipio, String estado, String idemex, String fechaNacimiento, String curp, String rfc, String telefono, String correo, String estatus, String comentarios, String sexo, String fechaCreacion, String usuario, String fechaActualizacion, String usuarioA) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.calle = calle;
        this.cp = cp;
        this.colonia = colonia;
        this.municipio = municipio;
        this.estado = estado;
        this.idemex = idemex;
        this.fechaNacimiento = fechaNacimiento;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.correo = correo;
        this.estatus = estatus;
        this.comentarios = comentarios;
        this.sexo = sexo;
        this.fechaCreacion = fechaCreacion;
        this.usuario = usuario;
        this.fechaActualizacion = fechaActualizacion;
        this.usuarioA = usuarioA;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdemex() {
        return idemex;
    }

    public void setIdemex(String idemex) {
        this.idemex = idemex;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getUsuarioA() {
        return usuarioA;
    }

    public void setUsuarioA(String usuarioA) {
        this.usuarioA = usuarioA;
    }
    
    
}
