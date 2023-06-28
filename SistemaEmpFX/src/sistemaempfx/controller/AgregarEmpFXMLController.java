package sistemaempfx.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.CatalogoPrenda;
import sistemaempfx.model.pojos.Cliente;
import sistemaempfx.model.pojos.Contrato;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.model.pojos.Prenda;
import sistemaempfx.model.pojos.Usuario;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class AgregarEmpFXMLController implements Initializable {

    private Usuario usuario = null;

    private Cliente cliente;
    @FXML
    private TextArea txtA_observacionE;
    @FXML
    private Label lb_claveEmpeño;
    @FXML
    private Label lb_claveContrato;
    @FXML
    private TextField dp_fechaLimite;
    @FXML
    private TextArea txtA_prendas;
    @FXML
    private Label lb_prestamo;
    @FXML
    private Label lb_usuarioAtiende;
    @FXML
    private TextArea txtA_observaciones;
    @FXML
    private TextArea txtA_cliente;
    private Empe empeño;
    private Contrato contrato;
    private List<Prenda> prendas;
    CatalogoPrenda catalogo = null;
    @FXML
    private TextField dp_fechaLimite1;
    @FXML
    private Label lb_almacenaje;
    @FXML
    private Label lb_refrendo;
    @FXML
    private Label lb_interes;
    @FXML
    private Label lb_iva;
    @FXML
    private Label lb_refrendo2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Toma la fecha actual
        LocalDate fecha = LocalDate.now();
        //Calculamos la fecha a 15 dias
        fecha = fecha.plusDays(15);
        this.dp_fechaLimite.setText(fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        //Calculamos la fecha a 30 dias
        LocalDate fecha30 = LocalDate.now();
        fecha30 = fecha30.plusDays(30);
        this.dp_fechaLimite1.setText(fecha30.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        //Cargamos el usuario
        //
    }

    public void setData(Usuario usuario) {
        this.usuario = usuario;
        lb_usuarioAtiende.setText(this.usuario.getNombre());
        this.registrarEmpeño();
    }

    private void cargarPrendas() {

        String respuesta = Requests.get("/emp/getAllPrendasByEmp/" + this.empeño.getIdEmp());
        Gson gson = new Gson();

        TypeToken<List<Prenda>> token = new TypeToken<List<Prenda>>() {
        };
        try {
            this.prendas = gson.fromJson(respuesta, token.getType());
            //Se calcula el total por el costo de cada prenda
            Float total = 0.0f;
            for (Prenda prenda : this.prendas) {
                total += prenda.getPrestamo();
            }
            
            //CALCULO PRIMER PERIODO
            //Se calcula el interes por el total de la prenda
            Float intereses = total * 0.0032f * 15 * 1;
            System.out.println("interes "+intereses);
            lb_interes.setText(intereses+"");
            
            //Se calcula el almacenaje por el total de las prendas
            Float almacenaje = total * 0.0008f * 15 * 1;
            System.out.println("almacenaje "+almacenaje);
            lb_almacenaje.setText(almacenaje+"");
             
            //Se calcula el iba sobre la suma de itnerese y almacenaje
            Float calculo = intereses + almacenaje; 
            Float iva = calculo * 0.16f;
            System.out.println("iva "+iva);
            lb_iva.setText(iva+"");
            
            Float primerRefrendo = intereses + almacenaje + iva;
            System.out.println("Primer refrendo "+ primerRefrendo);
            lb_refrendo.setText(primerRefrendo+"");
            Float primerDesemp = total + intereses + almacenaje + iva;
            System.out.println("Primer desempeño "+ primerDesemp);
            
            //CALCULO PRIMER PERIODO
            //Se suman los totales
            Float calculoSegundo = intereses + almacenaje + iva;
            //Se multiplica por el segundo periodo 
            Float periodo = calculoSegundo * 2; 
            System.out.println("Segundo refrendo" + periodo);
            lb_refrendo2.setText(periodo+"");
            //Se suma al total del costo de prendas 
            Float segundoRefrendo = total + periodo;
            System.out.println("Segundo desempeño"+ segundoRefrendo);
            
            this.lb_prestamo.setText(total + "");
            this.txtA_prendas.setText(this.prendas.size() + "");

        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
            this.prendas = new ArrayList<>();
        }

    }

    private void registrarEmpeño() {
        Gson gson = new Gson();

        TypeToken<Empe> token = new TypeToken<Empe>() {
        };
        HashMap<String, Object> param = new LinkedHashMap<>();
        param.put("idCliente", "");
        param.put("observaciones", "");
        param.put("usuario", usuario.getNombre());
        param.put("idContrato", "");
        param.put("interes", "");
        param.put("almacenaje", "");
        param.put("periodos", 2);
        param.put("diasPeriodos", 30);
        param.put("iva", "");
        param.put("tasaComercializacion", "");

        String respuesta = Requests.post("/emp/registrarEmp/", param);
        try {
            this.empeño = gson.fromJson(respuesta, token.getType());
        } catch (JsonSyntaxException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void abrirClientes(ActionEvent event) throws IOException {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/ClientesFXML.fxml"));

            Parent usuarios = loader.load();

            ClientesFXMLController ctrl = loader.getController();
            ctrl.setData(usuario);
            ctrl.activarEmpeño();

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.cliente = ctrl.obtenerCliente();
            if (cliente != null) {
                this.txtA_cliente.setText(this.cliente.getNombre());
            }

        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void limpiar(ActionEvent event) {
        txtA_observacionE.setText("");
        txtA_prendas.setText("");
        txtA_observaciones.setText("");
        txtA_cliente.setText("");
        lb_prestamo.setText("");

    }

    @FXML
    private void registrarPrendas(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarPrendaFXML.fxml"));

            Parent usuarios = loader.load();

            AgregarPrendaFXMLController ctrl = loader.getController();
            ctrl.setEmpeño(this.empeño);
            ctrl.setData(this.usuario, this.catalogo);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.cargarPrendas();
        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }

    @FXML
    private void agregar(ActionEvent event) {
        if(this.prendas.size() > 0){
            if(this.cliente != null){
                if(this.txtA_observacionE.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Informativo");
                    alert.setHeaderText(null);
                    alert.setContentText("Hay campos vacios");
                    alert.showAndWait();
                }else{
                    HashMap<String, Object> param = new LinkedHashMap<>();
                    param.put("idCliente", "");
                    param.put("observaciones", "");
                    param.put("usuario", usuario.getNombre());
                    param.put("idContrato", "");
                    param.put("interes", "");
                    param.put("almacenaje", "");
                    param.put("periodos", 2);
                    param.put("diasPeriodos", 30);
                    param.put("iva", "");
                    param.put("tasaComercializacion", "");

                    String respuesta = Requests.post("/emp/registrarEmp/", param);
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Informativo");
                alert.setHeaderText(null);
                alert.setContentText("Selecciona un cliente");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informativo");
            alert.setHeaderText(null);
            alert.setContentText("Registra al menos un prenda");
            alert.showAndWait();
        }
    }

    @FXML
    private void pnl_principal(MouseEvent event) {
    }

    @FXML
    private void visualizarPrenda(ActionEvent event) {
        if(this.txtA_prendas.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Debe agregar una prenda");
            alert.showAndWait();
        }else{
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/VisualizarPrendaEmpeñoFXML.fxml"));

            Parent usuarios = loader.load();

            VisualizarPrendaEmpeñoFXMLController ctrl = loader.getController();
            ctrl.cargarPrendas(this.prendas);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Lista de prendas");
            stage.setResizable(false);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(UsuariosFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

}
