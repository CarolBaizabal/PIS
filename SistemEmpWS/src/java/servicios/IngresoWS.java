/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
import modelo.pojos.Ingreso;
import modelo.pojos.Respuesta;
import modelo.pojos.Rol;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Alicia
 */
@Path("ingreso")
public class IngresoWS {
    
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public IngresoWS() {
    }

    @GET
    @Path("getAllIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngreso() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("Ingreso.getAllIngreso");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Ingreso."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getIngresoById/{motivo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIngresoById(@PathParam("motivo") String motivo) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Ingreso ingreso = conn.selectOne("Ingreso.getIngresoById", motivo);
            respuesta = Response.ok(parser.toJson(ingreso));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Ingreso."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("ingresoId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ingresoById(@FormParam("motivo") String motivo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("motivo", motivo);

            Ingreso result = conn.selectOne("Ingreso.getIngresoById", param);
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
    
    @GET
    @Path("getAllIngresoActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIngresoActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Ingreso> list = conn.selectList("Ingreso.getAllIngresoActivo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Ingreso"));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
    
    @GET
    @Path("buscarIngreso/{motivo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarIngresoByNombre(@PathParam("motivo") String motivo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Ingreso> list = conn.selectList("Ingreso.buscarIngresoPorNombre", motivo);
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
    
    @POST
    @Path("registrarIngreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarIngreso(
            @PathParam("idIngreso") Integer idIngreso,
            @FormParam("cantidad") Integer cantidad,            
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("usuario") String usuario) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaCreacion = now.toString();
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idIngreso", idIngreso);
            param.put("cantidad", cantidad);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("usuario", usuario);
            param.put("fechaCreacion", fechaCreacion);
            

            conn.insert("Ingreso.registrarIngreso", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Se registró correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo registrar "));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @PUT
    @Path("actualizarIngreso/{idIngreso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarIngreso(
            @PathParam("idIngreso") Integer idIngreso,
            @FormParam("cantidad") Integer cantidad,
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("usuarioA") String usuarioA){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaModificacion = now.toString();
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idIngreso", idIngreso);
            param.put("cantidad", cantidad);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("fechaModificacion", fechaModificacion);
            param.put("usuarioA", usuarioA);


            conn.update("Ingreso.actualizarIngreso", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Ingreso actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el Ingreso"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @DELETE
    @Path("eliminarIngreso/{idIngreso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarIngreso(
            @PathParam("idIngreso") Integer idIngreso
    ) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idIngreso", idIngreso);

            conn.update("Ingreso.eliminarIngreso", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Ingreso eliminado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo eliminar el ingreso"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
}
