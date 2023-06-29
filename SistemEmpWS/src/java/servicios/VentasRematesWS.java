/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.DELETE;
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
import modelo.pojos.Catalogo;
import modelo.pojos.Cliente;
import modelo.pojos.Egreso;
import modelo.pojos.Respuesta;
import modelo.pojos.VentasRemates;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Alicia
 */
    
@Path("ventasremates")
public class VentasRematesWS {
    
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public VentasRematesWS() {
    }

    @GET
    @Path("getAllVentasRemates")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVentasRemates() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<VentasRemates> list = conn.selectList("VentasRemates.getAllVentasRemates");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Ventas Remates"));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getVentasRematesById/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEgresoById(@PathParam("usuario") String usuario) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            VentasRemates ventasRemates = conn.selectOne("VentasRemates.getVentasRematesById", usuario);
            respuesta = Response.ok(parser.toJson(ventasRemates));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Ventas Remates"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("ventasRematesId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response egresoById(@FormParam("usuario") String usuario) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("usuario", usuario);

            VentasRemates result = conn.selectOne("VentasRemates.getVentasRematesById", param);
            conn.commit();

            respuesta = Response.ok(new Respuesta(result != null ? "1" : "0"));
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
    
    @DELETE
    @Path("eliminarVentasRemates/{idventasRemates}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarVentasRemates(
            @PathParam("idventasRemates") Integer idventasRemates,
            @FormParam("usuarioCancelar") String usuarioCancelar
    ) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaCancelacion = now.toString();
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idventasRemates", idventasRemates);
            param.put("usuarioCancelar", usuarioCancelar);
            param.put("fechaCancelacion", fechaCancelacion);

            conn.update("VentasRemates.eliminarVentasRemates", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Prenda eliminada correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo eliminar la prenda"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @GET
    @Path("buscarVentasRematesPorFecha/{fechaVenta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVentasRematesPorFecha(@PathParam("fechaVenta") String fechaVenta) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("fechaVenta", fechaVenta);
            List<VentasRemates> list = conn.selectList("VentasRemates.buscarVentasRematesPorFecha", params);
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
    @Path("estatusVentasRemates/{idventasRemates}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response estatusVentasRemates(
            @PathParam("idventasRemates") Integer idventasRemates) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idventasRemates", idventasRemates);

            conn.update("VentasRemates.estatusVentasRemates", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Vendido..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo vender"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
  
    @PUT
    @Path("remateVentasRemates/{idventasRemates}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remateVentasRemates(
            @PathParam("idventasRemates") Integer idventasRemates) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idventasRemates", idventasRemates);

            conn.update("VentasRemates.remateVentasRemates", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("En remate..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo rematar"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

}
