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
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class EmpFXMLController implements Initializable {
    @FXML
    private BorderPane pnl_principal;
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
     Usuario usuario = null;
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
    @FXML
    private TableColumn<Empe, Float> tc_interesP;
    @FXML
    private TableColumn<Empe, Float> tc_almacenajeP;
    @FXML
    private Button btn_observaciones;
    @FXML
    private Button btn_Comercializacion;
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
    private void cancelar(ActionEvent event) {
        
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
            String  buscar = txt_usuario.getText();
            tb_emp.getItems().clear();
            List<Empe> listaEmpe = null;

            // Actual
            String respuesta = "";

            respuesta = Requests.get("/emp/buscarEmp/" + buscar );
            Gson gson = new Gson();

            TypeToken<List<Empe>> token = new TypeToken<List<Empe>>() {
            };
            listaEmpe = gson.fromJson(respuesta, token.getType());

            tc_cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
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

            tc_cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
            tc_creacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
            tc_observaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
            tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
            tc_contrato.setCellValueFactory(new PropertyValueFactory<>("idContrato"));
            tc_fechaActualizacion.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));
            tc_interesP.setCellValueFactory(new PropertyValueFactory<>("interesPorcentaje"));
            tc_interes.setCellValueFactory(new PropertyValueFactory<>("interes"));
            tc_almacenajeP.setCellValueFactory(new PropertyValueFactory<>("almacenajePorcentaje"));
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
    
    private void clickTableEmp (MouseEvent event) {
        String respuesta = "";
        if (tb_emp.getSelectionModel().getSelectedItem() != null) {
            emp = tb_emp.getSelectionModel().getSelectedItem();
        }
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
            this.cargarTabla();

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrendar(ActionEvent event) {
        this.emp = tb_emp.getSelectionModel().getSelectedItem();
        if (this.emp != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea  refrendar el contrato?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.emp.getEstatus();

                        if ("Refrendado".equals(estado)||"Vigente".equals(estado)||"Espera".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idEmp", this.emp.getIdEmp());
                          
                            String respuesta = Requests.put("/emp/refrendarEmp/" + emp.getIdEmp(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.emp = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.emp = null;
                                this.cargarTabla();
                            }
                        } else if ("Cancelado".equals(estado)||"Finiquitado".equals(estado)||"Comercializado".equals(estado)||"Inconcluso".equals(estado)) {
                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert1.setTitle("Error");
                            alert1.setHeaderText(null);
                            alert1.setContentText("El contrato ya se encuentra finiquitado o cancelado...");
                            alert1.showAndWait();
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("Se finiquitó el contrato...");
                            alertInactivo.showAndWait();
                            this.emp = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.emp  = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un empeño...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void extension(ActionEvent event) {
        this.emp = tb_emp.getSelectionModel().getSelectedItem();
        if (this.emp != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea hacer una espera el contrato?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.emp.getEstatus();

                        if ("Refrendado".equals(estado)||"Vigente".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idEmp", this.emp.getIdEmp());
                          
                            String respuesta = Requests.put("/emp/agregarExtension/" + emp.getIdEmp(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.emp = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.emp = null;
                                this.cargarTabla();
                            }
                        } else if ("Cancelado".equals(estado)||"Finiquitado".equals(estado)||"Comercializado".equals(estado)||"Inconcluso".equals(estado)) {
                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert1.setTitle("Error");
                            alert1.setHeaderText(null);
                            alert1.setContentText("El contrato ya se encuentra finiquitado o cancelado...");
                            alert1.showAndWait();
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("Se finiquitó el contrato...");
                            alertInactivo.showAndWait();
                            this.emp = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.emp  = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un empeño...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void finiquitar(ActionEvent event) {
        this.emp = tb_emp.getSelectionModel().getSelectedItem();
        if (this.emp != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea  finiquitar el contrato?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.emp.getEstatus();

                        if ("Refrendado".equals(estado)||"Vigente".equals(estado)||"Espera".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idEmp", this.emp.getIdEmp());
                            params.put("usuario", this.usuario.getNombre());
                            
                            params.put("subtotal", this.emp.getInteres() * this.emp.getAlmacenaje());
                            params.put("iva", 0.16f*(this.emp.getInteres() * this.emp.getAlmacenaje()));
                            params.put("total", (this.emp.getInteres() * this.emp.getAlmacenaje()) * .016f + (this.emp.getInteres() * this.emp.getAlmacenaje()));
                          
                            String respuesta = Requests.put("/emp/finiquitar/" + emp.getIdEmp(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.emp = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.emp = null;
                                this.cargarTabla();
                            }
                        } else if ("Cancelado".equals(estado)||"Finiquitado".equals(estado)||"Comercializado".equals(estado)||"Inconcluso".equals(estado)) {
                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert1.setTitle("Error");
                            alert1.setHeaderText(null);
                            alert1.setContentText("El contrato ya se encuentra finiquitado o cancelado...");
                            alert1.showAndWait();
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("Se finiquitó el contrato...");
                            alertInactivo.showAndWait();
                            this.emp = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.emp  = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un empeño...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void observaciones(ActionEvent event) {
        this.emp = tb_emp.getSelectionModel().getSelectedItem();
         if (this.emp != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarEmpFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarEmpFXMLController ctrl = loader.getController();
                
                ctrl.setData(this.emp, this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.cargarTabla();
                this.emp = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un empeño...");
            alert.showAndWait();
        }
    }

    @FXML
    private void comercializar(ActionEvent event) {
        this.emp = tb_emp.getSelectionModel().getSelectedItem();
        if (this.emp != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea  comercializar el contrato?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.emp.getEstatus();

                        if ("Refrendado".equals(estado)||"Vigente".equals(estado)||"Espera".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idEmp", this.emp.getIdEmp());
                            params.put("usuario", this.usuario.getNombre());
   
                            String respuesta = Requests.put("/emp/comercializar/" + emp.getIdEmp(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.emp = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.emp = null;
                                this.cargarTabla();
                            }
                        } else if ("Cancelado".equals(estado)||"Finiquitado".equals(estado)||"Comercializado".equals(estado)||"Inconcluso".equals(estado)) {
                            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                            alert1.setTitle("Error");
                            alert1.setHeaderText(null);
                            alert1.setContentText("El contrato ya se encuentra finiquitado o cancelado...");
                            alert1.showAndWait();
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("Se comercializó el contrato...");
                            alertInactivo.showAndWait();
                            this.emp = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.emp  = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un empeño...");
            alertI.showAndWait();
        }
    }
    
}
