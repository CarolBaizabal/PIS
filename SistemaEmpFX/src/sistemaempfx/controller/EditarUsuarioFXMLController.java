/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Rol;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarUsuarioFXMLController implements Initializable {

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
    private TextField txt_password;
    @FXML
    private ComboBox<Rol> cmb_rolRegistrar;

    /**
     * Initializes the controller class.
     */

    private Integer[] arrayID;
    private ObservableList<Rol> comboBoxList;
    Usuario usuario = null;  
    Boolean isnew=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxList = getAllRoles();
        cmb_rolRegistrar.setItems(comboBoxList);
    }    
    
    public void setData(Usuario usuario, Boolean isnew){ 
        this.usuario=usuario;
        this.isnew=isnew;
        this.cargarUsuario();
    }


    @FXML
    private void editarUsuario(ActionEvent event) {
        
        VentanaAlert alert = new VentanaAlert();
        
        if (this.usuario != null) {

            int position = this.cmb_rolRegistrar.getSelectionModel().getSelectedIndex();

            if (this.txt_nombre.getText().isEmpty()
                || this.txt_apellidoPaterno.getText().isEmpty()
                || this.txt_apellidoMaterno.getText().isEmpty()
                || this.txt_usuario.getText().isEmpty()
                || this.txt_telefono.getText().isEmpty()
                || this.cmb_rolRegistrar.getValue() == null) {
                
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("nombre", this.txt_nombre.getText());
                params.put("apellidoPaterno", this.txt_apellidoPaterno.getText());
                params.put("apellidoMaterno", this.txt_apellidoMaterno.getText());
                params.put("usuario", this.txt_usuario.getText());
                params.put("telefono", this.txt_telefono.getText());
                params.put("rol", this.cmb_rolRegistrar.getValue().getNombreRol());

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar el usuario?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                alert.warning("Advertencia","Solo puede modificar el administrador...");
                                this.usuario = null;
                                Window.close(event);
                            } else {    
                                String respuesta = Requests.put("/usuario/actualizarUsuario/" + usuario.getIdUsuario(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.usuario = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.usuario = null;
                                    Window.close(event);
                                }
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarUsuarioFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.usuario = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar un Usuario...");
        }
    }

    public void cargarUsuario() {

        if (this.usuario.getRol().equals("Administrador")) {
            this.txt_nombre.setText(usuario.getNombre());
            this.txt_apellidoPaterno.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaterno.setText(usuario.getApellidoMaterno());
            this.txt_usuario.setText(usuario.getUsuario());
            this.txt_telefono.setText(usuario.getTelefono());
        } else {
            this.txt_nombre.setText(usuario.getNombre());
            this.txt_apellidoPaterno.setText(usuario.getApellidoPaterno());
            this.txt_apellidoMaterno.setText(usuario.getApellidoMaterno());
            this.txt_telefono.setText(usuario.getTelefono());
            this.txt_usuario.setText(usuario.getUsuario());
        }
    }

    private ObservableList getAllRoles() {

        String respuesta = Requests.get("/rol/getAllRolActivo/");
        Gson gson = new Gson();

        TypeToken<List<Rol>> token = new TypeToken<List<Rol>>() {
        };

        List<Rol> listaRoles = gson.fromJson(respuesta, token.getType());
        
        comboBoxList=FXCollections.observableArrayList(listaRoles);
        System.out.print(comboBoxList);
        return comboBoxList;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
        this.cargarUsuario();
        this.usuario = null;
    }

}
