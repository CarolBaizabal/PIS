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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sistemaempfx.api.Requests;
import sistemaempfx.model.pojos.Catalogo;
import sistemaempfx.model.pojos.Categoria;
import sistemaempfx.model.pojos.Usuario;
/**
 * FXML Controller class
 *
 * @author Carol Celina Pacheco
 */
public class CategoriasFXMLController implements Initializable {
    @FXML
    private TextField txt_usuario;
    @FXML
    private TextField txt_usuario1;
    @FXML
    private TableView<Categoria> tb_categorias;
    @FXML
    private TableColumn<Categoria, Integer> tcl_idCategoria;
    @FXML
    private TableColumn<Categoria, String> tcl_nombre;
    @FXML
    private TableColumn<Categoria, String> tcl_estatusCategoria;
    @FXML
    private TableView<Catalogo> tb_catalogos;
    @FXML
    private TableColumn<Catalogo, Integer> tcl_claveCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcl_categoria;
    @FXML
    private TableColumn<Catalogo, String> tcl_nombreCatalogo;
    @FXML
    private TableColumn<Catalogo, String> tcl_estatusCatalogos;
    
    Usuario usuario = null;
    Categoria categoria = null;
    Catalogo catalogo = null;
    @FXML
    private Button bt_activar;
    @FXML
    private Button bt_desactivar;
    @FXML
    private Button bt_desactivar1;
    @FXML
    private Button bt_activar1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tablaCategoria();
        this.tablaCatalogo();
    }    

    public void setData(Usuario usuario){       
        this.usuario = usuario;
    }
    
    private void tablaCategoria() {

        String respuesta = "";
        tb_categorias.getItems().clear();

        respuesta = Requests.get("/categoria/getAllCategoria/");
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };

        List<Categoria> listaCategoria= gson.fromJson(respuesta, token.getType());

        tcl_idCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatusCategoria.setCellValueFactory(new PropertyValueFactory<>("estatus"));

        listaCategoria.forEach(e -> {
            tb_categorias.getItems().add(e);
        });

    }
    
    private void clickTableCategorias(MouseEvent event) {
        String respuesta = "";
        if (tb_categorias.getSelectionModel().getSelectedItem() != null) {
            categoria = tb_categorias.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    private void agregarCategoria(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarCategoriaFXML.fxml"));

            Parent usuarios = loader.load();

            AgregarCategoriaFXMLController ctrl = loader.getController();
            ctrl.setData(null);

            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaCategoria();
            
        } catch (IOException ex) {
            Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void editarCategoria(ActionEvent event) {
        this.categoria= tb_categorias.getSelectionModel().getSelectedItem();
         if (this.categoria != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarCategoriaFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarCategoriaFXMLController ctrl = loader.getController();
                
                ctrl.setData(this.categoria, this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaCategoria();
                this.categoria = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione una categoria...");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void activarCategorias(ActionEvent event) {
        this.categoria= tb_categorias.getSelectionModel().getSelectedItem();
          if (this.categoria != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea activar la categoria?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        
                        String estado = this.categoria.getEstatus();

                        if ("Inactivo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idCategoria", this.categoria.getIdCategoria());
                            String respuesta = Requests.put("/categoria/actualizarEstatus/" + categoria.getIdCategoria(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.categoria = null;
                                this.tablaCategoria();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.categoria = null;
                                this.tablaCategoria();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("La categoria ya esta Activa...");
                            alertInactivo.showAndWait();
                            this.categoria = null;
                            this.tablaCategoria();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.categoria = null;
                    this.tablaCategoria();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione una categoria...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void desactivarCategorias(ActionEvent event) {
        this.categoria= tb_categorias.getSelectionModel().getSelectedItem();
        if (this.categoria != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea desactivar la categoria?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        
                        String estado = this.categoria.getEstatus();

                        if ("Activo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idCategoria", this.categoria.getIdCategoria());
                            String respuesta = Requests.delete("/categoria/eliminarCategoria/" + categoria.getIdCategoria(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.categoria = null;
                                this.tablaCategoria();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.categoria = null;
                                this.tablaCategoria();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("La categoria ya esta inactiva...");
                            alertInactivo.showAndWait();
                            this.categoria = null;
                            this.tablaCategoria();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.categoria = null;
                    this.tablaCategoria();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione una categoria...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void buscarCategoria(ActionEvent event) {
        String buscar = this.txt_usuario.getText();
        tb_categorias.getItems().clear();
        List<Categoria> listaCategoria = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/categoria/buscarCategoria/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Categoria>> token = new TypeToken<List<Categoria>>() {
        };
        listaCategoria = gson.fromJson(respuesta, token.getType());

        tcl_idCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        tcl_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatusCategoria.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        
        listaCategoria.forEach(e -> {
            tb_categorias.getItems().add(e);
        });
    }
    
    @FXML
    private void limpiarCategoria(ActionEvent event) {
        txt_usuario.setText("");
        this.tablaCategoria();
    }
    
     private void tablaCatalogo() {
       
        String respuesta = "";
        tb_catalogos.getItems().clear();

        //respuesta = Requests.get("/usuario/getAllUsers/");
        respuesta = Requests.get("/catalogo/getAllCatalogo/");
        Gson gson = new Gson();

        TypeToken<List<Catalogo>> token = new TypeToken<List<Catalogo>>() {
        };

        List<Catalogo> listaCategoria= gson.fromJson(respuesta, token.getType());

        tcl_claveCatalogo.setCellValueFactory(new PropertyValueFactory<>("idCatalogo"));
        tcl_nombreCatalogo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatusCatalogos.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria")); 

        listaCategoria.forEach(e -> {
            tb_catalogos.getItems().add(e);
        });

    }
    
    private void clickTableCatalogo(MouseEvent event) {
        String respuesta = "";
        if (tb_catalogos.getSelectionModel().getSelectedItem() != null) {
            catalogo = tb_catalogos.getSelectionModel().getSelectedItem();
        }
    }
    
    @FXML
    private void agregarCatalogo(ActionEvent event) {
        try {
            Stage stage = new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/AgregarCatalogosFXML.fxml"));

            Parent usuarios = loader.load();

            AgregarCatalogosFXMLController ctrl = loader.getController();
            ctrl.setData(null);
 
            Scene scene = new Scene(usuarios);
            stage.setScene(scene);
            stage.setTitle("Registrar");
            stage.setResizable(false);
            stage.showAndWait();
            this.tablaCatalogo();
            
        } catch (IOException ex) {
            Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void editarCatalogo(ActionEvent event) {
         this.catalogo= tb_catalogos.getSelectionModel().getSelectedItem();
         if (this.catalogo != null) {
            try {
                Stage stage = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sistemaempfx/gui/view/EditarCatalogoFXML.fxml"));

                Parent formUsuarioEditar = loader.load();

                EditarCatalogosFXMLController ctrl = loader.getController();
                
                ctrl.setData(this.catalogo, this.usuario, false);

                Scene scene = new Scene(formUsuarioEditar);
                stage.setScene(scene);
                stage.setTitle("Editar");
                stage.setResizable(false);
                stage.showAndWait();
                this.tablaCatalogo();
                this.catalogo = null;
                
            } catch (IOException ex) {
                Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Seleccione un catalogo...");
            alert.showAndWait();
        }
    }

    @FXML
    private void buscarCatalogo(ActionEvent event) {
        String buscar = this.txt_usuario1.getText();
        tb_catalogos.getItems().clear();
        List<Catalogo> listaCatalogo = null;

        // Actual
        String respuesta = "";

        respuesta = Requests.get("/catalogo/buscarCatalogo/" + buscar);
        Gson gson = new Gson();

        TypeToken<List<Catalogo>> token = new TypeToken<List<Catalogo>>() {
        };
        listaCatalogo = gson.fromJson(respuesta, token.getType());

        tcl_claveCatalogo.setCellValueFactory(new PropertyValueFactory<>("idCatalogo"));
        tcl_nombreCatalogo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcl_estatusCatalogos.setCellValueFactory(new PropertyValueFactory<>("estatus"));
        tcl_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        
        listaCatalogo.forEach(e -> {
            tb_catalogos.getItems().add(e);
        });
    }    

    @FXML
    private void limpirCatalogo(ActionEvent event) {
        txt_usuario1.setText("");
        this.tablaCatalogo();
    }

    @FXML
    private void desactivarCatalogos(ActionEvent event) {
        this.catalogo= tb_catalogos.getSelectionModel().getSelectedItem();
        if (this.catalogo != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea desactivar el catalogo?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        
                        String estado = this.catalogo.getEstatus();

                        if ("Activo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idCatalogo", this.catalogo.getIdCatalogo());
                            String respuesta = Requests.delete("/catalogo/eliminarCatalogo/" + catalogo.getIdCatalogo(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.catalogo = null;
                                this.tablaCatalogo();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.catalogo = null;
                                this.tablaCatalogo();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El catalogo ya esta inactiva...");
                            alertInactivo.showAndWait();
                            this.catalogo = null;
                            this.tablaCatalogo();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.catalogo = null;
                    this.tablaCatalogo();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un catalogo...");
            alertI.showAndWait();
        }
    }

    @FXML
    private void activarCatalogos(ActionEvent event) {
        this.catalogo= tb_catalogos.getSelectionModel().getSelectedItem();
          if (this.catalogo != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Validación");
            alert.setHeaderText(null);
            alert.setContentText("¿Desea activar el catalogo?...");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    try {
                        
                        String estado = this.catalogo.getEstatus();

                        if ("Inactivo".equals(estado)) {
                            HashMap<String, Object> params = new LinkedHashMap<>();
                            params.put("idCatalogo", this.catalogo.getIdCatalogo());
                            String respuesta = Requests.put("/catalogo/actualizarEstatus/" + catalogo.getIdCatalogo(), params);

                            JSONObject dataJson = new JSONObject(respuesta);

                            if ((Boolean) dataJson.get("errorRespuesta") == false) {

                                Alert alertC = new Alert(Alert.AlertType.INFORMATION);
                                alertC.setTitle("Informativo");
                                alertC.setHeaderText(null);
                                alertC.setContentText(dataJson.getString("mensaje"));
                                alertC.showAndWait();
                                this.catalogo = null;
                                this.tablaCatalogo();

                            } else {
                                Alert alertN = new Alert(Alert.AlertType.INFORMATION);
                                alertN.setTitle("Informativo");
                                alertN.setHeaderText(null);
                                alertN.setContentText(dataJson.getString("mensaje"));
                                alertN.showAndWait();
                                this.catalogo = null;
                                this.tablaCatalogo();
                            }
                        } else {
                            Alert alertInactivo = new Alert(Alert.AlertType.INFORMATION);
                            alertInactivo.setTitle("Informativo");
                            alertInactivo.setHeaderText(null);
                            alertInactivo.setContentText("El catalogo ya esta Activo...");
                            alertInactivo.showAndWait();
                            this.catalogo = null;
                            this.tablaCatalogo();
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(CategoriasFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (response == ButtonType.CANCEL) {
                    this.catalogo = null;
                    this.tablaCatalogo();
                }
            });
        } else {
            Alert alertI = new Alert(Alert.AlertType.WARNING);
            alertI.setTitle("Advertencia");
            alertI.setHeaderText(null);
            alertI.setContentText("Seleccione un catalogo...");
            alertI.showAndWait();
        }
    }

}
