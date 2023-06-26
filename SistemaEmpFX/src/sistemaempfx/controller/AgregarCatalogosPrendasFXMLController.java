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
import java.util.LinkedList;
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
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Rol;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class AgregarCatalogosPrendasFXMLController implements Initializable {

    Usuario usuario = null;
    @FXML
    private ComboBox<Categoria> cb_categorias;
    private Integer[] arrayID;
    private ObservableList<Categoria> comboBoxList;
    @FXML
    private TextField txt_catalogo;
    
    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxList = getAllCategoria();

        
        cb_categorias.setItems(comboBoxList);
    }    


    @FXML
    private void agregarCatalogo(ActionEvent event) {
        
        int position = this.cb_categorias.getSelectionModel().getSelectedIndex();
       
        if (this.txt_catalogo.getText().isEmpty()|| position <= -1){

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
                buscar.put("nombre", this.txt_catalogo.getText());
                verificacion = Requests.post("/catalogoprenda/catalogoPrendaId/", buscar);

                JSONObject dataJsonV = new JSONObject(verificacion);

                if (dataJsonV.getString("mensaje").equals("0")) {
                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_catalogo.getText());
                    params.put("idCategoria", this.cb_categorias.getValue().getIdCategoria());

                    String respuesta = Requests.post("/catalogoprenda/registrarCatalogoPrenda/", params);

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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("El catalogo ya se encuentra registrado...");
                    alert.showAndWait();
                }
            } catch (JSONException ex) {
                Logger.getLogger(AgregarCatalogosPrendasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private ObservableList getAllCategoria() {

        String respuesta = Requests.get("/categoriaprenda/getAllCategoriaPrendaActivo/");
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };

        List<Categoria> listaCategorias = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCategorias);
        System.out.print(comboBoxList);
        return comboBoxList;
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    
       
    
}
