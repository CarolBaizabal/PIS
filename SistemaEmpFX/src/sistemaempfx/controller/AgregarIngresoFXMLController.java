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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Catalogo;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class AgregarIngresoFXMLController implements Initializable {

    @FXML
    private ComboBox<Categoria> cb_motivo;
    private Integer[] arrayID;
    private ObservableList<Categoria> comboBoxList;
    @FXML
    private TextField txt_cantidad;
    @FXML
    private TextArea txtA_observaciones;
    
    Usuario usuario = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxList = datosCategoria();

        
        cb_motivo.setItems(comboBoxList);
    }    

    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }
    
    private ObservableList datosCategoria() {

        String respuesta = Requests.get("/categoria/datosCategoriaIngreso/");
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };

        List<Categoria> listaCategoria = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCategoria);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
    
    @FXML
    private void agregarIngreso(ActionEvent event) {
        int position = this.cb_motivo.getSelectionModel().getSelectedIndex();
       
        if (this.txt_cantidad.getText().isEmpty()|| position <= -1){

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
                buscar.put("motivo", this.cb_motivo.getValue());
                verificacion = Requests.post("/ingreso/ingresoId/", buscar);

                JSONObject dataJsonV = new JSONObject(verificacion);

                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("cantidad", this.txt_cantidad.getText());
                    params.put("motivo", this.cb_motivo.getValue());
                    params.put("observaciones", this.txtA_observaciones.getText());
                    params.put("usuario", this.usuario.getNombre());

                    String respuesta = Requests.post("/ingreso/registrarIngreso/", params);

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

            } catch (JSONException ex) {
                Logger.getLogger(AgregarIngresoFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
}
