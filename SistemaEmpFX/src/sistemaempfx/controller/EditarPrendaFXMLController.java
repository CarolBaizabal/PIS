/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Catalogo;
import sistemaempfx.model.pojos.CatalogoPrenda;
import sistemaempfx.model.pojos.Empe;
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
    private TextField txt_categoria;
    @FXML
    private TextField txt_numPiezas;
    @FXML
    private TextField txt_serie;
    @FXML
    private TextField txt_modelo;
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
    private TextArea txt_descripcion;
    private Empe empeño;

    public String respuesta;
    Usuario usuario = null;
    CatalogoPrenda catalogo = null;
    @FXML
    private ComboBox<Catalogo> cb_subcategoria;
    private Integer[] arrayID;
    private ObservableList<Catalogo> comboBoxList;

    Prenda prenda = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxList = datosCategoria();
        cb_subcategoria.setItems(comboBoxList);
    }    
    
    public void setData(Usuario usuario, Prenda prenda, Boolean isnew){ 
        this.usuario=usuario;
        this.prenda = prenda;
        this.cargarPrenda();
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void editar(ActionEvent event) {
       
        VentanaAlert alert = new VentanaAlert();

        if (this.prenda != null) {
             int position = this.cb_subcategoria.getSelectionModel().getSelectedIndex();
            if (txt_nombre.getText().isEmpty()
            || txt_numPiezas.getText().isEmpty()
            || txt_serie.getText().isEmpty()
            || txt_modelo.getText().isEmpty()
            || txt_prestamo.getText().isEmpty()
            || txt_descripcion.getText().isEmpty()
            || position <= -1) {
                
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {
                
                this.txt_peso.setText(String.valueOf(0.0f));
                this.txt_kilataje.setText(String.valueOf(0.0f));   
                
                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("nombre", txt_nombre.getText());
                //params.put("categoria", this.catalogo.getCategoria()); PREGUNTAR
                params.put("numPiezas", Integer.parseInt(txt_numPiezas.getText()));
                params.put("serie", txt_serie.getText());
                params.put("modelo", txt_modelo.getText());
                params.put("subcategoria", cb_subcategoria.getValue());
                params.put("metal", txt_metal.getText());
                params.put("peso", Float.parseFloat(txt_peso.getText()));
                params.put("kilataje", Float.parseFloat(txt_kilataje.getText()));
                params.put("prestamo", Float.parseFloat(txt_prestamo.getText()));
                /*params.put("precioComercializacion", Integer.parseInt(txt_precioComercializacion.getText()));
                params.put("precioVenta", Integer.parseInt(txt_precioVenta.getText()));
                params.put("fechaComercializacion", txt_fechaComercializacion.getValue().toString());
                params.put("fechaVenta", txt_fechaVenta.getValue().toString());*/
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
            this.txt_numPiezas.setText("" + prenda.getNumPiezas());
            this.txt_serie.setText(prenda.getSerie());
            this.txt_modelo.setText(prenda.getModelo());
            this.txt_metal.setText(prenda.getMetal());
            this.txt_peso.setText(String.valueOf(prenda.getPeso()));
            this.txt_kilataje.setText(String.valueOf(prenda.getKilataje()));
            this.txt_prestamo.setText(String.valueOf(prenda.getPrestamo()));
            //this.txt_fechaComercializacion.setValue(date);
            //this.txt_fechaVenta.setValue(date1);
            this.txt_descripcion.setText(prenda.getDescripcion());
        } 
    }

    private ObservableList datosCategoria() {

        String respuesta = Requests.get("/catalogoprenda/getAllCatalogoPrendaActivo/");
        Gson gson = new Gson();

        TypeToken<List<Catalogo>> token = new TypeToken<List<Catalogo>>() {
        };

        List<Catalogo> listaCatalogo = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCatalogo);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
    
    @FXML
    private void si(ActionEvent event) {
        txt_kilataje.setDisable(false);
        txt_peso.setDisable(false);
        String respuesta = "Si";
        txt_metal.setText(respuesta);
        txt_peso.setText("");
        txt_kilataje.setText("");
    }

    @FXML
    private void no(ActionEvent event) {
        txt_kilataje.setDisable(true);
        txt_peso.setDisable(true);
        this.txt_peso.setText(String.valueOf(0.0f));
        this.txt_kilataje.setText(String.valueOf(0.0f));
        String respuesta = "No";
        txt_metal.setText(respuesta);
    }
}
