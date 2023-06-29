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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Comercializacion;
import sistemaempfx.model.pojos.Egreso;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarComercializacionFXMLController implements Initializable {
    
Comercializacion comercializacion = null;
Usuario usuario = null;

    @FXML
    private TextArea txt_observaciones;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    public void setData(Comercializacion comercializacion ,Usuario usuario, Boolean isnew){    
        this.comercializacion = comercializacion;
        this.usuario = usuario;
         txt_observaciones.setText(this.comercializacion.getObservaciones());
    }
    @FXML
    private void editar(ActionEvent event) {
        VentanaAlert alert = new VentanaAlert();
        
        if (this.comercializacion != null) {
            

            if (this.txt_observaciones.getText().isEmpty()) {
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("observaciones", this.txt_observaciones.getText());

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar las observaciones?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                String respuesta = Requests.put("/comercializacion/actualizarComercializacion/" + comercializacion.getIdComercializacion(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.comercializacion = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.comercializacion = null;
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
                        this.comercializacion = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar una comercializacion...");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
}
