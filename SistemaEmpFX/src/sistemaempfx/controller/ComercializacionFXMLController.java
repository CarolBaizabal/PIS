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
    private TableColumn<?, ?> tb_emp;
    @FXML
    private TableColumn<?, ?> tb_catalogo;
    @FXML
    private TableColumn<?, ?> tb_categoria;
    @FXML
    private TableColumn<?, ?> tb_piezas;
    @FXML
    private TableColumn<?, ?> tb_serie;
    @FXML
    private TableColumn<?, ?> tb_modelo;
    @FXML
    private TableColumn<?, ?> tb_descripcion;
    @FXML
    private TableColumn<?, ?> tb_metal;
    @FXML
    private TableColumn<?, ?> tb_peso;
    @FXML
    private TableColumn<?, ?> tb_kilataje;
    @FXML
    private TableColumn<?, ?> tb_prestamo;
    @FXML
    private TableColumn<?, ?> tb_comercializacion;
    @FXML
    private TableColumn<?, ?> tb_venta;
    @FXML
    private TableColumn<?, ?> tb_estatus;
    @FXML
    private TableColumn<?, ?> tb_claveC;
    @FXML
    private TableColumn<?, ?> fechaCreacion;
    @FXML
    private TableColumn<?, ?> tb_fechaC;
    @FXML
    private TableColumn<?, ?> tb_fechaVent;
    @FXML
    private TableColumn<?, ?> tb_usuario;

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
    private void Limpiar(ActionEvent event) {
    }

    @FXML
    private void editar(ActionEvent event) {
    }
    
}
