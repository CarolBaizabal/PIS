/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
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
import modelo.pojos.Egreso;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Alicia
 */
@Path("egreso")
public class EgresoWS {
    
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public EgresoWS() {
    }

    @GET
    @Path("getAllEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEgreso() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Egreso> list = conn.selectList("Egreso.getAllEgreso");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Egreso."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
    
    @POST
    @Path("getAllEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEgresoPorFiltros(
            @FormParam("fecha") String fecha,
            @FormParam("motivo") String motivo
    ) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            HashMap<String, Object> param = new HashMap<>();
            param.put("fecha", fecha);
            param.put("motivo", motivo);
            List<Egreso> list = conn.selectList("Egreso.busquedaEgresoActivo",param);
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Egreso."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getEgresoById/{motivo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEgresoById(@PathParam("motivo") String motivo) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Egreso egreso = conn.selectOne("Egreso.getEgresoById", motivo);
            respuesta = Response.ok(parser.toJson(egreso));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Egreso."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("egresoId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response egresoById(@FormParam("motivo") String motivo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("motivo", motivo);

            Egreso result = conn.selectOne("Egreso.getEgresoById", param);
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
    @Path("buscarEgreso/{motivo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEgresoByNombre(@PathParam("motivo") String motivo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Egreso> list = conn.selectList("Egreso.buscarEgresoPorNombre", motivo);
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
    
    @GET
    @Path("getAllEgresoActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEgresoActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Egreso> list = conn.selectList("Egreso.getAllEgresoActivo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Egreso"));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
    
    @POST
    @Path("registrarEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEgreso(
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
            param.put("cantidad", cantidad);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("fechaCreacion", fechaCreacion);
            param.put("usuario", usuario);
            

            conn.insert("Egreso.registrarEgreso", param);
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
    @Path("actualizarEgreso/{idEgreso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEgreso(
            @PathParam("idEgreso") Integer idEgreso,
            @FormParam("cantidad") Integer cantidad,
            @FormParam("motivo") String motivo,
            @FormParam("observaciones") String observaciones,
            @FormParam("usuarioA") String usuarioA){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaModificacion = now.toString(); 
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idEgreso", idEgreso);
            param.put("cantidad", cantidad);
            param.put("motivo", motivo);
            param.put("observaciones", observaciones);
            param.put("fechaModificacion", fechaModificacion);
            param.put("usuarioA", usuarioA);


            conn.update("Egreso.actualizarEgreso", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Egreso actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el Egreso"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @DELETE
    @Path("eliminarEgreso/{idEgreso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarEgreso(
            @PathParam("idEgreso") Integer idEgreso
    ) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idEgreso", idEgreso);

            conn.update("Egreso.eliminarEgreso", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Egreso eliminado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo eliminar el egreso"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

}
