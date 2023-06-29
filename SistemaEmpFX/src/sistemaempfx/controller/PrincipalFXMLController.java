package sistemaempfx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sistemaempfx.model.pojos.Empe;
import sistemaempfx.model.pojos.Prenda;
import sistemaempfx.model.pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class PrincipalFXMLController implements Initializable {
    @FXML
    private BorderPane pnl_principal;
    @FXML
    private Label lb_usuario;
    Usuario usuario = null;
    Prenda prenda =null;
    Empe emp = null;
    @FXML
    private Label lb_rol;

    public void setData(HashMap<String,Object> context){                  
       System.out.print(context);
        
    }
    public void setDataUsuario(Usuario usuario){                  
        this.usuario=usuario;
        lb_usuario.setText("" + usuario.getNombre());
        lb_rol.setText("" + usuario.getRol());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    private void Empe (ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EmpFXML.fxml"));

            Parent principal = loader.load();

            EmpFXMLController controlador = loader.getController();
            controlador.setUsuario(this.usuario, this.emp);

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Clientes(ActionEvent event) {
         try{     
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/ClientesFXML.fxml"));

            Parent principal = loader.load();

            ClientesFXMLController cliente = loader.getController();
            cliente.setData(this.usuario);

            pnl_principal.setCenter(principal);
        }catch(IOException ex){
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Comercializacion(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/ComercializacionFXML.fxml"));

            Parent principal = loader.load();

            ComercializacionFXMLController usuario = loader.getController();
            usuario.setData(this.usuario);

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Ventas_Remates(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/VentasRematesFXML.fxml"));

            Parent principal = loader.load();

            VentasRematesFXMLController usuario = loader.getController();
            usuario.setData(this.usuario);

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @FXML
    private void Categorias_Sistema(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/CategoriasFXML.fxml"));

            Parent principal = loader.load();
            CategoriasFXMLController controlador = loader.getController();
            controlador.setData(this.usuario);

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void Categorias_Prendas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/CategoriasPrendasFXML.fxml"));

            Parent principal = loader.load();

            CategoriasPrendasFXMLController controlador = loader.getController();
            controlador.setData(this.usuario);
            
            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void Usuarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/UsuariosFXML.fxml"));

            Parent principal = loader.load();

            UsuariosFXMLController usuario = loader.getController();

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void Ingresos_Egresos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/MovimientosFXML.fxml"));

            Parent principal = loader.load();

            MovimientosFXMLController usuario = loader.getController();
            usuario.setData(this.usuario);

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Prendas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/PrendasFXML.fxml"));

            Parent principal = loader.load();

            PrendasFXMLController usuario = loader.getController();
            usuario.setData(this.usuario, this.prenda);

            pnl_principal.setCenter(principal);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void CerrarSesion(ActionEvent event){ 
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/LoginFXML.fxml"));

            Parent principal = loader.load();

            LoginFXMLController ctrl = loader.getController();
            Scene scene = new Scene(principal);
            //Nombramos la ventana
            stage.setTitle("Financiera Ramírez S.A DE C.V");
            //agregamos un icono a la ventana
            stage.getIcons().add(new Image("/sistemaempfx/gui/img/icon.png"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            
            Stage pnl = (Stage) pnl_principal.getScene().getWindow();
            pnl.close();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
