/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sistemaempfx.model.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class EmpFXMLController implements Initializable {
    @FXML
    private BorderPane pnl_principal;
    @FXML
    private Button btn_editar;
    @FXML
    private Button btn_cancelar;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_limpiar;
    @FXML
    private Button btn_crear;
    @FXML
    private Button btn_refrendar;
    @FXML
    private Button btn_extension;
    @FXML
    private Button btn_finiquitar;
    @FXML
    private TextField txt_usuario;
    private Usuario usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    @FXML
    private void editar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void buscar(ActionEvent event) {
    }

    @FXML
    private void limpiar(ActionEvent event) {
    }

    @FXML
    private void crear(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarEmpFXML.fxml"));
 
            Parent usuarios = loader.load();

            AgregarEmpFXMLController ctrl = loader.getController();
            ctrl.setData(usuario);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            //this.tablaUsuario();

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrendar(ActionEvent event) {
    }

    @FXML
    private void extension(ActionEvent event) {
    }

    @FXML
    private void finiquitar(ActionEvent event) {
    }
    
}
