/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import com.google.gson.Gson;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import modelo.pojos.Categoria;
import modelo.pojos.Egreso;
import modelo.pojos.Empe;
import modelo.pojos.Prenda;
import modelo.pojos.Respuesta;
import modelo.pojos.Rol;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Alicia
 */
@Path("emp")
public class EmpWS {
    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of RolWS
     */
    public EmpWS() {
    }

    @GET
    @Path("getAllPrendasByEmp/{idEmp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategoria(@PathParam("idEmp") Integer idEmp) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Prenda> lista = conn.selectList("Emp.getAllPrendaByEmp", idEmp);
            respuesta = Response.ok(parser.toJson(lista));
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
    @Path("getCategoriaById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoriaById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Rol rol = conn.selectOne("Categoria.getCategoriaById", nombre);
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
    @Path("categoriaId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response categoriaById(@FormParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);

            Rol result = conn.selectOne("Categoria.getCategoriaById", param);
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
    @Path("getAllCategoriaActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCategoriaActivo() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Categoria> list = conn.selectList("Categoria.getAllCategoriaActivo");
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
    @Path("registrarEmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarEmp(
            @FormParam("idCliente") Integer idCliente,
            @FormParam("observaciones") String observaciones,
            @FormParam("usuario") String usuario,
            @FormParam("idContrato") Integer idContrato,
            @FormParam("interes") Float interes,
            @FormParam("almacenaje") Float almacenaje,
            @FormParam("periodos") Integer periodos,
            @FormParam("diasPeriodos") Integer diasPeriodos,
            @FormParam("iva") Float iva,
            @FormParam("tasaComercializacion") Float tasaComercializacion) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaCreacion = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCliente", idCliente);
            param.put("fechaCreacion", fechaCreacion);
            param.put("observaciones", observaciones);
            param.put("usuario", usuario);
            param.put("idContrato", idContrato);
            param.put("fechaActualizacion", null);
            param.put("interes", interes);
            param.put("almacenaje", almacenaje);
            param.put("periodos", periodos);
            param.put("diasPeriodos", diasPeriodos);
            param.put("iva", iva);
            param.put("tasaComercializacion", tasaComercializacion);

            conn.insert("Emp.registrarEmp", param);
            HashMap<String,Object> resultado = conn.selectOne("Emp.obtenerId");
            Empe emp = new Empe(new BigInteger( resultado.get("id")+"").intValue(), idCliente, fechaCreacion, observaciones, usuario, idContrato, "", interes, almacenaje, periodos, diasPeriodos, iva, tasaComercializacion);
            conn.commit();
            respuesta = Response.ok(emp);
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
    @Path("actualizarCategoria/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCategoria(
            @PathParam("idCategoria") Integer idCategoria,
            @FormParam("nombre") String nombre){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCategoria", idCategoria);
            param.put("nombre", nombre);


            conn.update("Categoria.actualizarCategoria", param);
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
    @Path("actualizarEstatus/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstatusCategoria(
            @PathParam("idCategoria") Integer idCategoria) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCategoria", idCategoria);

            conn.update("Categoria.actualizarEstatus", param);
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
    @Path("eliminarCategoria/{idCategoria}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarCategoria(
            @PathParam("idCategoria") Integer idCategoria) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCategoria", idCategoria);

            conn.update("Categoria.eliminarCategoria", param);
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
    @Path("buscarCategoria/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarCategoriaByNombre(@PathParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Categoria> list = conn.selectList("Categoria.buscarCategoriaPorNombre", nombre);
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
    @Path("datosCategoriaRol")
    @Produces(MediaType.APPLICATION_JSON)
    public Response datosCategoriaRol() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Categoria> list = conn.selectList("Categoria.datosCategoriaRol");
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
    @Path("datosCategoriaEgreso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response datosCategoriaEgreso() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Categoria> list = conn.selectList("Categoria.datosCategoriaEgreso");
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
    @Path("datosCategoriaIngreso")
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
    
    //Emp
     @GET
    @Path("getAllEmp")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmp() {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            List<Catalogo> list = conn.selectList("Emp.getAllEmp");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar empeño."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getEmpById/{idContrato}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmpById(@PathParam("idContrato") String idContrato) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Egreso egreso = conn.selectOne("Emp.getEmpById", idContrato);
            respuesta = Response.ok(parser.toJson(egreso));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar emepeño."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("empId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response empById(@FormParam("idContrato") String idContrato) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idContrato", idContrato);

            Egreso result = conn.selectOne("Emp.getEmpById", param);
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
    @Path("buscarEmp/{idContrato}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarEmpByNombre(@PathParam("idContrato") String idContrato) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Empe> list = conn.selectList("Emp.buscarEmpPorNombre", idContrato);
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
        @Path("actualizarDatos/{idCliente}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response actualizarDatos(
                @PathParam("idCliente") Integer idCliente,
                @FormParam("observaciones") String observaciones,
                @FormParam("usuario") String usuario,
                @FormParam("idContrato") Integer idContrato,
                @FormParam("interes") Float interes,
                @FormParam("almacenaje") Float almacenaje,
                @FormParam("periodos") Integer periodos,
                @FormParam("diasPeriodos") Integer diasPeriodos,
                @FormParam("iva") Float iva,
                @FormParam("tasaComercializacion") Float tasaComercializacion) {

            Response.ResponseBuilder respuesta = null;
            SqlSession conn = MyBatisUtil.getSession();

            try {
                HashMap<String, Object> param = new HashMap<>();
                param.put("idCliente", idCliente);
                param.put("observaciones", observaciones);
                param.put("usuario", usuario);
                param.put("idContrato", idContrato);
                param.put("interes", interes);
                param.put("almacenaje", almacenaje);
                param.put("periodos", periodos);
                param.put("diasPeriodos", diasPeriodos);
                param.put("iva", iva);
                param.put("tasaComercializacion", tasaComercializacion);

                conn.update("Cliente.actualizarDatos", param);
                conn.commit();
                respuesta = Response.ok(new Respuesta("Datos actualizados correctamente."));
            } catch (Exception ex) {
                ex.printStackTrace();
                respuesta = Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new Respuesta("No se pudieron actualizar los datos."));
            } finally {
                conn.close();
            }
            return respuesta.build();
        }

}
