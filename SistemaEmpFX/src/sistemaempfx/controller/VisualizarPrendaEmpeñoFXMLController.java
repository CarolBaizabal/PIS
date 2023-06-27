/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaempfx.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemaempfx.model.pojos.Prenda;
import sistemaempfx.utils.Window;

/**
 * FXML Controller class
 *
 * @author Alicia
 */
public class VisualizarPrendaEmpeñoFXMLController implements Initializable {

    @FXML
    private TableView<Prenda> tb_prenda;
    @FXML
    private TableColumn<Prenda, String> tc_prenda;
    @FXML
    private TableColumn<Prenda, Float> tc_precio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void cargarPrendas(List<Prenda> prendas){
        ObservableList lista = FXCollections.observableArrayList(prendas);
        this.tc_prenda.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.tc_precio.setCellValueFactory(new PropertyValueFactory<>("prestamo"));
        this.tb_prenda.setItems(lista);
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Window.close(event);
    }
    
}
