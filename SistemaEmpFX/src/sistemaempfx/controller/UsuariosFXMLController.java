package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class UsuariosFXMLController implements Initializable {

    @FXML
    private TableView<Usuario> tb_Usuarios;
    @FXML
    private TableColumn<Usuario, String> tcl_nombre;
    @FXML
    private TableColumn<Usuario, String> tcl_apellidoPaterno;
    @FXML
    private TableColumn<Usuario, String> tcl_apellidoMaterno;
    @FXML
    private TableColumn<Usuario, String> tcl_usuario;
    @FXML
    private TableColumn<Usuario, String> tcl_password;
    @FXML
    private TableColumn<Usuario, String> tcl_telefono;
    @FXML
    private TableColumn<Usuario, String> tcl_rol;
    @FXML
    private TableColumn<Usuario, String> tcl_estatus;

    Usuario usuario = null;
    Usuario usuarioU = null;

    @FXML
    private TextField txt_usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaUsuario();
    }

    @FXML
    private void buscarUsuario(ActionEvent event) {
        String buscar = this.txt_usuario.getText();
        tb_Usuarios.getItems().clear();
        List<Usuario> listaUsuario = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/usuario/buscarUsuario/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>() {
        };
        listaUsuario = gson.fromJson(respuesta, token.getType());

        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        tcl_apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcl_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tcl_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        tcl_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        listaUsuario.forEach(e -> {
            tb_Usuarios.getItems().add(e);
        });
    }

    @FXML
    private void agregarUsuario(ActionEvent event) throws InterruptedException {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarUsuarioFXML.fxml"));
 
            Parent usuarios = loader.load();

            AgregarUsuarioFXMLController ctrl = loader.getController();
            ctrl.setData(null);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaUsuario();

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarUsuario(ActionEvent event) {
        this.usuario = tb_Usuarios.getSelectionModel().getSelectedItem();
        if (this.usuario != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarUsuarioFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarUsuarioFXMLController ctrl = loader.getController();
                ctrl.setData(this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaUsuario();
                this.usuario = null;
            } catch (IOException ex) {
                Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un Usuario...");
            alert.showAndWait();
        }
    }

    @FXML
    private void Limpiar(ActionEvent event) {
        txt_usuario.setText("");
        this.tablaUsuario();
    }

    private void tablaUsuario() {

        String respuesta = "";
        tb_Usuarios.getItems().clear();

        respuesta = Requests.get("/usuario/getAllUsers/");
        Gson gson = new Gson();

        TypeToken<List<Usuario>> token = new TypeToken<List<Usuario>>() {
        };

        List<Usuario> listaUsuario = gson.fromJson(respuesta, token.getType());

        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        tcl_apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcl_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tcl_rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        tcl_estatus.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        listaUsuario.forEach(e -> {
            tb_Usuarios.getItems().add(e);
        });

    }

    private void clickTableUsuario(MouseEvent event) {
        String respuesta = "";
        if (tb_Usuarios.getSelectionModel().getSelectedItem() != null) {
            usuario = tb_Usuarios.getSelectionModel().getSelectedItem();
        }
    }

    @FXML
    private void desactivar(ActionEvent event) {
        this.usuario = tb_Usuarios.getSelectionModel().getSelectedItem();
        if (this.usuario != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea desactivar el usuario?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.usuario.getEstatus();

                        if ("Activo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idUsuario", this.usuario.getIdUsuario());
                            String respuesta = Requests.delete("/usuario/eliminarUsuario/" + usuario.getIdUsuario(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.usuario = null;
                                this.tablaUsuario();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.usuario = null;
                                this.tablaUsuario();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El usuario ya esta inactivo...");
                            alertInactivo.showAndWait();
                            this.usuario = null;
                            this.tablaUsuario();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.usuario = null;
                    this.tablaUsuario();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un Usuario...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void activar(ActionEvent event) {
        this.usuario = tb_Usuarios.getSelectionModel().getSelectedItem();
        if (this.usuario != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea activar el usuario?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {

                        String estado = this.usuario.getEstatus();

                        if ("Inactivo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idUsuario", this.usuario.getIdUsuario());
                            String respuesta = Requests.put("/usuario/actualizarEstatus/" + usuario.getIdUsuario(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.usuario = null;
                                this.tablaUsuario();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.usuario = null;
                                this.tablaUsuario();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El usuario ya esta Activo...");
                            alertInactivo.showAndWait();
                            this.usuario = null;
                            this.tablaUsuario();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.usuario = null;
                    this.tablaUsuario();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un Usuario...");
            alertI.showAndWait();
        }
    }

}
