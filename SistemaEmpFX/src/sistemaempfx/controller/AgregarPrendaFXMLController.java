package sistemaempfx.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

public class AgregarPrendaFXMLController implements Initializable {

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
    private TextField txt_estatus;
    @FXML
    private DatePicker txt_fechaComercializacion;
    @FXML
    private DatePicker txt_fechaVenta;
    @FXML
    private TextArea txt_descripcion;
    private Empe empeño;

    
    Usuario usuario = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setEmpeño(Empe empeño){
        this.empeño = empeño;
    }
    
     public void setData(Usuario usuario) {
        this.usuario = usuario;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void agregar(ActionEvent event) {
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
            || txt_fechaComercializacion.getValue() == null
            || txt_fechaVenta.getValue() == null
            || txt_descripcion.getText().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("Ingrese los datos");
        alert.showAndWait();
    } else {
        try {
            String verificacion = null;
            String v = "0";
            HashMap<String, Object> buscar = new LinkedHashMap<>();
            buscar.put("nombre", txt_nombre.getText());
            verificacion = Requests.post("/prenda/prendaId/", buscar);

            JSONObject dataJsonV = new JSONObject(verificacion);

            if (dataJsonV.getString("mensaje").equals("0")) {
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
                params.put("prestamo", Integer.parseInt(txt_prestamo.getText()));
                params.put("precioComercializacion", Integer.parseInt(txt_precioComercializacion.getText()));
                params.put("precioVenta", Integer.parseInt(txt_precioVenta.getText()));
                params.put("fechaComercializacion", txt_fechaComercializacion.getValue().toString());
                params.put("fechaVenta", txt_fechaVenta.getValue().toString());
                params.put("descripcion", txt_descripcion.getText());
                params.put("idEmp", this.empeño.getIdEmp());
                params.put("usuario", this.usuario.getNombre());

                String respuesta = Requests.post("/prenda/registrarPrenda/", params);

                JSONObject dataJson = new JSONObject(respuesta);

                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informativo");
                    alert.setHeaderText(null);
                    alert.setContentText(dataJson.getString("mensaje"));
                    alert.showAndWait();
                    Window.close(event);

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText(dataJson.getString("mensaje"));
                    alert.showAndWait();
                }
            } else {
                Alert alertV = new Alert(Alert.AlertType.WARNING);
                alertV.setTitle("Advertencia");
                alertV.setHeaderText(null);
                alertV.setContentText("La prenda ya se encuentra registrado...");
                alertV.showAndWait();
            }
        } catch (JSONException ex) {
            Logger.getLogger(AgregarPrendaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }
    
}
