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
import modelo.pojos.Categoria;
import modelo.pojos.Respuesta;
import modelo.pojos.Rol;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;
import utils.JavaUtils;

/**
 *
 * @author Alicia
 */
@Path("categoriaprenda")
public class CategoriaPrendaWS {
    
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public CategoriaPrendaWS() {
    }

    @GET
    @Path("getAllCategoriaPrenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategoriaPrenda() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Categoria> list = conn.selectList("CategoriaPrenda.getAllCategoriaPrenda");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Categoria."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getCategoriaPrendaById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoriaPrendaById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Rol rol = conn.selectOne("CategoriaPrenda.getCategoriaPrendaById", nombre);
            respuesta = Response.ok(parser.toJson(rol));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Categoria."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("categoriaPrendaId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response categoriaPrendaById(@FormParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);

            Rol result = conn.selectOne("CategoriaPrenda.getCategoriaPrendaById", param);
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
    @Path("getAllCategoriaPrendaActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategoriaActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Categoria> list = conn.selectList("CategoriaPrenda.getAllCategoriaPrendaActivo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Categoria."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @POST
    @Path("registrarCategoriaPrenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCategoriaPrenda(
            @FormParam("nombre") String nombre,
            @FormParam("estatus") String estatus) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);
            param.put("estatus", estatus);
            

            conn.insert("CategoriaPrenda.registrarCategoriaPrenda", param);
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
    @Path("actualizarCategoriaPrenda/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCategoriaPrenda(
            @PathParam("idCategoria") Integer idCategoria,
            @FormParam("nombre") String nombre){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCategoria", idCategoria);
            param.put("nombre", nombre);


            conn.update("CategoriaPrenda.actualizarCategoriaPrenda", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Categoria actualizada correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar la Categoria"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @PUT
    @Path("actualizarEstatusPrenda/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstatusCategoriaPrenda(
            @PathParam("idCategoria") Integer idCategoria) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCategoria", idCategoria);

            conn.update("CategoriaPrenda.actualizarEstatusPrenda", param);
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
    @Path("eliminarCategoriaPrenda/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCategoriaPrenda(
            @PathParam("idCategoria") Integer idCategoria) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCategoria", idCategoria);

            conn.update("CategoriaPrenda.eliminarCategoriaPrenda", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Categoria eliminada correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo eliminar la categoria"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @GET
    @Path("buscarCategoriaPrenda/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCategoriaPrendaByNombre(@PathParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Categoria> list = conn.selectList("CategoriaPrenda.buscarCategoriaPrendaPorNombre", nombre);
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
    @Path("datosCategoriaMetal")
    @Produces(MediaType.APPLICATION_JSON)
    public Response datosCategoriaIngreso() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Categoria> list = conn.selectList("Categoria.datosCategoriaIngreso");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Categoria."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
}
