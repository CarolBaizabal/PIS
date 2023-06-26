/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Cliente;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class AgregarClienteFXMLController implements Initializable {

    @FXML
    private TextField txt_nombre;
    @FXML
    private TextArea txtA_comentarios;
    @FXML
    private TextField txt_ap;
    @FXML
    private TextField txt_am;
    @FXML
    private DatePicker txt_fn;
    @FXML
    private CheckBox rb_sexo;
    @FXML
    private TextField txt_curp;
    @FXML
    private TextField txt_rfc;
    @FXML
    private TextField txt_calle;
    @FXML
    private TextField txt_cp;
    @FXML
    private TextField txt_col;
    @FXML
    private TextField txt_min;
    @FXML
    private TextField txt_es;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_correo;
    @FXML
    private TextField txt_id;

    /**
     * Initializes the controller class.
     */
    
    Cliente cliente =null;
    Usuario usuario = null;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Usuario usuario, Cliente cliente, Boolean iuy) {
        this.usuario = usuario;
        this.cliente = cliente;
    }

    public void setData(Usuario usuario) {
        this.usuario = usuario;
    }
     
    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    private void agregar(ActionEvent event) {
        if (this.txt_nombre.getText().isEmpty() 
                ||txt_fn.getValue() == null
                ||txt_ap.getText().isEmpty()
                ||txt_am.getText().isEmpty()
                ||txt_calle.getText().isEmpty()
                ||txt_col.getText().isEmpty()
                ||txt_cp.getText().isEmpty()
                ||txt_rfc.getText().isEmpty()
                ||txt_es.getText().isEmpty()
                ||txt_min.getText().isEmpty()
                ||txt_id.getText().isEmpty()
                ||txt_curp.getText().isEmpty()
                ||txt_tel.getText().isEmpty()
                ||txt_correo.getText().isEmpty()) {
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
                buscar.put("nombre", this.txt_nombre.getText());
                verificacion = Requests.post("/cliente/clienteId/", buscar);

                JSONObject dataJsonV = new JSONObject(verificacion);

                if (dataJsonV.getString("mensaje").equals("0")) {

                    HashMap<String, Object> params = new LinkedHashMap<>();
                    params.put("nombre", this.txt_nombre.getText());
                    params.put("apellidoPaterno", this.txt_ap.getText());
                    params.put("apellidoMaterno", this.txt_am.getText());
                    params.put("calle", this.txt_calle.getText());
                    params.put("cp", this.txt_cp.getText());
                    params.put("colonia", this.txt_col.getText());
                    params.put("municipio", this.txt_min.getText());
                    params.put("estado", this.txt_es.getText());
                    params.put("idemex", this.txt_id.getText());
                    params.put("fechaNacimiento", this.txt_fn.getValue());
                    params.put("curp", this.txt_curp.getText());
                    params.put("rfc", this.txt_rfc.getText());
                    params.put("telefono", this.txt_tel.getText());
                    params.put("correo", this.txt_correo.getText());
                    params.put("comentarios", this.txtA_comentarios.getText());
                    params.put("sexo", this.rb_sexo.isSelected() ? "Femenino" : "Masculino");
                    params.put("usuario", this.usuario.getNombre());
                    

                    String respuesta = Requests.post("/cliente/registrarCliente/", params);

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
                    alertV.setContentText("E cliente ya se encuentra registrado...");
                    alertV.showAndWait();

                }
            } catch (JSONException ex) {
                Logger.getLogger(AgregarClienteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    private void limpiar(ActionEvent event) {
        txt_nombre.setText("");
        txt_fn.setValue(null);
        txt_ap.setText("");
        txt_am.setText("");
        txt_calle.setText("");
        txt_col.setText("");
        txt_cp.setText("");
        txt_rfc.setText("");
        txt_es.setText("");
        txt_min.setText("");
        txt_id.setText("");
        txt_curp.setText("");
        txt_tel.setText("");
        txt_correo.setText("");
        txtA_comentarios.setText("");
        rb_sexo.setSelected(false);     
    }

    @FXML
    private void editar(ActionEvent event) {
    }

    
}
