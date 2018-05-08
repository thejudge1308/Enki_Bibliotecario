/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class ConfigurarLibrosController implements Initializable {

    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private BorderPane borderPaneConfigurarLibros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     //Stage stage = (Stage) borderPaneConfigurarLibros.getScene().getWindow();
     //stage.initModality(Modality.APPLICATION_MODAL);
    }    

    @FXML
    private void guardar(ActionEvent event) {
        ((Stage)borderPaneConfigurarLibros.getScene().getWindow()).close();
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ((Stage)borderPaneConfigurarLibros.getScene().getWindow()).close();
    }
    
}
