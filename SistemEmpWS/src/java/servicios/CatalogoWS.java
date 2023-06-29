/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
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
import modelo.pojos.Respuesta;
import modelo.pojos.Rol;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Alicia
 */
@Path("catalogo")
public class CatalogoWS {
    
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public CatalogoWS() {
    }

    @GET
    @Path("getAllCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCatalogo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("Catalogo.getAllCatalogo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Catalogo."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
    
    @GET
    @Path("getAllEstatusContrato")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEstatusContrato() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("Catalogo.getAllEstatusContrato");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Catalogo."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getCatalogoById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogoById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Rol rol = conn.selectOne("Catalogo.getCatalogoById", nombre);
            respuesta = Response.ok(parser.toJson(rol));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Catalogo."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("catalogoId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogoById(@FormParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);

            Rol result = conn.selectOne("Catalogo.getCatalogoById", param);
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
    @Path("getAllCatalogoActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCatalogoActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("Catalogo.getAllCatalogoActivo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Catalogo."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
    
    @POST
    @Path("registrarCatalogo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCatalogo(
            @FormParam("nombre") String nombre,            
            @FormParam("idCategoria") Integer idCategoria,
            @FormParam("estatus") String estatus) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);
            param.put("idCategoria", idCategoria);
            param.put("estatus", estatus);
            

            conn.insert("Catalogo.registrarCatalogo", param);
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
    @Path("actualizarCatalogo/{idCatalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCatalogo(
            @PathParam("idCatalogo") Integer idCatalogo,
            @FormParam("nombre") String nombre,
            @FormParam("idCategoria") Integer idCategoria){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            System.out.println("idCatalogo" +  idCatalogo);
            System.out.println("nombre" +  nombre);
            System.out.println("idCategoria" +  idCategoria);
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCatalogo", idCatalogo);
            param.put("nombre", nombre);
            param.put("idCategoria", idCategoria);


            conn.update("Catalogo.actualizarCatalogo", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Catalogo actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el Catalogo"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @PUT
    @Path("actualizarEstatus/{idCatalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstatusCatalogo(
            @PathParam("idCatalogo") Integer idCatalogo) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCatalogo", idCatalogo);

            conn.update("Catalogo.actualizarEstatus", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Estatus actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el estado"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    
    @DELETE
    @Path("eliminarCatalogo/{idCatalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCatalogo(
            @PathParam("idCatalogo") Integer idCatalogo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCatalogo", idCatalogo);

            conn.update("Catalogo.eliminarCatalogo", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Catalogo eliminado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo eliminar el Catalogo"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @GET
    @Path("buscarCatalogo/{catalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCatalogoByNombre(@PathParam("catalogo") String catalogo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Catalogo> list = conn.selectList("Catalogo.buscarCatalogoPorNombre", catalogo);
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
}
