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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author patricio
 */
public class ListaLectorController implements Initializable {

    @FXML
    private Button buttonNuevoLector;
    @FXML
    private Button buttonBuscar;
    @FXML
    private TextField textBoxBuscar;
    @FXML
    private RadioButton radioButtonTodos;
    @FXML
    private RadioButton radioButtonRut;
    @FXML
    private RadioButton radioButtonNombre;
    @FXML
    private TableView<?> tableViewLectores;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClick_buttonNuevoLector(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/enki/CrearLector.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setMinWidth(600);
            stage.setMinHeight(321);
            stage.setTitle(Valores.ValoresEstaticos.enki);
            stage.setScene(scene);
          //  ((Node)(event.getSource())).getScene().getWindow().hide(); //Cierra la ventana actual
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(ListaLectorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void onClick_buttonBuscar(ActionEvent event) {
        
    }
    
}
