/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import modelo.pojos.Rol;
import modelo.pojos.Cliente;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Carol Celina Pacheco
 */


@Path("rol")
public class RolWS {

    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public RolWS() {
    }

    @GET
    @Path("getAllRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRol() {
        SqlSession conn = MyBatisUtil.getSession();
        ResponseBuilder respuesta = null;

        try {
            List<Rol> list = conn.selectList("Rol.getAllRol");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar roles."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getRolById/{nombreRol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRolById(@PathParam("nombreRol") String nombreRol) {
        SqlSession conn = MyBatisUtil.getSession();
        ResponseBuilder respuesta = null;

        try {
            Rol rol = conn.selectOne("Rol.getRolById", nombreRol);
            respuesta = Response.ok(parser.toJson(rol));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar roles."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("rolId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response rolById(@FormParam("nombreRol") String nombreRol) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombreRol", nombreRol);

            Rol result = conn.selectOne("Rol.getRolById", param);
            conn.commit();

            respuesta = Response.ok(new Respuesta(result != null ? "1" : "0"));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @GET
    @Path("getAllRolActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRolActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        ResponseBuilder respuesta = null;

        try {
            List<Rol> list = conn.selectList("Rol.getAllRolActivo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar roles."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

}
