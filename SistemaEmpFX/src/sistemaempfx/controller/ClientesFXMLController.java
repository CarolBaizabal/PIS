package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Cliente;
import sistemaempfx.model.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class ClientesFXMLController implements Initializable {
    @FXML
    private TableView<Cliente> tb_Cliente;
    @FXML
    private Button bt_buscar;
    @FXML
    private TextField txt_usuario;
    @FXML
    private Button bt_agregar;
    @FXML
    private Button bt_editar;
    @FXML
    private Button bt_limpiar;

    Cliente cliente = null;
    Usuario usuario= null;
    Cliente nombreC = null;
    @FXML
    private TableColumn<Cliente, String> tcl_usuario;
    private TableColumn<Cliente, String> tcl_estatus;
    @FXML
    private TableColumn<Cliente, String> tcl_calle;
    @FXML
    private TableColumn<Cliente, String> tcl_cp;
    @FXML
    private TableColumn<Cliente, String> tcl_colonia;
    @FXML
    private TableColumn<Cliente, String> tcl_municipio;
    @FXML
    private TableColumn<Cliente, String> tcl_estado;
    @FXML
    private TableColumn<Cliente, String> tcl_identificacion;
    @FXML
    private TableColumn<Cliente, String> tcl_fechaNacimiento;
    @FXML
    private TableColumn<Cliente, String> tcl_rfc;
    @FXML
    private TableColumn<Cliente, String> tcl_correo;
    @FXML
    private TableColumn<Cliente, String> tcl_comentarios;
    @FXML
    private TableColumn<Cliente, String> tcl_sexo;
    @FXML
    private TableColumn<Cliente, String> tcl_fechaCreacion;
    @FXML
    private TableColumn<Cliente, String> tcl_fechaActualizacion;
    @FXML
    private TableColumn<Cliente, String> tcl_nombre;
    @FXML
    private TableColumn<Cliente, String> tcl_apellidoPaterno;
    @FXML
    private TableColumn<Cliente, String> tcl_apellidoMaterno;
    @FXML
    private TableColumn<Cliente, String> tcl_curp;
    @FXML
    private TableColumn<Cliente, String> tcl_telefono;
    @FXML
    private TableColumn<Cliente, String> tcl_usuarioA;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaCliente();
    }    
    
    public void setData(Usuario usuario){       
        this.usuario = usuario;
    }

    public void setDataUsuario(Cliente  cliente) {
        this.cliente = cliente ;
    }

    private void tablaCliente() {
       
        String respuesta = "";
        tb_Cliente.getItems().clear();

        //respuesta = Requests.get("/usuario/getAllUsers/");
        respuesta = Requests.get("/cliente/getAllCliente/");
        Gson gson = new Gson();

        TypeToken<List<Cliente>> token = new TypeToken<List<Cliente>>() {
        };

        List<Cliente> listaCliente= gson.fromJson(respuesta, token.getType());

        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        tcl_apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        tcl_calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
        tcl_cp.setCellValueFactory(new PropertyValueFactory<>("cp"));
        tcl_colonia.setCellValueFactory(new PropertyValueFactory<>("colonia"));
        tcl_municipio.setCellValueFactory(new PropertyValueFactory<>("municipio"));
        tcl_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tcl_identificacion.setCellValueFactory(new PropertyValueFactory<>("idemex"));
        tcl_fechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tcl_curp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        tcl_rfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        tcl_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tcl_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tcl_comentarios.setCellValueFactory(new PropertyValueFactory<>("comentarios"));
        tcl_sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tcl_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_fechaActualizacion.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));
        tcl_usuarioA.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));

        listaCliente.forEach(e -> {
            tb_Cliente.getItems().add(e);
        });

    }
    
    private void clickTableCliente(MouseEvent event) {
        String respuesta = "";
        if (tb_Cliente.getSelectionModel().getSelectedItem() != null) {
            cliente = tb_Cliente.getSelectionModel().getSelectedItem();
        }
    }
    

    @FXML
    private void agregar(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarClienteFXML.fxml"));

            Parent clientes = loader.load();

            AgregarClienteFXMLController ctrl = loader.getController();
            ctrl.setData(this.usuario);

            Scene scene = new Scene(clientes);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaCliente();
            
        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void Limpiar(ActionEvent event) {
        this.tablaCliente();
        this.txt_usuario.setText("");
    }

    @FXML
    private void buscar(ActionEvent event) {
        String buscar = this.txt_usuario.getText();
        tb_Cliente.getItems().clear();
        List<Cliente> listaCliente = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/cliente/buscarCliente/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Cliente>> token = new TypeToken<List<Cliente>>() {
        };
        listaCliente = gson.fromJson(respuesta, token.getType());

        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_apellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        tcl_apellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        tcl_calle.setCellValueFactory(new PropertyValueFactory<>("calle"));
        tcl_cp.setCellValueFactory(new PropertyValueFactory<>("cp"));
        tcl_colonia.setCellValueFactory(new PropertyValueFactory<>("colonia"));
        tcl_municipio.setCellValueFactory(new PropertyValueFactory<>("municipio"));
        tcl_estado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        tcl_identificacion.setCellValueFactory(new PropertyValueFactory<>("idemex"));
        tcl_fechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        tcl_curp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        tcl_rfc.setCellValueFactory(new PropertyValueFactory<>("rfc"));
        tcl_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tcl_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tcl_comentarios.setCellValueFactory(new PropertyValueFactory<>("comentarios"));
        tcl_sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tcl_fechaCreacion.setCellValueFactory(new PropertyValueFactory<>("fechaCreacion"));
        tcl_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tcl_fechaActualizacion.setCellValueFactory(new PropertyValueFactory<>("fechaActualizacion"));
        tcl_usuarioA.setCellValueFactory(new PropertyValueFactory<>("usuarioA"));

        listaCliente.forEach(e -> {
            tb_Cliente.getItems().add(e);
        });
    }

    @FXML
    private void editar(ActionEvent event) {
        this.cliente= tb_Cliente.getSelectionModel().getSelectedItem();
         if (this.cliente != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarClienteFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarClienteFXMLController ctrl = loader.getController();
                ctrl.setData(this.usuario, this.cliente);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaCliente();
                this.cliente = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un egreso...");
            alert.showAndWait();
        }
    }
    
}
