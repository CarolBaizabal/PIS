/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Empe;
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
    @FXML
    private TableView<Empe> tb_emp;
    @FXML
    private TableColumn<Empe, Integer> tc_cliente;
    @FXML
    private TableColumn<Empe, String> tc_creacion;
    @FXML
    private TableColumn<Empe, String> tc_observaciones;
    @FXML
    private TableColumn<Empe, String> tc_usuario;
    @FXML
    private TableColumn<Empe, Integer> tc_contrato;
    @FXML
    private TableColumn<Empe, String> tc_fechaActualizacion;
    @FXML
    private TableColumn<Empe, Float> tc_interes;
    @FXML
    private TableColumn<Empe, Float> tc_almacenaje;
    @FXML
    private TableColumn<Empe, Float> tc_periodos;
    @FXML
    private TableColumn<Empe, Float> tc_diasPeriodo;
    @FXML
    private TableColumn<Empe, Float> tc_iva;
    @FXML
    private TableColumn<Empe, Float> tc_tasaComercializacion;
    @FXML
    private TableColumn<Empe, String> tc_estatus;

    
    Empe emp = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }
    
    public void setUsuario(Usuario usuario, Empe emp){
        this.usuario = usuario;
        this.emp = emp;
    }

    @FXML
    private void editar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
        
    }

    @FXML
    private void buscar(ActionEvent event) {
        int buscar = Integer.parseInt(txt_usuario.getText());
        tb_emp.getItems().clear();
        List<Empe> listaEmpe = null;

        // Actual
            String respuesta = "";

             respuesta = Requests.get("/emp/buscarEmp/" + buscar);
            Gson gson = new Gson();

            TypeToken<List<Empe>> token = new TypeToken<List<Empe>>() {
            };
            listaEmpe = gson.fromJson(respuesta, token.getType());

            tc_cliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
            tc_creacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
            tc_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
            tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tc_contrato.setCellValueFactory(new PropertyValueFactory<>("idContrato"));
            tc_fechaActualizacion.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));
            tc_interes.setCellValueFactory(new PropertyValueFactory<>("interes"));
            tc_almacenaje.setCellValueFactory(new PropertyValueFactory<>("almacenaje"));
            tc_periodos.setCellValueFactory(new PropertyValueFactory<>("periodos"));
            tc_diasPeriodo.setCellValueFactory(new PropertyValueFactory<>("diasPeriodos"));
            tc_iva.setCellValueFactory(new PropertyValueFactory<>("iva"));
            tc_tasaComercializacion.setCellValueFactory(new PropertyValueFactory<>("tasaComercializacion"));
            tc_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        listaEmpe.forEach(e -> {
            tb_emp.getItems().add(e);
        });
    }

    @FXML
    private void limpiar(ActionEvent event) {
        this.cargarTabla();
        txt_usuario.setText("");
    }

    public void cargarTabla(){
         String respuesta = "";
            tb_emp.getItems().clear();

            respuesta = Requests.get("/emp/getAllEmp/");
            Gson gson = new Gson();

            TypeToken<List<Empe>> token = new TypeToken<List<Empe>>() {};

            List<Empe> listaEmpe = gson.fromJson(respuesta, token.getType());

            tc_cliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
            tc_creacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
            tc_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
            tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tc_contrato.setCellValueFactory(new PropertyValueFactory<>("idContrato"));
            tc_fechaActualizacion.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));
            tc_interes.setCellValueFactory(new PropertyValueFactory<>("interes"));
            tc_almacenaje.setCellValueFactory(new PropertyValueFactory<>("almacenaje"));
            tc_periodos.setCellValueFactory(new PropertyValueFactory<>("periodos"));
            tc_diasPeriodo.setCellValueFactory(new PropertyValueFactory<>("diasPeriodos"));
            tc_iva.setCellValueFactory(new PropertyValueFactory<>("iva"));
            tc_tasaComercializacion.setCellValueFactory(new PropertyValueFactory<>("tasaComercializacion"));
            tc_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

            listaEmpe.forEach(e -> {
                tb_emp.getItems().add(e);
            });
    }
    
    @FXML
    private void crear(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarEmpFXML.fxml"));
 
            Parent usuarios = loader.load();

            AgregarEmpFXMLController ctrl = loader.getController();
            ctrl.setData(this.usuario);

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
