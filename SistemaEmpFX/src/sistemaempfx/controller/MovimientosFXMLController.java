package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Egreso;
import sistemaempfx.model.pojos.Ingreso;
import sistemaempfx.model.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class MovimientosFXMLController implements Initializable {
    @FXML
    private TextField txt_usuario;
    @FXML
    private TextField txt_usuario1;
    @FXML
    private TableView<Ingreso> tb_Ingreso;
    @FXML
    private TableColumn<Ingreso, ?> tcl_cantidad1I;
    @FXML
    private TableColumn<Ingreso, ?> tcl_motivoI;
    @FXML
    private TableColumn<Ingreso, ?> tcl_observacionesI;
    @FXML
    private TableColumn<Ingreso, ?> tcl_fechaCreacionI;
    @FXML
    private TableColumn<Ingreso, ?> tcl_usuarioI;
    @FXML
    private TableColumn<Ingreso, ?> tcl_fechaModificacionI;
    @FXML
    private TableColumn<Ingreso, ?> tcl_usuarioAI;
    @FXML
    private TableView<Egreso> tb_egreso;
    @FXML
    private TableColumn<Egreso, ?> tcl_cantidad;
    @FXML
    private TableColumn<Egreso, ?> tcl_motivo;
    @FXML
    private TableColumn<Egreso, ?> tcl_observaciones;
    @FXML
    private TableColumn<Egreso, ?> tcl_fechaCreacion;
    @FXML
    private TableColumn<Egreso, ?> tcl_usuario;
    @FXML
    private TableColumn<Egreso, ?> tcl_fechaModificacion;
    @FXML
    private TableColumn<Egreso, ?> tcl_usuarioA;
    
    Egreso egreso = null;
    Ingreso ingreso = null;
    Usuario usuario = null;
    Categoria categoria = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tablaIngreso();
        tablaEgreso();
    }    
    
    public void setData(Usuario usuario){       
        this.usuario = usuario;
    }

    private void tablaIngreso() {

        String respuesta = "";
        tb_Ingreso.getItems().clear();

        respuesta = Requests.get("/ingreso/getAllIngreso/");
        Gson gson = new Gson();

        TypeToken<List<Ingreso>> token = new TypeToken<List<Ingreso>>() {
        };

        List<Ingreso> listaIngreso= gson.fromJson(respuesta, token.getType());

        tcl_cantidad1I.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_motivoI.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observacionesI.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacionI.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuarioI.setCellValueFactory(new PropertyValueFactory<>("usuario"));       
        tcl_fechaModificacionI.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_usuarioAI.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));
         
        listaIngreso.forEach(e -> {
            tb_Ingreso.getItems().add(e);
        });

    }
    
    private void clickTableIngreso(MouseEvent event) {
        String respuesta = "";
        if (tb_Ingreso.getSelectionModel().getSelectedItem() != null) {
            ingreso = tb_Ingreso.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    private void agregarIngreso(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarIngresoFXML.fxml"));

            Parent usuarios = loader.load();

            AgregarIngresoFXMLController ctrl = loader.getController();
            ctrl.setData(this.usuario);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaIngreso();
            
        } catch (IOException ex) {
            Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarIngreso(ActionEvent event) {
        this.ingreso= tb_Ingreso.getSelectionModel().getSelectedItem();
         if (this.ingreso != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarIngresoFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarIngresoFXMLController ctrl = loader.getController();
                
                ctrl.setData(this.ingreso, this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaIngreso();
                this.ingreso = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un egreso...");
            alert.showAndWait();
        }
    }

    @FXML
    private void buscarIngreso(ActionEvent event) {
        if(this.txt_usuario.getText().isEmpty()){
        Alert alertI = new Alert(Alert.AlertType.WARNING);
        alertI.setTitle("Advertencia");
        alertI.setHeaderText(null);
        alertI.setContentText("Ingrese una busqueda...");
        alertI.showAndWait();
        }else{
        String buscar = this.txt_usuario.getText();
        tb_Ingreso.getItems().clear();
        List<Ingreso> listaIngreso = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/ingreso/buscarIngreso/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Ingreso>> token = new TypeToken<List<Ingreso>>() {
        };
        listaIngreso = gson.fromJson(respuesta, token.getType());

        tcl_cantidad1I.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_motivoI.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observacionesI.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacionI.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuarioI.setCellValueFactory(new PropertyValueFactory<>("usuario"));       
        tcl_fechaModificacionI.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_usuarioAI.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));
        
        listaIngreso.forEach(e -> {
            tb_Ingreso.getItems().add(e);
        });
        }
    }


    @FXML
    private void limpiarIngreso(ActionEvent event) {
        tablaIngreso();
        this.txt_usuario.setText("");
    }

    private void tablaEgreso() {

        String respuesta = "";
        tb_egreso.getItems().clear();

        respuesta = Requests.get("/egreso/getAllEgreso/");
        Gson gson = new Gson();

        TypeToken<List<Egreso>> token = new TypeToken<List<Egreso>>() {
        };

        List<Egreso> listaEgreso= gson.fromJson(respuesta, token.getType());

        tcl_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_motivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_fechaModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_usuarioA.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));
        
        listaEgreso.forEach(e -> {
            tb_egreso.getItems().add(e);
        });

    }
    
    private void clickTableEgreso(MouseEvent event) {
        String respuesta = "";
        if (tb_egreso.getSelectionModel().getSelectedItem() != null) {
            egreso = tb_egreso.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    private void agregarEgreso(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarEgresoFXML.fxml"));

            Parent usuarios = loader.load();

            AgregarEgresoFXMLController ctrl = loader.getController();
            ctrl.setData(this.usuario, this.categoria, this.egreso);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaEgreso();
            
        } catch (IOException ex) {
            Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarEgreso(ActionEvent event) {
        this.egreso= tb_egreso.getSelectionModel().getSelectedItem();
         if (this.egreso != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarEgresoFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarEgresoFXMLController ctrl = loader.getController();
                
                ctrl.setData(this.egreso, this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaEgreso();
                this.egreso = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un egreso...");
            alert.showAndWait();
        }
    }

    @FXML
    private void buscarEgreso(ActionEvent event) {
        if(this.txt_usuario.getText().isEmpty()){
        Alert alertI = new Alert(Alert.AlertType.WARNING);
        alertI.setTitle("Advertencia");
        alertI.setHeaderText(null);
        alertI.setContentText("Ingrese una busqueda...");
        alertI.showAndWait();
        }else{
        String buscar = this.txt_usuario1.getText();
        tb_egreso.getItems().clear();
        List<Egreso> listaEgreso = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/egreso/buscarEgreso/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Egreso>> token = new TypeToken<List<Egreso>>() {
        };
        listaEgreso = gson.fromJson(respuesta, token.getType());

        tcl_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_motivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_fechaModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));        
        tcl_usuarioA.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));
        
        listaEgreso.forEach(e -> {
            tb_egreso.getItems().add(e);
        });
        }
    }

    @FXML
    private void limpiarEgreso(ActionEvent event) {
        tablaEgreso();
        this.txt_usuario1.setText("");
    }

    @FXML
    private void eliminarEgreso(ActionEvent event) {
        this.egreso= tb_egreso.getSelectionModel().getSelectedItem();
        if (this.egreso != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el egreso?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        
                        String estado = this.egreso.getEstatus();

                        if ("Activo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idEgreso", this.egreso.getEstatus());
                            String respuesta = Requests.delete("/egreso/eliminarEgreso/" + egreso.getIdEgreso(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.egreso = null;
                                this.tablaEgreso();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.egreso = null;
                                this.tablaEgreso();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El egreso ya esta eliminado...");
                            alertInactivo.showAndWait();
                            this.getAllIngreso();
                            this.egreso = null;
                       
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.egreso = null;
                    this.tablaEgreso();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un egreso...");
            alertI.showAndWait();
        }
    }
    
    private ObservableList getAllEgreso() {
        String buscar = this.txt_usuario.getText();
        tb_egreso.getItems().clear();
        List<Egreso> listaEgreso = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/egreso/getAllEgresoActivo/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Egreso>> token = new TypeToken<List<Egreso>>() {
        };
        listaEgreso = gson.fromJson(respuesta, token.getType());

        tcl_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_motivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_fechaModificacion.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));        
        tcl_usuarioA.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));
        
        listaEgreso.forEach(e -> {
            tb_egreso.getItems().add(e);
        });
        return FXCollections.observableArrayList(listaEgreso);
    }

    @FXML
    private void eliminarIngreso(ActionEvent event) {
        this.ingreso= tb_Ingreso.getSelectionModel().getSelectedItem();
        if (this.ingreso != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea eliminar el ingreso?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        
                        String estado = this.ingreso.getEstatus();

                        if ("Activo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idUsuario", this.ingreso.getEstatus());
                            String respuesta = Requests.delete("/ingreso/eliminarIngreso/" + ingreso.getIdIngreso(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.ingreso = null;
                                this.tablaIngreso();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.ingreso = null;
                                this.tablaIngreso();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El ingreso ya esta eliminado...");
                            alertInactivo.showAndWait();
                            this.getAllIngreso();
                            this.ingreso = null;
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.ingreso = null;
                    this.tablaIngreso();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un ingreso...");
            alertI.showAndWait();
        }
    }    
    
    private ObservableList getAllIngreso() {
        String buscar = this.txt_usuario.getText();
        tb_Ingreso.getItems().clear();
        List<Ingreso> listaIngreso = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/ingreso/getAllIngresoActivo/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Ingreso>> token = new TypeToken<List<Ingreso>>() {
        };
        listaIngreso = gson.fromJson(respuesta, token.getType());

        tcl_cantidad1I.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        tcl_motivoI.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        tcl_observacionesI.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        tcl_fechaCreacionI.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuarioI.setCellValueFactory(new PropertyValueFactory<>("usuario"));       
        tcl_fechaModificacionI.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
        tcl_usuarioAI.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));
        
        listaIngreso.forEach(e -> {
            tb_Ingreso.getItems().add(e);
        });
        return FXCollections.observableArrayList(listaIngreso);
    }
}
