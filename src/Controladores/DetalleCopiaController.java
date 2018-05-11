/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class DetalleCopiaController implements Initializable {

    @FXML
    private BorderPane lector_View;
    @FXML
    private TableView<?> tableViewListaLibros;
    @FXML
    private TableColumn<?, ?> tableColumnISBN;
    @FXML
    private TableColumn<?, ?> tableColumnTitulo;
    @FXML
    private TableColumn<?, ?> tableColumnAutor;
    @FXML
    private TableColumn<?, ?> tableColumnEdicion;
    @FXML
    private TableColumn<?, ?> tableColumnNCopias;
    @FXML
    private Button buttonAgregarCopia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void agregarCopia(ActionEvent event) {
        
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/enki/AgregarCopia.fxml"));
        Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
        Stage agregarCopia = new Stage();
        agregarCopia.setMinWidth(650);
        agregarCopia.setMinHeight(401);
        agregarCopia.setTitle("Agregar Copia");
        agregarCopia.setScene(scene);
        agregarCopia.initModality(Modality.APPLICATION_MODAL);
        agregarCopia.show();
            }
    }
    



