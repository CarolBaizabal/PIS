/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.MouseEvent;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.CatalogoPrenda;
import sistemaempfx.model.pojos.Prenda;
import sistemaempfx.model.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class PrendasFXMLController implements Initializable {

    @FXML
    private TableView<Prenda> tb_Prenda;
    @FXML
    private TableColumn<Prenda, Integer> tb_emp;
    @FXML
    private TableColumn<Prenda, String> tb_nombre;
    @FXML
    private TableColumn<Prenda, String> tb_catalogo;
    @FXML
    private TableColumn<Prenda, String> tb_categoria;
    @FXML
    private TableColumn<Prenda, Integer> tb_piezas;
    @FXML
    private TableColumn<Prenda, String> tb_serie;
    @FXML
    private TableColumn<Prenda, String> tb_modelo;
    @FXML
    private TableColumn<Prenda, String> tb_descripcion;
    @FXML
    private TableColumn<Prenda, String> tb_metal;
    @FXML
    private TableColumn<Prenda, Integer> tb_peso;
    @FXML
    private TableColumn<Prenda, Integer> tb_kilataje;
    @FXML
    private TableColumn<Prenda, Integer> tb_prestamo;
    @FXML
    private TableColumn<Prenda, Integer> tb_comercializacion;
    @FXML
    private TableColumn<Prenda, Integer> tb_venta;
    @FXML
    private TableColumn<Prenda, String> tb_estatus;
    private TableColumn<Prenda, String> tb_claveC;
    @FXML
    private TableColumn<Prenda, String> fechaCreacion;
    @FXML
    private TableColumn<Prenda, String> tb_fechaC;
    private TableColumn<Prenda, String> tb_fechaVent;
    @FXML
    private TableColumn<Prenda, String> tb_usuario;
    @FXML
    private TextField txt_usuario;

    Prenda prenda = null;
    Usuario usuario = null;
    CatalogoPrenda catalogo = null;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaPrenda();
    }    

    public void setData(Usuario usuario, Prenda prenda) {
        this.usuario = usuario;
        this.prenda = prenda;
    }

    private void tablaPrenda() {

        String respuesta = "";
        tb_Prenda.getItems().clear();

        respuesta = Requests.get("/prenda/getAllPrenda/");
        Gson gson = new Gson();

        TypeToken<List<Prenda>> token = new TypeToken<List<Prenda>>() {
        };

        List<Prenda> listaPrenda = gson.fromJson(respuesta, token.getType());

            tb_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tb_emp.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
            tb_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tb_piezas.setCellValueFactory(new PropertyValueFactory<>("numPiezas"));
            tb_serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
            tb_modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            tb_catalogo.setCellValueFactory(new PropertyValueFactory<>("subcategoria"));
            tb_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tb_metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
            tb_peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
            tb_kilataje.setCellValueFactory(new PropertyValueFactory<>("kilataje"));
            tb_prestamo.setCellValueFactory(new PropertyValueFactory<>("prestamo"));
            tb_comercializacion.setCellValueFactory(new PropertyValueFactory<>("precioComercializacion"));
            tb_venta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
            tb_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
            fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
            tb_fechaC.setCellValueFactory(new PropertyValueFactory<>("fechaComercializacion"));
            tb_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        listaPrenda.forEach(e -> {
            tb_Prenda.getItems().add(e);
        });

    }

    private void clickTableUsuario(MouseEvent event) {
        String respuesta = "";
        if (tb_Prenda.getSelectionModel().getSelectedItem() != null) {
            prenda = tb_Prenda.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void buscar(ActionEvent event) {
        if(this.txt_usuario.getText().isEmpty()){
        Alert alertI = new Alert(Alert.AlertType.WARNING);
        alertI.setTitle("Advertencia");
        alertI.setHeaderText(null);
        alertI.setContentText("Ingrese una busqueda...");
        alertI.showAndWait();
        }else{
        String buscar = this.txt_usuario.getText();
        tb_Prenda.getItems().clear();
        List<Prenda> listaPrenda = null;

        String respuesta = "";

        respuesta = Requests.get("/prenda/buscarPrenda/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Prenda>> token = new TypeToken<List<Prenda>>() {
        };
        listaPrenda = gson.fromJson(respuesta, token.getType());

            tb_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tb_emp.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
            tb_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            tb_piezas.setCellValueFactory(new PropertyValueFactory<>("numPiezas"));
            tb_serie.setCellValueFactory(new PropertyValueFactory<>("serie"));
            tb_modelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
            tb_catalogo.setCellValueFactory(new PropertyValueFactory<>("subcategoria"));
            tb_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tb_metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
            tb_peso.setCellValueFactory(new PropertyValueFactory<>("peso"));
            tb_kilataje.setCellValueFactory(new PropertyValueFactory<>("kilataje"));
            tb_prestamo.setCellValueFactory(new PropertyValueFactory<>("prestamo"));
            tb_comercializacion.setCellValueFactory(new PropertyValueFactory<>("precioComercializacion"));
            tb_venta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
            tb_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
            tb_claveC.setCellValueFactory(new PropertyValueFactory<>("idComercializacion"));
            fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
            tb_fechaC.setCellValueFactory(new PropertyValueFactory<>("fechaComercializacion"));
            tb_fechaVent.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
            tb_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        listaPrenda.forEach(e -> {
            tb_Prenda.getItems().add(e);
        });
        }
    }

    @FXML
    private void Limpiar(ActionEvent event) {
        txt_usuario.setText("");
        this.tablaPrenda();
    }

    @FXML
    private void editar(ActionEvent event) {
        this.prenda = tb_Prenda.getSelectionModel().getSelectedItem();
        if (this.prenda != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarPrendaFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarPrendaFXMLController ctrl = loader.getController();
                ctrl.setData(this.usuario, this.prenda, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaPrenda();
                this.prenda = null;
            } catch (IOException ex) {
                Logger.getLogger(PrendasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una prenda...");
            alert.showAndWait();
        }
    }

    private void agregar(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarPrendaFXML.fxml"));
 
            Parent usuarios = loader.load();

            AgregarPrendaFXMLController ctrl = loader.getController();
            ctrl.setData(this.usuario, this.catalogo);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaPrenda();

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
