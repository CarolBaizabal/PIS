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
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Catalogo;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarCatalogosFXMLController implements Initializable {

    @FXML
    private ComboBox<Categoria> cb_categorias;
    private Integer[] arrayID;
    private ObservableList<Categoria> comboBoxList;
    @FXML
    private TextField txt_catalogo;
    
    Catalogo catalogo = null;
    Usuario usuario = null;
    
     public void setData(Catalogo catalogo,Usuario usuario, Boolean isnew){ 
        this.txt_catalogo.setText(catalogo.getNombre());
        this.catalogo = catalogo;
        this.usuario = usuario;
    }

     private ObservableList getAllCategoria() {

        String respuesta = Requests.get("/categoria/getAllCategoriaActivo/");
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };

        List<Categoria> listaCategorias = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCategorias);
        System.out.print(comboBoxList);
        return comboBoxList;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBoxList = getAllCategoria();
       cb_categorias.setItems(comboBoxList);
    }    

    @FXML
    private void editarCatalogo(ActionEvent event) {
        VentanaAlert alert = new VentanaAlert();
        
        if (this.catalogo != null) {
              int position = this.cb_categorias.getSelectionModel().getSelectedIndex();

            if (this.txt_catalogo.getText().isEmpty()|| position <= -1) {
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("nombre", this.txt_catalogo.getText());
                params.put("idCategoria", this.cb_categorias.getValue().getIdCategoria());

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar el catalogo?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                String respuesta = Requests.put("/catalogo/actualizarCatalogo/" + catalogo.getIdCatalogo(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.catalogo = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.catalogo= null;
                                    Window.close(event);
                                }
                            } else {    
                                alert.warning("Advertencia","Solo puede modificar el administrador...");
                                this.usuario = null;
                                Window.close(event);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarCatalogosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.catalogo = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar un catalogo...");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
         Window.close(event);
    }
    
}
