/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class ListaLibrosController implements Initializable {

    @FXML
    private BorderPane lector_View;
    @FXML
    private ToggleGroup filtro;
    @FXML
    private TableView<Libro> tableViewListaLibros;
    @FXML
    private TableColumn<Libro,String> tableColumnISBN;
    @FXML
    private TableColumn<Libro,String> tableColumnTitulo;
    @FXML
    private TableColumn<Libro,String> tableColumnAutor;
    @FXML
    private TableColumn<Libro,String> tableColumnEdicion;
    @FXML
    private TableColumn<Libro,Integer> tableColumnNCopias;
    @FXML
    private TableColumn<Libro,String> tableColumnConfigurar;
    @FXML
    private TableColumn<Libro,String> tableColumnDetalle;
    
    private Button buttonDetalle;
    private Button buttonConfigurar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ObservableList<Libro> libros=FXCollections.observableArrayList(new Libro("19KOSPA","El principito","Saint-Exupéry","Tercera",3));
        //tableColumnConfigurar.setCellValueFactory(new PropertyValueFactory<Libro,String>("buttonConfigurar"));
        tableColumnISBN.setCellValueFactory(new PropertyValueFactory<Libro,String>("ISBN"));
        tableColumnTitulo.setCellValueFactory(new PropertyValueFactory<Libro,String>("titulo"));
        tableColumnAutor.setCellValueFactory(new PropertyValueFactory<Libro,String>("autor"));
        tableColumnNCopias.setCellValueFactory(new PropertyValueFactory<Libro,Integer>("NCopias"));
        tableColumnEdicion.setCellValueFactory(new PropertyValueFactory<Libro,String>("edicion"));
        
        tableColumnConfigurar.setCellValueFactory(new PropertyValueFactory<Libro,String>("buttonConfigurar"));
        tableColumnDetalle.setCellValueFactory(new PropertyValueFactory<Libro,String>("buttonDetalle"));
        
        tableViewListaLibros.setItems(libros);
    }    
    
}
