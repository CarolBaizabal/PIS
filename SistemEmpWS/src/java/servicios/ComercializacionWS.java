package servicios;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Cliente;
import modelo.pojos.Comercializacion;
import modelo.pojos.Respuesta;
import modelo.pojos.VentasRemates;
import org.apache.ibatis.session.SqlSession;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@Path("comercializacion")
public class ComercializacionWS {

    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    public ComercializacionWS() {
    }

    @GET
    @Path("getAllComercializacion")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComercializacion() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Comercializacion> list = conn.selectList("Comercializacion.getAllComercializacion");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Comercializacion."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getComercializacionById/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComercializacionById(@PathParam("usuario") String usuario) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Comercializacion comercializacion = conn.selectOne("Comercializacion.getComercializacionById", usuario);
            respuesta = Response.ok(parser.toJson(comercializacion));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Comercializacion."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("comercializacionId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response comercializacionById(@FormParam("usuario") String usuario) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {
            Comercializacion result = conn.selectOne("Comercializacion.getComercializacionById", usuario);
            conn.commit();

            respuesta = Response.ok(new Respuesta(result != null ? "1" : "0"));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @GET
    @Path("buscarComercializacionPorFecha/{fechaCreacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarComercializacionPorFecha(@PathParam("fechaCreacion") String fechaCreacion) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("fechaCreacion", fechaCreacion);
            List<Comercializacion> list = conn.selectList("Comercializacion.buscarComercializacionPorFecha", params);
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @PUT
    @Path("actualizarComercializacion/{idComercializacion}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEgreso(
            @PathParam("idComercializacion") Integer idComercializacion,
            @FormParam("observaciones") String observaciones){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idComercializacion", idComercializacion);
            param.put("observaciones", observaciones);


            conn.update("Comercializacion.actualizarComercializacion", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Comercializacion actualizada correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar la Comercializacion"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
}
