/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Alicia
 */
@Path("contrato")
public class ContratoWS {
    
@Context
    private UriInfo context;
    private Gson parser = new Gson();

    public ContratoWS() {
    }

@POST
@Path("registrarContrato")
@Produces(MediaType.APPLICATION_JSON)
public Response registrarContrato(
        @FormParam("idEmp") Integer idEmp,
        @FormParam("fechaCreacion") String fechaCreacion,
        @FormParam("fechaActualizacion") String fechaActualizacion,
        @FormParam("fechaLimiteRefrendo") String fechaLimiteRefrendo,
        @FormParam("FechaComercializacion") String FechaComercializacion,
        @FormParam("importePrestamo") Float importePrestamo,
        @FormParam("estatus") String estatus,
        @FormParam("idContratoAnterior") Integer idContratoAnterior,
        @FormParam("idContratoSiguiente") Integer idContratoSiguiente,
        @FormParam("fechaCreacionActual") String fechaCreacionActual,
        @FormParam("fechaComercializacionActual") String fechaComercializacionActual,
        @FormParam("fechaCancelacion") String fechaCancelacion,
        @FormParam("idUsuario") Integer idUsuario,
        @FormParam("observaciones") String observaciones,
        @FormParam("idRefrendo") Integer idRefrendo,
        @FormParam("idFiniquito") Integer idFiniquito,
        @FormParam("idAumentoEspera") Integer idAumentoEspera) {

    Response.ResponseBuilder respuesta = null;
    SqlSession conn = MyBatisUtil.getSession();

    try {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("idEmp", idEmp);
        param.put("fechaCreacion", fechaCreacion);
        param.put("fechaActualizacion", fechaActualizacion);
        param.put("fechaLimiteRefrendo", fechaLimiteRefrendo);
        param.put("FechaComercializacion", FechaComercializacion);
        param.put("importePrestamo", importePrestamo);
        param.put("estatus", estatus);
        param.put("idContratoAnterior", idContratoAnterior);
        param.put("idContratoSiguiente", idContratoSiguiente);
        param.put("fechaCreacionActual", fechaCreacionActual);
        param.put("fechaComercializacionActual", fechaComercializacionActual);
        param.put("fechaCancelacion", fechaCancelacion);
        param.put("idUsuario", idUsuario);
        param.put("observaciones", observaciones);
        param.put("idRefrendo", idRefrendo);
        param.put("idFiniquito", idFiniquito);
        param.put("idAumentoEspera", idAumentoEspera);

        conn.insert("Contrato.registrarContrato", param);
        conn.commit();
        respuesta = Response.ok(new Respuesta("Se registró correctamente..."));
    } catch (Exception ex) {
        conn.rollback();
        ex.printStackTrace();
        respuesta = Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new Respuesta("No se pudo registrar "));
    } finally {
        conn.close();
    }
    return respuesta.build();
}

}