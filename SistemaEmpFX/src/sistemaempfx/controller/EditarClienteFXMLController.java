/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Cliente;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.VentanaAlert;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class EditarClienteFXMLController implements Initializable {

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
    
    Usuario usuario = null;
    Cliente cliente = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(Usuario usuario, Cliente cliente) {
        this.usuario = usuario;
        this.cliente = cliente;
        this.cargarCliente();
    }
     
    @FXML
    private void cancelar(ActionEvent event) {
        this.cargarCliente();
        this.cliente = null;
    }

    @FXML
    private void editar(ActionEvent event) throws JSONException {
        VentanaAlert alert = new VentanaAlert();
        
        if (this.cliente != null) {

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
            
             alert.warning("Faltan Datos", "Ingrese todos los datos");
        } else  {
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
                    params.put("usuarioA", this.usuario.getNombre());
                    
                Alert alertI = new Alert(Alert.AlertType.CONFIRMATION);
                alertI.setTitle("Validación");
                alertI.setHeaderText(null);
                alertI.setContentText("¿Desea actualizar el cliente?...");
            System.out.println("IdCliente"+ this.cliente.getIdCliente());
                alertI.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            if (this.usuario.getRol().equals("Administrador")) {
                                String respuesta = Requests.put("/cliente/actualizarCliente/" + cliente.getIdCliente(), params);

                                JSONObject dataJson = new JSONObject(respuesta);

                                if ((Boolean) dataJson.get("errorRespuesta") == false) {
                                    alert.information("Informativo", dataJson.getString("mensaje"));
                                    this.cliente = null;
                                    Window.close(event);

                                } else {
                                    alert.warning("Advertencia", dataJson.getString("mensaje"));
                                    this.cliente = null;
                                    Window.close(event);
                                }
                            } else {    
                                
                                alert.warning("Advertencia","Solo puede modificar el administrador...");
                                this.cliente = null;
                                Window.close(event);
                            }
                        } catch (JSONException ex) {
                            Logger.getLogger(EditarClienteFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (response == ButtonType.CANCEL) {
                        this.cliente = null;
                        Window.close(event);
                    }
                });
            }
        } else {
            alert.warning("Advertencia","Debe seleccionar un cliente..");
        }
    }
    
    public void cargarCliente() {
        if (this.usuario.getRol().equals("Administrador")) {
            LocalDate date = LocalDate.parse(cliente.getFechaNacimiento(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            this.txt_nombre.setText(cliente.getNombre());
            this.txt_ap.setText(cliente.getApellidoPaterno());
            this.txt_am.setText(cliente.getApellidoMaterno());
            this.txt_calle.setText(cliente.getCalle());
            this.txt_col.setText(cliente.getColonia());
            this.txt_cp.setText(cliente.getCp());
            this.txt_rfc.setText(cliente.getRfc());
            this.txt_es.setText(cliente.getEstado());
            this.txt_min.setText(cliente.getMunicipio());
            this.txt_id.setText(cliente.getIdemex());
            this.txt_curp.setText(cliente.getCurp());
            this.txt_tel.setText(cliente.getTelefono());
            this.txtA_comentarios.setText(cliente.getComentarios());
            this.txt_correo.setText(cliente.getCorreo());
            this.txt_fn.setValue(date);
          } 
    }
}
