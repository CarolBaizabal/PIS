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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.model.pojos.VentasRemates;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class VentasRematesFXMLController implements Initializable {
    @FXML
    private TextField txt_usuario;
    @FXML
    private TableColumn<VentasRemates, Float> tc_Subtotal;
    @FXML
    private TableColumn<VentasRemates, Float> tc_iva;
    @FXML
    private TableColumn<VentasRemates, Float> tc_total;
    @FXML
    private TableColumn<VentasRemates, String> tc_estatus;
    @FXML
    private TableColumn<VentasRemates, String> tc_cliente;
    @FXML
    private TableColumn<VentasRemates, String> tc_fechaVenta;
    @FXML
    private TableColumn<VentasRemates, Float> tc_totalPrenda;
    @FXML
    private TableColumn<VentasRemates, String> tc_fechaCancelacion;
    @FXML
    private TableColumn<VentasRemates, String> tc_usuarioCancelo;
    @FXML
    private TableColumn<VentasRemates, String> tc_tipo;
    @FXML
    private TableView<VentasRemates> tb_ventasRemates;

    Usuario usuario = null;
    VentasRemates ventasRemates= null;
    @FXML
    private TableColumn<?, ?> tc_usuario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cargarTabla();
    }    

    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        this.ventasRemates = tb_ventasRemates.getSelectionModel().getSelectedItem();
        if (this.ventasRemates != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea cancelar la prenda?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.ventasRemates.getEstatus();

                        if ("Venta".equals(estado)||"Remate".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idventasRemates", this.ventasRemates.getIdVentasRemates());
                            params.put("usuarioCancelar", this.usuario.getNombre());
                            
                            String respuesta = Requests.delete("/ventasremates/eliminarVentasRemates/" + ventasRemates.getIdVentasRemates(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.ventasRemates = null;
                                this.cargarTabla();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.ventasRemates = null;
                                this.cargarTabla();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El usuario ya esta inactivo...");
                            alertInactivo.showAndWait();
                            this.ventasRemates = null;
                            this.cargarTabla();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.ventasRemates= null;
                    this.cargarTabla();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione una prenda...");
            alertI.showAndWait();
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
        
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        this.txt_usuario.setText("");
        this.cargarTabla();
    }

    public void cargarTabla(){
        String respuesta = "";
        tb_ventasRemates.getItems().clear();

        respuesta = Requests.get("/ventasremates/getAllVentasRemates/");
        Gson gson = new Gson();

        TypeToken<List<VentasRemates>> token = new TypeToken<List<VentasRemates>>() {
        };
        List<VentasRemates> listaVentasRemates = gson.fromJson(respuesta, token.getType());

        tc_Subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
        tc_iva.setCellValueFactory(new PropertyValueFactory<>("iva"));
        tc_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tc_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tc_cliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        tc_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tc_fechaVenta.setCellValueFactory(new PropertyValueFactory<>("fechaVenta"));
        tc_totalPrenda.setCellValueFactory(new PropertyValueFactory<>("totalPrendas"));
        tc_fechaCancelacion.setCellValueFactory(new PropertyValueFactory<>("fechaCancelacion"));
        tc_usuarioCancelo.setCellValueFactory(new PropertyValueFactory<>("usuarioCancelar"));
        tc_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        listaVentasRemates.forEach(e -> {
            tb_ventasRemates.getItems().add(e);
        });
    }

    private void clickTableUsuario(MouseEvent event) {
        String respuesta = "";
        if (tb_ventasRemates.getSelectionModel().getSelectedItem() != null) {
            ventasRemates = tb_ventasRemates.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    private void vender(ActionEvent event) {
    }

    @FXML
    private void remate(ActionEvent event) {
    }

    
}
