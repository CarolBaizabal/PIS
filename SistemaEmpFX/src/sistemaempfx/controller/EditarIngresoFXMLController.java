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
import sistemaempfx.model.pojos.Catalogo;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Egreso;
import sistemaempfx.model.pojos.Ingreso;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarIngresoFXMLController implements Initializable {

    @FXML
    private ComboBox<Categoria> cb_motivo;
    private Integer[] arrayID;
    private ObservableList<Categoria> comboBoxList;
    @FXML
    private TextField txt_cantidad;
    @FXML
    private TextArea txtA_observaciones;

    Usuario usuario = null;
    Ingreso ingreso = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       comboBoxList = datosCategoria();
       cb_motivo.setItems(comboBoxList);
    }    
    
    public void setData(Ingreso ingreso,Usuario usuario, Boolean isnew){    
        this.txt_cantidad.setText("" + ingreso.getCantidad());
        this.txtA_observaciones.setText(ingreso.getObservaciones());
        this.ingreso = ingreso;
        this.usuario = usuario;
    }
    
    private ObservableList datosCategoria() {

        String respuesta = Requests.get("/categoria/datosCategoriaEgreso/");
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };

        List<Categoria> listaCategoria = gson.fromJson(respuesta, token.getType());

        comboBoxList = FXCollections.observableArrayList(listaCategoria);
        System.out.print(comboBoxList);
        return comboBoxList;
    }

    @FXML
    private void editarIngreso(ActionEvent event) {
        VentanaAlert alert = new VentanaAlert();
        
        if (this.ingreso != null) {
              int position = this.cb_motivo.getSelectionModel().getSelectedIndex();

            if (this.txt_cantidad.getText().isEmpty()|| this.txtA_observaciones.getText().isEmpty()|| position <= -1) {
                alert.warning("Faltan Datos", "Ingrese todos los datos");
            } else {

                HashMap<String, Object> params = new LinkedHashMap<>();
                params.put("cantidad", this.txt_cantidad.getText());
                params.put("motivo", this.cb_motivo.getValue().getNombre());
                params.put("observaciones", this.txtA_observaciones.getText());
                params.put("usuarioA", this.usuario.getNombre());

                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar el ingreso?...");

                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                String respuesta = Requests.put("/ingreso/actualizarIngreso/" + ingreso.getIdIngreso(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.ingreso = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.ingreso = null;
                                    Window.close(event);
                                }
                            } else {    
                                alert.warning("Advertencia","Solo puede modificar el administrador...");
                                this.usuario = null;
                                Window.close(event);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarIngresoFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.ingreso = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar un ingreso...");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
}
