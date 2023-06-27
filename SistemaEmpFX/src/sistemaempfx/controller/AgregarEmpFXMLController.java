package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Cliente;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class AgregarEmpFXMLController implements Initializable {

    private Usuario usuario = null;

    private Cliente cliente;
    @FXML
    private TextArea txtA_observacionE;
    @FXML
    private Label lb_claveEmpeño;
    @FXML
    private Label lb_claveContrato;
    @FXML
    private DatePicker dp_fechaLimite;
    @FXML
    private TextArea txtA_prendas;
    @FXML
    private Label lb_prestamo;
    @FXML
    private Label lb_usuarioAtiende;
    @FXML
    private TextArea txtA_observaciones;
    @FXML
    private TextArea txtA_cliente;
    private Empe empeño;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Usuario usuario) {
        this.usuario = usuario;
        this.registrarEmpeño();
    }

    private void registrarEmpeño() {
        Gson gson = new Gson();

        TypeToken<Empe> token = new TypeToken<Empe>() {
        };
        HashMap<String, Object> param = new LinkedHashMap<>();
        param.put("idCliente", "");
        param.put("observaciones", "");
        param.put("usuario", usuario.getNombre());
        param.put("idContrato", "");
        param.put("interes", "");
        param.put("almacenaje", "");
        param.put("periodos", "");
        param.put("diasPeriodos", "");
        param.put("iva", "");
        param.put("tasaComercializacion", "");

        String respuesta = Requests.post("/emp/registrarEmp/", param);
        try{
            this.empeño = gson.fromJson(respuesta, token.getType());
        }catch(JsonSyntaxException ex){
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void abrirClientes(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/ClientesFXML.fxml"));

            Parent usuarios = loader.load();

            ClientesFXMLController ctrl = loader.getController();
            ctrl.setData(usuario);
            ctrl.activarEmpeño();

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.cliente = ctrl.obtenerCliente();
            if (cliente != null) {
                this.txtA_cliente.setText(this.cliente.getNombre());
            }

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
    }

    @FXML
    private void registrarPrendas(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarPrendaFXML.fxml"));

            Parent usuarios = loader.load();

            AgregarPrendaFXMLController ctrl = loader.getController();
            ctrl.setEmpeño(this.empeño);
            ctrl.setData(this.usuario);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void agregar(ActionEvent event) {
    }

    @FXML
    private void pnl_principal(MouseEvent event) {
    }

}
