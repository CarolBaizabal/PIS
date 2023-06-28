package servicios;


import com.google.gson.Gson;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Prenda;
import modelo.pojos.Respuesta;
import org.apache.ibatis.session.SqlSession;

@Path("prenda")
public class PrendaWS {

    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of UsuarioWS
     */
    public PrendaWS() {
    }

    @GET
    @Path("getAllPrenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrenda() {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            List<Prenda> list = conn.selectList("Prenda.getAllPrenda");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error consultando las Prendas"));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

   @GET
    @Path("getPrendaById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrendaById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        Response.ResponseBuilder respuesta = null;

        try {
            Prenda prenda = conn.selectOne("Prenda.getPrendaById", nombre);
            respuesta = Response.ok(parser.toJson(prenda));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Prenda"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("prendaId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response prendaById(@PathParam("nombre") String nombre) {
        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        int num = 0;
        try {
            Prenda result = conn.selectOne("Prenda.getPrendaById", nombre);
            conn.commit();

            respuesta = Response.ok(new Respuesta(result != null ? "1" : "0"));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Prenda"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @GET
    @Path("buscarPrenda/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPrendaByNombre(@PathParam("nombre") String nombre) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Prenda> list = conn.selectList("Prenda.buscarPrendaPorNombre", nombre);
            respuesta = Response.ok(parser.toJson(list));
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
    
    @PUT
    @Path("actualizarPrenda/{idPrendas}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPrenda(
            @PathParam("idPrendas") Integer idPrendas,
            @FormParam("nombre") String nombre,
            @FormParam("categoria") String categoria,
            @FormParam("numPiezas") Integer numPiezas,
            @FormParam("serie") String serie,
            @FormParam("modelo") String modelo,
            @FormParam("subcategoria") String subcategoria,
            @FormParam("metal") String metal,
            @FormParam("peso") Double peso,
            @FormParam("kilataje") Double kilataje,
            @FormParam("prestamo") Double prestamo,
            @FormParam("descripcion") String descripcion) {

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idPrendas", idPrendas);
            param.put("nombre", nombre);
            param.put("categoria", categoria);
            param.put("numPiezas", numPiezas);
            param.put("serie", serie);
            param.put("modelo", modelo);
            param.put("subcategoria", subcategoria);
            param.put("metal", metal);
            param.put("peso", peso);
            param.put("kilataje", kilataje);
            param.put("prestamo", prestamo);
            param.put("descripcion", descripcion);

            conn.update("Prenda.actualizarPrenda", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Prenda actualizada correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar Prenda")); 
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("registrarPrenda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarPrenda(
            @FormParam("nombre") String nombre,
            @FormParam("idEmp") Integer idEmp,
            @FormParam("categoria") String categoria,
            @FormParam("numPiezas") Integer numPiezas,
            @FormParam("serie") String serie,
            @FormParam("modelo") String modelo,
            @FormParam("subcategoria") String subcategoria,
            @FormParam("descripcion") String descripcion,
            @FormParam("metal") String metal,
            @FormParam("peso") Float peso,
            @FormParam("kilataje") Float kilataje,
            @FormParam("prestamo") Float prestamo,
            @FormParam("precioComercializacion") Float precioComercializacion,
            @FormParam("precioVenta") Float precioVenta,
            @FormParam("estatus") String estatus,
            @FormParam("usuario") String usuario
            
    ) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaCreacion = now.toString();
            
           //Toma la fecha actual
           LocalDate fechaComercializacion = LocalDate.now();
           //Calculamos la fecha a 15 dias
           fechaComercializacion = fechaComercializacion.plusDays(33);
           String fechaC = fechaComercializacion.toString();
           
           
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);
            param.put("idEmp", idEmp);
            param.put("categoria", categoria);
            param.put("numPiezas", numPiezas);
            param.put("serie", serie);
            param.put("modelo", modelo);
            param.put("subcategoria", subcategoria);
            param.put("descripcion", descripcion);
            param.put("metal", metal);
            param.put("peso", peso);
            param.put("kilataje", kilataje);
            param.put("prestamo", prestamo);
            param.put("precioComercializacion", precioComercializacion);
            param.put("precioVenta", precioVenta);
            param.put("estatus", estatus);
            param.put("fechaCreacion", fechaCreacion);
            param.put("fechaComercializacion", fechaC);
            
            param.put("usuario", usuario);

            conn.insert("Prenda.registrarPrenda", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Se registró correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo registrar "));
        } finally {
            conn.close();
        }
        return respuesta.build();
        //tienes que buscar los Empeños que esten caducados que esten Refrendo, Extencion, Activo, Inactivo
    }

}