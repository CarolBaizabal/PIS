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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import sistemaempfx.model.pojos.Comercializacion;
import sistemaempfx.model.pojos.Usuario;
/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class ComercializacionFXMLController implements Initializable {
    @FXML
    private TextField txt_usuario;
    @FXML
    private TableColumn<Comercializacion, String> tc_fechaCreacion;
    @FXML
    private TableColumn<Comercializacion, String> tc_usuario;
    @FXML
    private TableColumn<Comercializacion, String> tb_fechaInicioBusqueda;
    @FXML
    private TableColumn<Comercializacion, String> tc_observaciones;
    @FXML
    private TableColumn<Comercializacion, String> tc_metal;
    @FXML
     private TableView<Comercializacion> tb_comercializacion;
    @FXML
    private TableColumn<Comercializacion, String> tb_fechaFinBusqueda;

    Comercializacion comercializacion = null;
    Usuario usuario= null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }   
     public void setData(Usuario usuario){       
        this.usuario = usuario;
    }
    
    public void cargarTabla() {
        String respuesta = "";
        tb_comercializacion.getItems().clear();

        respuesta = Requests.get("/comercializacion/getAllComercializacion/");
        Gson gson = new Gson();

        TypeToken<List<Comercializacion>> token = new TypeToken<List<Comercializacion>>() {
        };
        List<Comercializacion> listaComercializacion = gson.fromJson(respuesta, token.getType());

        tc_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tb_fechaInicioBusqueda.setCellValueFactory(new PropertyValueFactory<>("fechaInicioBusqueda"));
        tb_fechaFinBusqueda.setCellValueFactory(new PropertyValueFactory<>("fechaFinBusqueda"));
        tc_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tc_metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
        
        listaComercializacion.forEach(e -> {
            tb_comercializacion.getItems().add(e);
        });
    }

    @FXML
    private void buscar(ActionEvent event) {
        if (this.txt_usuario.getText().isEmpty()) {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Ingrese una busqueda...");
            alertI.showAndWait();
        } else {
            String buscar = this.txt_usuario.getText();
            tb_comercializacion.getItems().clear();
            List<Comercializacion> listaComercializacion = null;

            // Actual
            String respuesta = "";

            respuesta = Requests.get("/comercializacion/buscarComercializacionPorFecha/" + buscar);
            Gson gson = new Gson();

            TypeToken<List<Comercializacion>> token = new TypeToken<List<Comercializacion>>() {
            };
            listaComercializacion = gson.fromJson(respuesta, token.getType());
            if(listaComercializacion.size()>0){
            tc_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
            tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tb_fechaInicioBusqueda.setCellValueFactory(new PropertyValueFactory<>("fechaInicioBusqueda"));
            tb_fechaFinBusqueda.setCellValueFactory(new PropertyValueFactory<>("fechaFinBusqueda"));
            tc_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
            tc_metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
            listaComercializacion.forEach(e -> {
                tb_comercializacion.getItems().add(e);
            });
            } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("No hay resultados...");
            alert.showAndWait();
            this.cargarTabla();
            }
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        this.txt_usuario.setText("");
        this.cargarTabla();
    }

    @FXML
    private void observaciones(ActionEvent event) {
        this.comercializacion= tb_comercializacion.getSelectionModel().getSelectedItem();
         if (this.comercializacion != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarComercializacionFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarComercializacionFXMLController ctrl = loader.getController();
                
                ctrl.setData(this.comercializacion, this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.cargarTabla();
                this.comercializacion = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una comercializacion...");
            alert.showAndWait();
        }
    }

    @FXML
    private void vender(ActionEvent event) {
    }

    @FXML
    private void asignarFecha(ActionEvent event) throws IOException {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/FechaFXML.fxml"));

        Parent formUsuarioEditar = loader.load();

        FechaFXMLController ctrl = loader.getController();

        Scene scene = new Scene(formUsuarioEditar);
        stage.setScene(scene);
        stage.setTitle("Asignar");
        stage.setResizable(false);
        stage.showAndWait();
        this.cargarDatosPorFechas(ctrl.getFechaInicio(), ctrl.getFechaFin());
    }
    
    private void cargarDatosPorFechas(LocalDate fechaInicio, LocalDate fechaFin){
        if(fechaInicio != null && fechaFin != null){
            String respuesta = "";
            tb_comercializacion.getItems().clear();
            String inicio = fechaInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String fin = fechaFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            respuesta = Requests.get("/comercializacion/buscarConsultasPorFecha/" + inicio + "/" + fin);
            Gson gson = new Gson();

            TypeToken<List<Comercializacion>> token = new TypeToken<List<Comercializacion>>() {
            };
            List<Comercializacion> listaComercializacion = gson.fromJson(respuesta, token.getType());
            if (listaComercializacion.size()>0){
                tc_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
                tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
                tb_fechaInicioBusqueda.setCellValueFactory(new PropertyValueFactory<>("fechaInicioBusqueda"));
                tb_fechaFinBusqueda.setCellValueFactory(new PropertyValueFactory<>("fechaFinBusqueda"));
                tc_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
                tc_metal.setCellValueFactory(new PropertyValueFactory<>("metal"));
                    listaComercializacion.forEach(e -> {
                   tb_comercializacion.getItems().add(e);
                   });
            }else{
            this.cargarTabla();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("No hay resultados...");
            alert.showAndWait();
            }
        }
    }

    public void actualizarTabla(List<Comercializacion> comercializaciones) {
    // Realiza la lógica para actualizar la tabla con la lista de comercializaciones recibida
    // ...
    }
}
