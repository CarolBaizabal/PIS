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
public class Contrato {

    private Integer idContrato;
    private Integer idEmp;
    private String fechaCreacion;
    private String fechaActualizacion;
    private String fechaLimiteRefrendo;
    private String FechaComercializacion;
    private Float importePrestamo;
    private String estatus;
    private Integer idContratoAnterior;
    private Integer idContratoSiguiente;
    private String fechaCreacionActual;
    private String fechaComercializacionActual;
    private String fechaCancelacion;
    private Integer idUsuario;
    private String observaciones;
    private Integer idRefrendo;
    private Integer idFiniquito;
    private Integer idAumentoEspera;

    public Contrato() {
    }

    public Contrato(Integer idContrato, Integer idEmp, String fechaCreacion, String fechaActualizacion, String fechaLimiteRefrendo, String FechaComercializacion, Float importePrestamo, String estatus, Integer idContratoAnterior, Integer idContratoSiguiente, String fechaCreacionActual, String fechaComercializacionActual, String fechaCancelacion, Integer idUsuario, String observaciones, Integer idRefrendo, Integer idFiniquito, Integer idAumentoEspera) {
        this.idContrato = idContrato;
        this.idEmp = idEmp;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.fechaLimiteRefrendo = fechaLimiteRefrendo;
        this.FechaComercializacion = FechaComercializacion;
        this.importePrestamo = importePrestamo;
        this.estatus = estatus;
        this.idContratoAnterior = idContratoAnterior;
        this.idContratoSiguiente = idContratoSiguiente;
        this.fechaCreacionActual = fechaCreacionActual;
        this.fechaComercializacionActual = fechaComercializacionActual;
        this.fechaCancelacion = fechaCancelacion;
        this.idUsuario = idUsuario;
        this.observaciones = observaciones;
        this.idRefrendo = idRefrendo;
        this.idFiniquito = idFiniquito;
        this.idAumentoEspera = idAumentoEspera;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getFechaLimiteRefrendo() {
        return fechaLimiteRefrendo;
    }

    public void setFechaLimiteRefrendo(String fechaLimiteRefrendo) {
        this.fechaLimiteRefrendo = fechaLimiteRefrendo;
    }

    public String getFechaComercializacion() {
        return FechaComercializacion;
    }

    public void setFechaComercializacion(String FechaComercializacion) {
        this.FechaComercializacion = FechaComercializacion;
    }

    public Float getImportePrestamo() {
        return importePrestamo;
    }

    public void setImportePrestamo(Float importePrestamo) {
        this.importePrestamo = importePrestamo;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getIdContratoAnterior() {
        return idContratoAnterior;
    }

    public void setIdContratoAnterior(Integer idContratoAnterior) {
        this.idContratoAnterior = idContratoAnterior;
    }

    public Integer getIdContratoSiguiente() {
        return idContratoSiguiente;
    }

    public void setIdContratoSiguiente(Integer idContratoSiguiente) {
        this.idContratoSiguiente = idContratoSiguiente;
    }

    public String getFechaCreacionActual() {
        return fechaCreacionActual;
    }

    public void setFechaCreacionActual(String fechaCreacionActual) {
        this.fechaCreacionActual = fechaCreacionActual;
    }

    public String getFechaComercializacionActual() {
        return fechaComercializacionActual;
    }

    public void setFechaComercializacionActual(String fechaComercializacionActual) {
        this.fechaComercializacionActual = fechaComercializacionActual;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getIdRefrendo() {
        return idRefrendo;
    }

    public void setIdRefrendo(Integer idRefrendo) {
        this.idRefrendo = idRefrendo;
    }

    public Integer getIdFiniquito() {
        return idFiniquito;
    }

    public void setIdFiniquito(Integer idFiniquito) {
        this.idFiniquito = idFiniquito;
    }

    public Integer getIdAumentoEspera() {
        return idAumentoEspera;
    }

    public void setIdAumentoEspera(Integer idAumentoEspera) {
        this.idAumentoEspera = idAumentoEspera;
    }

    
}
