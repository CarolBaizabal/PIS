/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Prenda;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarPrendaFXMLController implements Initializable {

    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_categoria;
    @FXML
    private TextField txt_numPiezas;
    @FXML
    private TextField txt_serie;
    @FXML
    private TextField txt_modelo;
    @FXML
    private TextField txt_subcategoria;
    @FXML
    private TextField txt_metal;
    @FXML
    private TextField txt_peso;
    @FXML
    private TextField txt_kilataje;
    @FXML
    private TextField txt_prestamo;
    @FXML
    private TextField txt_precioComercializacion;
    @FXML
    private TextField txt_precioVenta;
    @FXML
    private TextField txt_estatus;
    @FXML
    private DatePicker txt_fechaComercializacion;
    @FXML
    private DatePicker txt_fechaVenta;
    @FXML
    private TextArea txt_descripcion;

    Usuario usuario = null;
    Prenda prenda = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setData(Usuario usuario, Prenda prenda, Boolean isnew){ 
        this.usuario=usuario;
        this.prenda = prenda;
        this.cargarPrenda();
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void editar(ActionEvent event) {
       
        VentanaAlert alert = new VentanaAlert();

        if (this.prenda != null) {
            if (txt_nombre.getText().isEmpty()
                || txt_categoria.getText().isEmpty()
                || txt_numPiezas.getText().isEmpty()
                || txt_serie.getText().isEmpty()
                || txt_modelo.getText().isEmpty()
                || txt_subcategoria.getText().isEmpty()
                || txt_metal.getText().isEmpty()
                || txt_peso.getText().isEmpty()
                || txt_kilataje.getText().isEmpty()
                || txt_prestamo.getText().isEmpty()
                || txt_precioComercializacion.getText().isEmpty()
                || txt_precioVenta.getText().isEmpty()
                || txt_estatus.getText().isEmpty()
                || txt_fechaComercializacion.getValue() == null
                || txt_fechaVenta.getValue() == null
                || txt_descripcion.getText().isEmpty()) {

                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {
                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("nombre", txt_nombre.getText());
                params.put("categoria", txt_categoria.getText());
                params.put("numPiezas", Integer.parseInt(txt_numPiezas.getText()));
                params.put("serie", txt_serie.getText());
                params.put("modelo", txt_modelo.getText());
                params.put("subcategoria", txt_subcategoria.getText());
                params.put("metal", txt_metal.getText());
                params.put("peso", Integer.parseInt(txt_peso.getText()));
                params.put("kilataje", Integer.parseInt(txt_kilataje.getText()));
                params.put("prestamo", Integer.parseInt(txt_peso.getText()));
                params.put("precioComercializacion", Integer.parseInt(txt_precioComercializacion.getText()));
                params.put("precioVenta", Integer.parseInt(txt_precioVenta.getText()));
                params.put("estatus", txt_estatus.getText());
                params.put("fechaComercializacion", txt_fechaComercializacion.getValue().toString());
                params.put("fechaVenta", txt_fechaVenta.getValue().toString());
                params.put("descripcion", txt_descripcion.getText());

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar la prenda?");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            String respuesta = Requests.put("/prenda/actualizarPrenda/" + prenda.getIdPrendas(), params);
                            JSONObject dataJson = new JSONObject(respuesta);

                            if (!dataJson.getBoolean("errorRespuesta")) {
                                alert.information("Informativo", dataJson.getString("mensaje"));
                                Window.close(event);
                            } else {
                                alert.warning("Advertencia", dataJson.getString("mensaje"));
                                Window.close(event);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarPrendaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia", "Debe seleccionar un elemetno...");
        }
    }
    
    public void cargarPrenda() {
        if (this.usuario.getRol().equals("Administrador")) {
            //LocalDate date = LocalDate.parse(prenda.getFechaComercializacion(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //LocalDate date1 = LocalDate.parse(prenda.getFechaVenta(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.txt_nombre.setText(prenda.getNombre());
            this.txt_categoria.setText(prenda.getCategoria());
            this.txt_numPiezas.setText("" + prenda.getNumPiezas());
            this.txt_serie.setText(prenda.getSerie());
            this.txt_modelo.setText(prenda.getModelo());
            this.txt_subcategoria.setText(prenda.getSubcategoria());
            this.txt_metal.setText(prenda.getMetal());
            this.txt_peso.setText(String.valueOf(prenda.getPeso()));
            this.txt_kilataje.setText(String.valueOf(prenda.getKilataje()));
            this.txt_prestamo.setText(String.valueOf(prenda.getPrestamo()));
            this.txt_precioComercializacion.setText(String.valueOf(prenda.getPrecioComercializacion()));
            this.txt_precioVenta.setText(String.valueOf(prenda.getPrecioVenta()));
            this.txt_estatus.setText(prenda.getEstatus());
            //this.txt_fechaComercializacion.setValue(date);
            //this.txt_fechaVenta.setValue(date1);
            this.txt_descripcion.setText(prenda.getDescripcion());
        } 
    }
}
