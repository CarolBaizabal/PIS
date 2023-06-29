/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Comercializacion;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class FechaFXMLController implements Initializable {

    @FXML
    private DatePicker dp_fechaInicio;
    @FXML
    private DatePicker dp_fechaFin;

    ComercializacionFXMLController comercializacion = new ComercializacionFXMLController();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void aceptar(ActionEvent event) {
        if (this.dp_fechaInicio.getValue() == null || this.dp_fechaFin.getValue() == null) {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Ingrese ambas fechas...");
            alertI.showAndWait();
        } else {
     
        }
    }

    
    
    
    
    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
        this.comercializacion.cargarTabla();
    }
    
}
