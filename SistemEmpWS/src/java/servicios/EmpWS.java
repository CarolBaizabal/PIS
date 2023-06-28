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
import modelo.pojos.Contrato;
import modelo.pojos.Egreso;
import modelo.pojos.Empe;
import modelo.pojos.Prenda;
import modelo.pojos.Refrendo;
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
            @FormParam("tasaComercializacion") Float tasaComercializacion,
            @FormParam("estatus") String estatus) {

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
            HashMap<String, Object> resultado = conn.selectOne("Emp.obtenerId");
            Empe emp = new Empe(new BigInteger(resultado.get("id") + "").intValue(), idCliente, fechaCreacion, observaciones, usuario, idContrato, "", interes, almacenaje, periodos, diasPeriodos, iva, tasaComercializacion, estatus);
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
    @Path("actualizarEmp/{idEmp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEmp(
            @PathParam("idEmp") Integer idEmp,
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
            HashMap<String, Object> param = new HashMap<>();
            param.put("idEmp", idEmp);
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

            conn.update("Emp.actualizarEmp", param);
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

    @PUT
    @Path("registrarFullEmp/{idEmp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarFullEmp(
            @PathParam("idEmp") Integer idEmp,
            @FormParam("idCliente") Integer idCliente,
            @FormParam("observaciones") String observaciones,
            @FormParam("observacionesContrato") String observacionesContrato,
            @FormParam("usuario") String usuario,
            @FormParam("interes") Float interes,
            @FormParam("almacenaje") Float almacenaje,
            @FormParam("totalPrestamo") Float totalPrestamo,
            @FormParam("periodos") Integer periodos,
            @FormParam("diasPeriodos") Integer diasPeriodos,
            @FormParam("iva") Float iva) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaCreacion = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDateTime fechaLimiteReferendo = now.plusDays(30);
            LocalDateTime fechaComercializacion = now.plusDays(31);

            HashMap<String, Object> paramContrato = new HashMap<>();
            paramContrato.put("idEmp", idEmp);
            paramContrato.put("fechaCreacion", fechaCreacion);
            paramContrato.put("fechaActualizacion", null);
            paramContrato.put("fechaLimiteRefrendo", fechaLimiteReferendo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            paramContrato.put("FechaComercializacion", fechaComercializacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            paramContrato.put("importePrestamo", totalPrestamo);
            paramContrato.put("estatus", "Activo");
            paramContrato.put("idContratoAnterior", null);
            paramContrato.put("idContratoSiguiente", null);
            paramContrato.put("fechaCreacionActual", fechaLimiteReferendo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            paramContrato.put("fechaComercializacionActual", fechaComercializacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            paramContrato.put("fechaCancelacion", null);
            paramContrato.put("idUsuario", usuario);
            paramContrato.put("observaciones", observacionesContrato);
            paramContrato.put("idRefrendo", null);
            paramContrato.put("idFiniquito", null);
            paramContrato.put("idAumentoEspera", null);

            conn.update("Contrato.registrarContrato", paramContrato);

            HashMap<String, Object> resultado = conn.selectOne("Emp.obtenerId");

            HashMap<String, Object> paramRefrendo = new HashMap<>();
            paramRefrendo.put("idEmp", idEmp);
            paramRefrendo.put("idContrato", new BigInteger(resultado.get("id") + "").intValue());
            paramRefrendo.put("fechaCreacion", fechaCreacion);
            paramRefrendo.put("usuario", usuario);
            paramRefrendo.put("interes", interes);
            paramRefrendo.put("almacenaje", almacenaje);
            paramRefrendo.put("subtotal", interes + almacenaje);
            paramRefrendo.put("iva", iva);
            paramRefrendo.put("total", totalPrestamo);
            paramRefrendo.put("estatus", "Activo");

            conn.insert("Emp.registrarRefrendo", paramRefrendo);

            HashMap<String, Object> idResultado = conn.selectOne("Emp.obtenerId");

            HashMap<String, Object> paramContratoRefrendo = new HashMap<>();
            paramContratoRefrendo.put("idRefrendo", new BigInteger(resultado.get("id") + "").intValue());
            paramContratoRefrendo.put("idContrato", new BigInteger(idResultado.get("id") + "").intValue());

            conn.update("Emp.actualizarContratoRefrendo", paramContratoRefrendo);

            HashMap<String, Object> param = new HashMap<>();
            param.put("idEmp", idEmp);
            param.put("idCliente", idCliente);
            param.put("observaciones", observaciones);
            param.put("usuario", usuario);
            param.put("idContrato", new BigInteger(resultado.get("id") + "").intValue());
            param.put("interes", interes);
            param.put("almacenaje", almacenaje);
            param.put("periodos", periodos);
            param.put("diasPeriodos", diasPeriodos);
            param.put("iva", iva);
            param.put("tasaComercializacion", null);

            conn.update("Emp.actualizarEmp", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Datos actualizados correctamente."));
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudieron actualizar los datos."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("registrarRefrendo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarRefrendo(
            @FormParam("idEmp") Integer idEmp,
            @FormParam("idContrato") Integer idContrato,
            @FormParam("fechaCreacion") String fechaCreacion,
            @FormParam("usuario") String usuario,
            @FormParam("interes") Float interes,
            @FormParam("almacenaje") Float almacenaje,
            @FormParam("subtotal") Float subtotal,
            @FormParam("iva") Float iva,
            @FormParam("total") Float total,
            @FormParam("estatus") String estatus) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idEmp", idEmp);
            param.put("idContrato", idContrato);
            param.put("fechaCreacion", fechaCreacion);
            param.put("usuario", usuario);
            param.put("interes", interes);
            param.put("almacenaje", almacenaje);
            param.put("subtotal", subtotal);
            param.put("iva", iva);
            param.put("total", total);
            param.put("estatus", estatus);

            conn.insert("Emp.registrarRefrendo", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Se registró correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo registrar"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @PUT
    @Path("finiquitarContrato/{idContrato}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstatusCategoria(
            @PathParam("idContrato") Integer idContrato) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idContrato", idContrato);

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

    @PUT
    @Path("refrendarEmp/{idEmp}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response refrendarEmpeño(
            @PathParam("idEmp") Integer idEmp) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idEmp", idEmp);
            
            //Empe empeño = conn.selectOne("Emp.empById",param);
            Contrato contrato = conn.selectOne("Contrato.getContratoPorEmp",param);
            Integer idContrato = contrato.getIdContrato();
            Refrendo refrendo = conn.selectOne("Contrato.getRefrendoPorContrato", idContrato);
            
            LocalDateTime now = LocalDateTime.now();
            String fechaCreacion = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDateTime fechaLimiteReferendo = now.plusDays(30);
            LocalDateTime fechaComercializacion = now.plusDays(31);

            HashMap<String, Object> paramContrato = new HashMap<>();
            paramContrato.put("idEmp", idEmp);
            paramContrato.put("fechaCreacion", fechaCreacion);
            paramContrato.put("fechaActualizacion", fechaCreacion);
            paramContrato.put("fechaLimiteRefrendo", contrato.getFechaLimiteRefrendo());
            paramContrato.put("FechaComercializacion", contrato.getFechaComercializacion());
            paramContrato.put("importePrestamo", contrato.getImportePrestamo());
            paramContrato.put("estatus", "Activo");
            paramContrato.put("idContratoAnterior", contrato.getIdContrato());
            paramContrato.put("idContratoSiguiente", null);
            paramContrato.put("fechaCreacionActual", fechaLimiteReferendo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            paramContrato.put("fechaComercializacionActual", fechaComercializacion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            paramContrato.put("fechaCancelacion", null);
            paramContrato.put("idUsuario", contrato.getUsuario());
            paramContrato.put("observaciones", contrato.getObservaciones());
            paramContrato.put("idRefrendo", null);
            paramContrato.put("idFiniquito", null);
            paramContrato.put("idAumentoEspera", null);

            conn.update("Contrato.registrarContrato", paramContrato);

            HashMap<String, Object> idContratoNuevo = conn.selectOne("Emp.obtenerId");

            HashMap<String, Object> paramRefrendo = new HashMap<>();
            paramRefrendo.put("idEmp", idEmp);
            paramRefrendo.put("idContrato", new BigInteger(idContratoNuevo.get("id") + "").intValue());
            paramRefrendo.put("fechaCreacion", fechaCreacion);
            paramRefrendo.put("usuario", refrendo.getUsuario());
            paramRefrendo.put("interes", refrendo.getInteres());
            paramRefrendo.put("almacenaje", refrendo.getAlmacenaje());
            paramRefrendo.put("subtotal", refrendo.getInteres() + refrendo.getAlmacenaje());
            paramRefrendo.put("iva", refrendo.getIva());
            paramRefrendo.put("total", refrendo.getTotal());
            paramRefrendo.put("estatus", "Activo");

            conn.insert("Emp.registrarRefrendo", paramRefrendo);

            HashMap<String, Object> idRefrendo = conn.selectOne("Emp.obtenerId");

            HashMap<String, Object> paramContratoRefrendo = new HashMap<>();
            paramContratoRefrendo.put("idRefrendo", new BigInteger(idContratoNuevo.get("id") + "").intValue());
            paramContratoRefrendo.put("idContrato", new BigInteger(idRefrendo.get("id") + "").intValue());

            conn.update("Emp.actualizarContratoRefrendo", paramContratoRefrendo);
            
            //Enlazar el nuevo contrato con el empeño
            param = new HashMap<>();
            param.put("idEmp", idEmp);
            param.put("idContrato", new BigInteger(idContratoNuevo.get("id") + "").intValue());
            conn.update("Emp.actualizarEmpRefrendo", param);
            
            //Actualizar el contrato antiguo
            param = new HashMap<>();
            param.put("idContratoNuevo", new BigInteger(idContratoNuevo.get("id") + "").intValue());
            param.put("idContrato", new BigInteger(idContratoNuevo.get("id") + "").intValue());
            conn.update("Contrato.actualizarAnteriorContratoRefrendo", param);

            
            conn.commit();
            respuesta = Response.ok(new Respuesta("Estatus actualizado correctamente..."));
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el estado"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
}
