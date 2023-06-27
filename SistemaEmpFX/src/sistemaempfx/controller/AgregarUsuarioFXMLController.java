
package sistemaempfx.controller;

import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Rol;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.model.pojos.Categoria;

public class AgregarUsuarioFXMLController implements Initializable {

    @FXML
    private ComboBox<Categoria> cmb_rolRegistrar;
    private Integer[] arrayID;
    private ObservableList<Categoria> comboBoxList;

    @FXML
    private TextField txt_apellidoPaterno;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_telefono;
    @FXML
    private TextField txt_apellidoMaterno;
    @FXML
    private TextField txt_usuario;
    @FXML
    private TextField txt_password;

    Usuario usuario = null;
    Rol rol = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxList = datosCategoria();
        cmb_rolRegistrar.setItems(comboBoxList);
    }

    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private void agregar(ActionEvent event) {

        int position = this.cmb_rolRegistrar.getSelectionModel().getSelectedIndex();

        if (this.txt_nombre.getText().isEmpty()
            || this.txt_apellidoPaterno.getText().isEmpty()
            || this.txt_apellidoMaterno.getText().isEmpty()
            || this.txt_usuario.getText().isEmpty()
            || this.txt_password.getText().isEmpty()
            || this.txt_telefono.getText().isEmpty()
            || position <= -1) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese los datos");
            alert.showAndWait();
        } else {

            try {
                String verificacion = null;
                String v = "0";
                HashMap<String, Object> buscar = new LinkedHashMap<>();
                buscar.put("usuario", this.txt_usuario.getText());
                verificacion = Requests.post("/usuario/usuarioId/", buscar);

                JSONObject dataJsonV = new JSONObject(verificacion);

                if (dataJsonV.getString("mensaje").equals("0")) {
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_nombre.getText());
                    params.put("apellidoPaterno", this.txt_apellidoPaterno.getText());
                    params.put("apellidoMaterno", this.txt_apellidoMaterno.getText());
                    params.put("usuario", this.txt_usuario.getText());
                    params.put("password", this.txt_password.getText());
                    params.put("telefono", this.txt_telefono.getText());
                    params.put("rol", this.cmb_rolRegistrar.getValue());

                    String respuesta = Requests.post("/usuario/registrarUsuario/", params);

                    JSONObject dataJson = new JSONObject(respuesta);

                    if ((Boolean) dataJson.get("errorRespuesta") == false) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Informativo");
                        alert.setHeaderText(null);
                        alert.setContentText(dataJson.getString("mensaje"));
                        alert.showAndWait();
                        Window.close(event);

                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText(dataJson.getString("mensaje"));
                        alert.showAndWait();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El usuario ya se encuentra registrado...");
                    alert.showAndWait();
                }
            } catch (JSONException ex) {
                Logger.getLogger(AgregarUsuarioFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    private ObservableList datosCategoria() {

        String respuesta = Requests.get("/categoria/datosCategoriaRol/");
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };

        List<Categoria> listaCategoria = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCategoria);
        System.out.print(comboBoxList);
        return comboBoxList;
    }

    @FXML
    private void limpiar(ActionEvent event) {
        this.txt_nombre.setText("");
        this.txt_apellidoPaterno.setText("");
        this.txt_apellidoMaterno.setText("");
        this.txt_usuario.setText("");
        this.txt_password.setText("");
        this.txt_telefono.setText("");
    }


}
