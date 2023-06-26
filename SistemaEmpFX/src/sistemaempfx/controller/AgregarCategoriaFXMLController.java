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
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class AgregarCategoriaFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Usuario usuario = null;
    @FXML
    private TextField txt_agregaCategoria;
    
    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void agregarCategoria(ActionEvent event) {
       
        if (this.txt_agregaCategoria.getText().isEmpty()){

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
                buscar.put("nombre", this.txt_agregaCategoria.getText());
                verificacion = Requests.post("/categoria/categoriaId/", buscar);

                JSONObject dataJsonV = new JSONObject(verificacion);

                if (dataJsonV.getString("mensaje").equals("0")) {

                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_agregaCategoria.getText());

                    String respuesta = Requests.post("/categoria/registrarCategoria/", params);

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
                Logger.getLogger(AgregarCategoriaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
    
}
