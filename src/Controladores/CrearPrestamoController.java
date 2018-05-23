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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author mati_
 */
public class CrearPrestamoController implements Initializable {

    @FXML
    private TextField textFieldRut;
    @FXML
    private Button buttonBuscar;
    @FXML
    private TextField textFieldCodigo1;
    @FXML
    private Button buttonAgregarPrestamo1;
    @FXML
    private TextField textFieldCodigo2;
    @FXML
    private Button buttonAgregarPrestamo2;
    @FXML
    private TextField textFieldCodigo3;
    @FXML
    private Button buttonAceptar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Label labelPrestamo2;
    @FXML
    private Label labelPrestamo3;
    @FXML
    private Button buttonQuitarPrestamo2;
    @FXML
    private Button buttonQuitarPrestamo3;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        textFieldCodigo2.setVisible(false);
        buttonAgregarPrestamo2.setVisible(false);
        labelPrestamo2.setVisible(false);
        buttonQuitarPrestamo2.setVisible(false);
        
        textFieldCodigo3.setVisible(false);
        labelPrestamo3.setVisible(false);
        buttonQuitarPrestamo3.setVisible(false);
        
        
    }    

    @FXML
    private void agregarPrestamo1(ActionEvent event) {
        
        textFieldCodigo2.setVisible(true);
        buttonAgregarPrestamo2.setVisible(true);
        labelPrestamo2.setVisible(true);
        buttonAgregarPrestamo1.setVisible(false);
        buttonQuitarPrestamo2.setVisible(true);
    }

    @FXML
    private void agregarPrestamo2(ActionEvent event) {
        
        textFieldCodigo3.setVisible(true);
        labelPrestamo3.setVisible(true);
        buttonAgregarPrestamo2.setVisible(false);
        buttonQuitarPrestamo3.setVisible(true);
        buttonQuitarPrestamo2.setVisible(false);
    }


    @FXML
    private void aceptar(ActionEvent event) {
        
        buttonAgregarPrestamo1.setVisible(true);
        
        textFieldCodigo2.setVisible(false);
        buttonAgregarPrestamo2.setVisible(false);
        labelPrestamo2.setVisible(false);
        
        textFieldCodigo3.setVisible(false);
        labelPrestamo3.setVisible(false);
        
       textFieldCodigo1.setText("");
       textFieldCodigo2.setText("");
       textFieldCodigo3.setText("");
       textFieldRut.setText("");
       
       buttonQuitarPrestamo3.setVisible(false);
       buttonQuitarPrestamo2.setVisible(false);
       
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void quitarPrestamo2(ActionEvent event) {
        
        buttonQuitarPrestamo2.setVisible(false);
        labelPrestamo2.setVisible(false);
        textFieldCodigo2.setVisible(false);
        buttonAgregarPrestamo2.setVisible(false);
        buttonAgregarPrestamo1.setVisible(true);
       
    }

    @FXML
    private void quitarPrestam3(ActionEvent event) {
        buttonQuitarPrestamo3.setVisible(false);
        labelPrestamo3.setVisible(false);
        textFieldCodigo3.setVisible(false);
        buttonAgregarPrestamo2.setVisible(true);
        buttonQuitarPrestamo2.setVisible(true);
    }
    
}
