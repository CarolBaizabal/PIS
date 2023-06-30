/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Permiso;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class PermisosFXMLController implements Initializable {

    @FXML
    private Button bt_restringirPermiso;
    @FXML
    private Button bt_asignarPermiso;
    @FXML
    private TableColumn<Permiso, String> tcl_rol;
    @FXML
    private TableColumn<Permiso, Boolean> tcl_estatus;
    @FXML
    private TableView<Permiso> tb_permiso;
    @FXML
    private TableColumn<Permiso, String> tcl_permiso;

    Permiso permiso = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }

    @FXML
    private void desactivar(ActionEvent event) {
        this.permiso = tb_permiso.getSelectionModel().getSelectedItem();
        if (this.permiso != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea desactivar el permiso?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        Boolean estado = this.permiso.getEstatus();

                        if (estado) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("estatus", false);
                            String respuesta = Requests.put("/usuario/actualizarPermiso/" + permiso.getIdPermiso(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.permiso = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.permiso = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El permiso ya esta inactivo...");
                            alertInactivo.showAndWait();
                            this.permiso = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.permiso = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un permiso...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void activar(ActionEvent event) {
        
        this.permiso = tb_permiso.getSelectionModel().getSelectedItem();
        if (this.permiso != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea activar el permiso?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        Boolean estado = this.permiso.getEstatus();

                        if (!estado) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("estatus", true);
                            String respuesta = Requests.put("/usuario/actualizarPermiso/" + permiso.getIdPermiso(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.permiso = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.permiso = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El permiso está activo, renicie sesion para aplicar los cambios...");
                            alertInactivo.showAndWait();
                            this.permiso = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.permiso = null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un permiso...");
            alertI.showAndWait();
        }
    }

    private void cargarTabla() {

        String respuesta = "";
        tb_permiso.getItems().clear();

        respuesta = Requests.get("/usuario/getAllPermisos/");
        Gson gson = new Gson();

        TypeToken<List<Permiso>> token = new TypeToken<List<Permiso>>() {
        };

        List<Permiso> listaPermiso = gson.fromJson(respuesta, token.getType());

        tcl_permiso.setCellValueFactory(new PropertyValueFactory<>("permiso"));
        tcl_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        tcl_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        //Aqui se coloca el chek de la tabla 
        this.tcl_estatus.setCellValueFactory(cell -> {
            Permiso p = cell.getValue();
            return new ReadOnlyBooleanWrapper(p.getEstatus());
        });
        this.tcl_estatus.setCellFactory(CheckBoxTableCell.forTableColumn(this.tcl_estatus));

        listaPermiso.forEach(e -> {
            tb_permiso.getItems().add(e);
        });

    }

}
