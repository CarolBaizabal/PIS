package sistemaempfx.controller;

import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.JavaUtils;
import sistemaempfx.utils.Window;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.json.JSONObject;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Permiso;

public class LoginFXMLController implements Initializable {

    @FXML
    private TextField txt_usuario;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label lbl_mensaje;
    Usuario usuario = null;
    Usuario nombreUsuario = null;
    
    public void setData(HashMap<String, Object> context) {
        System.out.print(context);
    }

    public void setDataUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private void iniciarSesion(ActionEvent event) throws UnknownHostException {
        if (this.validar()) {
            this.lbl_mensaje.setText("");
            String data = "";
            HashMap<String, Object> params = new LinkedHashMap<>();
            params.put("usuario", this.txt_usuario.getText());
            params.put("password", this.txt_password.getText());
            data = Requests.post("/sesion/login/", params);
            if (!data.isEmpty()) {
                System.out.println("Data;" + data);
                
                try {
                    JSONObject dataJson = new JSONObject(data);

                    Stage stage = Window.getStageByEvent(event);

                    Gson gson = new Gson();
                    Usuario user = gson.fromJson(data, Usuario.class); 

                    System.out.println("Hola " + user.getRol());
                    
                    HashMap<String, Object> context = new HashMap<String, Object>();           
                    context.put("mac", JavaUtils.getMAC());
                    context.put("usuario", user);                                                 
                    context.put("ip", InetAddress.getLocalHost());

                    if (user.getRol().equals("Administrador")||user.getRol().equals("Cajero")||user.getRol().equals("Gerente")) {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/sistemaempfx/gui/view/PrincipalFXML.fxml"));

                        Parent principal = loader.load();
                        PrincipalFXMLController ctrl = loader.getController();
                        ctrl.setData(context);
                        ctrl.setDataUsuario(user);
                        
                        HashMap<String,Boolean> permisos = this.lista(user.getRol());
                        ctrl.setPermisos(permisos);

                        Scene scene = new Scene(principal);
                        stage.setScene(scene);
                        stage.setTitle("Financiera Ramírez S.A DE C.V");
                        stage.setResizable(false);
                        stage.getIcons().add(new Image("/sistemaempfx/gui/img/icon.png"));
                        stage.show();
                    } else {
                        /*Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Advertencia");
                        alert.setHeaderText(null);
                        alert.setContentText("Solo los administradores pueden ingresar...");
                        alert.showAndWait();*/
                    }
                } catch (Exception ex) {
                    Alert alertI = new Alert(Alert.AlertType.WARNING);
                    alertI.setTitle("Advertencia");
                    alertI.setHeaderText(null);
                    alertI.setContentText("El usuario no existe o confirme sus datos...");
                    alertI.showAndWait();
                }
            } else {
                this.lbl_mensaje.setText("Error en la conexion");
            }
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Complete los datos...");
            alertI.showAndWait();
        }
    }

    private boolean validar() {
        boolean valido = false;
        if (!this.txt_usuario.getText().isEmpty() && !this.txt_password.getText().isEmpty()) {
            valido = true;
        }
        return valido;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private HashMap<String,Boolean> lista(String idRol) {

        String respuesta = Requests.get("/usuario/getRol/"+idRol);
        Gson gson = new Gson();

        TypeToken<List<Permiso>> token = new TypeToken<List<Permiso>>() {
        };

        List<Permiso> listaPermisos = gson.fromJson(respuesta, token.getType());
        HashMap<String,Boolean> permisos = new HashMap<>();
        
        for(Permiso permiso : listaPermisos){
            permisos.put(permiso.getPermiso(),permiso.getEstatus());
        }

        return permisos;
    }
}
