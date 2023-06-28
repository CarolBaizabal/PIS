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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Catalogo;
import sistemaempfx.model.pojos.CatalogoPrenda;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

public class AgregarPrendaFXMLController implements Initializable {

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
    private ComboBox<CatalogoPrenda> cb_subcategoria;
    private Integer[] arrayID;
    private ObservableList<CatalogoPrenda> comboBoxList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBoxList = datosCategoria();
       cb_subcategoria.setItems(comboBoxList);
    }    
    
    public void setEmpeño(Empe empeño){
        this.empeño = empeño;
    }
    
     public void setData(Usuario usuario, CatalogoPrenda catalogo) {
        this.usuario = usuario;
        this.catalogo = catalogo;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void agregar(ActionEvent event) {
        
         int position = this.cb_subcategoria.getSelectionModel().getSelectedIndex();
            if (txt_nombre.getText().isEmpty()
            || txt_numPiezas.getText().isEmpty()
            || txt_serie.getText().isEmpty()
            || txt_modelo.getText().isEmpty()
            || txt_prestamo.getText().isEmpty()
            || txt_descripcion.getText().isEmpty()
            || position <= -1) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Ingrese los datos");
                alert.showAndWait();
        } else {
                this.txt_peso.setText(String.valueOf(0.0f));
                this.txt_kilataje.setText(String.valueOf(0.0f));    
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
                params.put("categoria", this.cb_subcategoria.getValue().getCategoria());
                params.put("numPiezas", Integer.parseInt(txt_numPiezas.getText()));
                params.put("serie", txt_serie.getText());
                params.put("modelo", txt_modelo.getText());
                params.put("subcategoria", cb_subcategoria.getValue().getNombre());
                params.put("metal", txt_metal.getText());
                params.put("peso", Float.parseFloat(txt_peso.getText()));
                params.put("kilataje", Float.parseFloat(txt_kilataje.getText()));
                params.put("prestamo", Float.parseFloat(txt_prestamo.getText()) * Integer.parseInt(txt_numPiezas.getText()));
                /*params.put("precioComercializacion", Integer.parseInt(txt_precioComercializacion.getText()));
                params.put("precioVenta", Integer.parseInt(txt_precioVenta.getText()));
                params.put("fechaComercializacion", txt_fechaComercializacion.getValue().toString());
                params.put("fechaVenta", txt_fechaVenta.getValue().toString());*/
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
                alertV.setContentText("La prenda ya se encuentra registrada...");
                alertV.showAndWait();
            }
        } catch (JSONException ex) {
            Logger.getLogger(AgregarPrendaFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    }
    
    private ObservableList datosCategoria() {

        String respuesta = Requests.get("/catalogoprenda/getAllCatalogoPrendaActivo/");
        Gson gson = new Gson();

        TypeToken<List<CatalogoPrenda>> token = new TypeToken<List<CatalogoPrenda>>() {
        };

        List<CatalogoPrenda> listaCatalogo = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCatalogo);
        System.out.print(comboBoxList);
        return comboBoxList;
    }

    @FXML
    private void limpiar(MouseEvent event) {
        txt_nombre.setText(null);
        txt_categoria.setText(null);
        txt_numPiezas.setText(null);
        txt_serie.setText(null);
        txt_modelo.setText(null);
        txt_subcategoria.setText(null);
        txt_metal.setText(null);
        txt_peso.setText(null);
        txt_kilataje.setText(null);
        txt_prestamo.setText(null);
        txt_descripcion.setText(null);
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

    @FXML
    private void limpiar(ActionEvent event) {
    }
    
}
