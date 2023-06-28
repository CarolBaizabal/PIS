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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Egreso;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarEgresoFXMLController implements Initializable {

    @FXML
    private ComboBox<Categoria> cb_motivoE;
    private Integer[] arrayID;
    private ObservableList<Categoria> comboBoxList;
    @FXML
    private TextField txt_cantidadE;
    @FXML
    private TextArea txtA_observacionesE;

    Usuario usuario = null;
    Egreso egreso = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBoxList = datosCategoria();
       cb_motivoE.setItems(comboBoxList);
    }    
    
    public void setData(Egreso egreso,Usuario usuario, Boolean isnew){    
        this.txt_cantidadE.setText("" + egreso.getCantidad());
        this.txtA_observacionesE.setText(egreso.getObservaciones());
        this.egreso = egreso;
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
    private void editarEgreso(ActionEvent event) {
        VentanaAlert alert = new VentanaAlert();
        
        if (this.egreso != null) {
              int position = this.cb_motivoE.getSelectionModel().getSelectedIndex();

            if (this.txt_cantidadE.getText().isEmpty()|| this.txtA_observacionesE.getText().isEmpty()|| position <= -1) {
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("cantidad", this.txt_cantidadE.getText());
                params.put("motivo", this.cb_motivoE.getValue().getNombre());
                params.put("odservaciones", this.txtA_observacionesE.getText());
                params.put("usuarioA", this.usuario.getNombre());
                
                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar el egreso?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                String respuesta = Requests.put("/egreso/actualizarEgreso/" + egreso.getIdEgreso(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.egreso = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.egreso= null;
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
                        this.egreso = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar un egreso...");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
}
