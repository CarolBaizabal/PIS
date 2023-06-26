package servicios;

import com.google.gson.Gson;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import modelo.pojos.Cliente;
import org.apache.ibatis.session.SqlSession;
/**
 *
 * @author Carol Celina Pacheco
 */


@Path("cliente")
public class ClienteWS {

    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    public ClienteWS() {
    }

    @GET
    @Path("getAllCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCliente() {
        SqlSession conn = MyBatisUtil.getSession();
        ResponseBuilder respuesta = null;

        try {
            List<Cliente> list = conn.selectList("Cliente.getAllCliente");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Cliente."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getClienteById/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClienteById(@PathParam("nombre") String nombre) {
        SqlSession conn = MyBatisUtil.getSession();
        ResponseBuilder respuesta = null;

        try {
            Cliente cliente = conn.selectOne("Cliente.getClienteById", nombre);
            respuesta = Response.ok(parser.toJson(cliente));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar Cliente."));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("clienteId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response clienteById(@FormParam("nombre") String nombre) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        long num = 0;
        try {
            Cliente result = conn.selectOne("Cliente.getClienteById", nombre);
            conn.commit();

            respuesta = Response.ok(new Respuesta(result != null ? "1" : "0"));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("registrarCliente")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCliente(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("calle") String calle,
            @FormParam("cp") String cp,
            @FormParam("colonia") String colonia,
            @FormParam("municipio") String municipio,
            @FormParam("estado") String estado,
            @FormParam("idemex") String idemex,
            @FormParam("fechaNacimiento") String fechaNacimiento,            
            @FormParam("curp") String curp,
            @FormParam("rfc") String rfc,
            @FormParam("telefono") String telefono,
            @FormParam("correo") String correo,            
            @FormParam("comentarios") String comentarios,
            @FormParam("sexo") String sexo,
            @FormParam("usuario") String usuario ){

        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaCreacion = now.toString();
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("calle", calle);
            param.put("cp", cp);
            param.put("colonia", colonia);
            param.put("municipio", municipio);
            param.put("estado", estado);
            param.put("idemex", idemex);
            param.put("fechaNacimiento", fechaNacimiento);            
            param.put("curp", curp);
            param.put("rfc", rfc);
            param.put("telefono", telefono);
            param.put("correo",correo);            
            param.put("comentarios",comentarios);
            param.put("sexo",sexo); 
            param.put("usuario",usuario); 
            param.put("fechaCreacion", fechaCreacion);
            

            conn.insert("Cliente.registrarCliente", param);
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
    }

    @GET
    @Path("buscarCliente/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClienteByNombre(@PathParam("nombre") String nombre) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Cliente> list = conn.selectList("Cliente.buscarClientePorNombre", nombre);
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
    @Path("actualizarCliente/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarCliente(
            @PathParam("idCliente") Integer idCliente,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("calle") String calle,
            @FormParam("cp") String cp,
            @FormParam("colonia") String colonia,
            @FormParam("municipio") String municipio,
            @FormParam("estado") String estado,
            @FormParam("idemex") String idemex,
            @FormParam("fechaNacimiento") String fechaNacimiento,            
            @FormParam("curp") String curp,
            @FormParam("rfc") String rfc,
            @FormParam("telefono") String telefono,
            @FormParam("correo") String correo,            
            @FormParam("comentarios") String comentarios,
            @FormParam("sexo") String sexo,
            @FormParam("usuarioA") String usuarioA){

        Response.ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            LocalDateTime now = LocalDateTime.now();
            String fechaActualizacion = now.toString();
            System.out.println("" + usuarioA);
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idCliente", idCliente);
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("calle", calle);
            param.put("cp", cp);
            param.put("colonia", colonia);
            param.put("municipio", municipio);
            param.put("estado", estado);
            param.put("idemex", idemex);
            param.put("fechaNacimiento", fechaNacimiento);            
            param.put("curp", curp);
            param.put("rfc", rfc);
            param.put("telefono", telefono);
            param.put("correo",correo);            
            param.put("comentarios",comentarios);
            param.put("sexo",sexo); 
            param.put("fechaActualizacion", fechaActualizacion);
            param.put("usuarioA",usuarioA); 
            


            conn.update("Cliente.actualizarCliente", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Cliente actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el Cliente"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
}

