/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class ComercializacionFXMLController implements Initializable {
    @FXML
    private TextField txt_usuario;
    @FXML
    private TableColumn<?, ?> tc_fechaCreacion;
    @FXML
    private TableColumn<?, ?> tc_usuario;
    @FXML
    private TableColumn<?, ?> tb_fechaInicioBusqueda;
    @FXML
    private TableColumn<?, ?> tc_observaciones;
    @FXML
    private TableColumn<?, ?> tc_metal;
    @FXML
    private TableColumn<?, ?> tc_detalleComercializacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buscar(ActionEvent event) {
    }

    @FXML
    private void limpiar(ActionEvent event) {
    }

    @FXML
    private void observaciones(ActionEvent event) {
    }

    @FXML
    private void vender(ActionEvent event) {
    }
    
}
