package servicios;

import java.util.HashMap;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import modelo.mybatis.MyBatisUtil;
import modelo.pojos.Respuesta;
import modelo.pojos.Usuario;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import utils.JavaUtils;



@Path("sesion")
public class LoginWS {

    @Context
    private UriInfo context;

    public LoginWS() {
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("usuario") String usuario, @FormParam("password") String password) {

        ResponseBuilder respuesta = null;
        SqlSession conn = MyBatisUtil.getSession();

        try {
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("usuario", usuario);
            System.out.println("AQUI TOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOY");
            //param.put("password", JavaUtils.hashString(password.toUpperCase()));
            param.put("password", password);
            Usuario u = conn.selectOne("Sesion.login", param);
            if (u == null || u.getIdUsuario() == null) {
                respuesta = Response
                        .status(Status.NOT_FOUND)
                        .entity(new Respuesta("No se encontro ningun usuario con esas credenciales"));
            } else {
                u.setPassword(null);
                respuesta = Response.ok(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            respuesta = Response
                    .status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Respuesta("Error al iniciar sesion."));
        } finally {
            conn.close();
        }

        return respuesta.build();
    }
}