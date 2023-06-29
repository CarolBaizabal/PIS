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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class PermisosFXMLController implements Initializable {

    @FXML
    private TableView<?> tb_categorias;
    @FXML
    private TableColumn<?, ?> tcl_idCategoria;
    @FXML
    private TableColumn<?, ?> tcl_nombre;
    @FXML
    private TableColumn<?, ?> tcl_estatusCategoria;
    @FXML
    private Button bt_restringirPermiso;
    @FXML
    private Button bt_asignarPermiso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void desactivar(ActionEvent event) {
    }

    @FXML
    private void activar(ActionEvent event) {
    }
    
}
