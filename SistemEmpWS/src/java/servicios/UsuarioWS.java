package servicios;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;

import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Permiso;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import utils.JavaUtils;

@Path("usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;
    private Gson parser = new Gson();

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    @GET
    @Path("getAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            List<Usuario> list = conn.selectList("Usuario.getAllUsers");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error consultando los usuarios."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }

    @GET
    @Path("getAllUsersActivo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsersActivo() {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            List<Usuario> list = conn.selectList("Usuario.getAllUsersActivo");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error consultando los usuarios."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }


    @GET
    @Path("getUsuarioById/{usuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuarioById(@PathParam("usuario") String usuario) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            Usuario user = conn.selectOne("Usuario.getUsuarioById", usuario);
            user.setPassword(null);
            respuesta = Response.ok(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al consultar el usuario"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @POST
    @Path("usuarioId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usuarioById(@PathParam("usuario") String usuario) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        int num = 0;
        try {
            Usuario result = conn.selectOne("Usuario.getUsuarioById", usuario);
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
    @Path("registrarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("usuario") String usuario,
            @FormParam("password") String password,
            @FormParam("telefono") String telefono,            
            @FormParam("rol") String rol,
            @FormParam("estatus") String estatus) {

        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("usuario", usuario);
            param.put("password", JavaUtils.hashString(password.toUpperCase()));
            param.put("telefono", telefono);
            param.put("rol", rol);
            param.put("estatus", estatus);
            

            conn.insert("Usuario.registrarUsuario", param);
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

    @PUT
    @Path("actualizarEstatus/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarEstatusUsuario(
            @PathParam("idUsuario") Integer idUsuario) {

        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);

            conn.update("Usuario.actualizarEstatus", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Estatus actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el estado"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    
    @DELETE
    @Path("eliminarUsuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(
            @PathParam("idUsuario") Integer idUsuario
    ) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);

            conn.update("Usuario.eliminarUsuario", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Usuario eliminado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo eliminar el usuario"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @PUT
    @Path("actualizarUsuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(
            @PathParam("idUsuario") Integer idUsuario,
            @FormParam("nombre") String nombre,
            @FormParam("apellidoPaterno") String apellidoPaterno,
            @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("usuario") String usuario,
            @FormParam("telefono") String telefono,
            @FormParam("rol") String rol){

        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idUsuario", idUsuario);
            param.put("nombre", nombre);
            param.put("apellidoPaterno", apellidoPaterno);
            param.put("apellidoMaterno", apellidoMaterno);
            param.put("usuario", usuario);
            param.put("telefono", telefono);
            param.put("rol", rol);

            conn.update("Usuario.actualizarUsuario", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Usuario actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el usuario"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }

    @GET
    @Path("buscarUsuario/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioByNombre(@PathParam("nombre") String nombre) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();
        try {
            List<Usuario> list = conn.selectList("Usuario.buscarUsuarioPorNombre", nombre);
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
    
    //Permisos
    @GET
    @Path("getAllPermisos")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPermisos() {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            List<Permiso> list = conn.selectList("Usuario.getAllPermisos");
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error consultando los permisos."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
    
    @PUT
    @Path("actualizarPermiso/{idPermiso}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarPermiso(
            @PathParam("idPermiso") Integer idPermiso,
            @FormParam("estatus") Boolean estatus) {

        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("idPermiso", idPermiso);
            param.put("estatus", estatus);
            
            conn.update("Usuario.actualizarPermiso", param);
            conn.commit();
            respuesta = Response.ok(new Respuesta("Permiso actualizado correctamente..."));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("No se pudo actualizar el Permiso"));
        } finally {
            conn.close();
        }
        return respuesta.build();
    }
    
    @GET
    @Path("getRol/{idRol}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRol(@PathParam("idRol") String idRol) {
        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            List<Permiso> list = conn.selectList("Usuario.getRol", idRol);
            respuesta = Response.ok(parser.toJson(list));
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error consultando los permisos."));
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta.build();
    }
}
