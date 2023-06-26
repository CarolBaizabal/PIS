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
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class AgregarEgresoFXMLController implements Initializable {

    @FXML
    private ComboBox<Catalogo> cb_motivoE;
    
    private Integer[] arrayID;
    private ObservableList<Catalogo> comboBoxList;
    @FXML
    private TextField txt_cantidadE;
    @FXML
    private TextArea txtA_observacionesE;
    
    Usuario usuario = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxList = getAllCatalogo();

        
        cb_motivoE.setItems(comboBoxList);
    }    
    
    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }
    
     private ObservableList getAllCatalogo() {

        String respuesta = Requests.get("/catalogo/getAllCatalogoActivo/");
        Gson gson = new Gson();

        TypeToken<List<Catalogo>> token = new TypeToken<List<Catalogo>>() {
        };

        List<Catalogo> listaCatalogo = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCatalogo);
        System.out.print(comboBoxList);
        return comboBoxList;
    }

    @FXML
    private void agregarEgreso(ActionEvent event) {
         int position = this.cb_motivoE.getSelectionModel().getSelectedIndex();
       
        if (this.txt_cantidadE.getText().isEmpty()|| position <= -1){

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
                buscar.put("motivo", this.cb_motivoE.getValue());
                verificacion = Requests.post("/egreso/egresoId/", buscar);

                JSONObject dataJsonV = new JSONObject(verificacion);

         
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("cantidad", this.txt_cantidadE.getText());
                    params.put("motivo", this.cb_motivoE.getValue());
                    params.put("observaciones", this.txtA_observacionesE.getText());
                    params.put("usuario", this.usuario.getNombre());

                    String respuesta = Requests.post("/egreso/registrarEgreso/", params);

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
                Logger.getLogger(AgregarEgresoFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
}
