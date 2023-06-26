/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarCategoriaFXMLController implements Initializable {

    @FXML
    private TextField txt_agregaCategoria;

    Usuario usuario = null;  
    Categoria categoria = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Categoria categoria,Usuario usuario, Boolean isnew){ 
        this.txt_agregaCategoria.setText(categoria.getNombre());
        this.categoria = categoria;
        this.usuario = usuario;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void editar(ActionEvent event) {
        VentanaAlert alert = new VentanaAlert();
        
        if (this.categoria != null) {
            

            if (this.txt_agregaCategoria.getText().isEmpty()) {
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("nombre", this.txt_agregaCategoria.getText());

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar la categoria?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                String respuesta = Requests.put("/categoria/actualizarCategoria/" + categoria.getIdCategoria(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.categoria = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.categoria = null;
                                    Window.close(event);
                                }
                            } else {    
                                alert.warning("Advertencia","Solo puede modificar el administrador...");
                                this.usuario = null;
                                Window.close(event);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarCategoriaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.categoria = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar una categoria...");
        }
    }

    
}
