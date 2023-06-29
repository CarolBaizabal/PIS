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
@Path("catalogoprenda")
public class CatalogoPrendaWS {
    
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public CatalogoPrendaWS() {
    }

    @GET
    @Path("getAllCatalogoPrenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCatalogoPrenda() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("CatalogoPrenda.getAllCatalogoPrenda");
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
    @Path("getCatalogoPrendaById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCatalogoPrendaById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Rol rol = conn.selectOne("CatalogoPrenda.getCatalogoPrendaById", nombre);
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
    @Path("catalogoPrendaId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogoPrendaById(@FormParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);

            Rol result = conn.selectOne("CatalogoPrenda.getCatalogoPrendaById", param);
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
    @Path("getAllCatalogoPrendaActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCatalogoPrendaActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("CatalogoPrenda.getAllCatalogoPrendaActivo");
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
    @Path("registrarCatalogoPrenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCatalogoPrenda(
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
            

            conn.insert("CatalogoPrenda.registrarCatalogoPrenda", param);
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
    @Path("actualizarCatalogoPrenda/{idCatalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCatalogoPrenda(
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


            conn.update("CatalogoPrenda.actualizarCatalogoPrenda", param);
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
    @Path("actualizarEstatusPrenda/{idCatalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstatusCatalogoPrenda(
            @PathParam("idCatalogo") Integer idCatalogo) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCatalogo", idCatalogo);

            conn.update("CatalogoPrenda.actualizarEstatusPrenda", param);
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
    @Path("eliminarCatalogoPrenda/{idCatalogo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCatalogoPrenda(
            @PathParam("idCatalogo") Integer idCatalogo) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCatalogo", idCatalogo);

            conn.update("CatalogoPrenda.eliminarCatalogoPrenda", param);
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
    @Path("buscarCatalogoPrenda/{categoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCatalogoPrendaByNombre(@PathParam("categoria") String categoria) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Catalogo> list = conn.selectList("CatalogoPrenda.buscarCatalogoPrendaPorNombre", categoria);
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
