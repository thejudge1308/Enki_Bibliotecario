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
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import enki.*;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class MainViewController implements Initializable {
    @FXML
    private BorderPane contenido_View;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/LectorMainView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //bp.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
        contenido_View.setCenter(bp);
    }    

    @FXML
    private void crear(ActionEvent event) throws IOException {
        System.out.println("Hola");
        
    }
    
    @FXML
    private void crearCopia(ActionEvent event) throws IOException
    {
        
        
       BorderPane bp2 = FXMLLoader.load(getClass().getResource("/enki/copiaLibro.fxml"));
       contenido_View.setCenter(bp2);
    }
    
    @FXML
    private void listaLibros(ActionEvent event)throws IOException
    {
        BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/LectorMainView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //bp.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
        contenido_View.setCenter(bp);
    }
    
    @FXML
    private void listaEstantes(ActionEvent event) throws IOException
    {
       BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/EstanteMainView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //bp.setPrefSize(Double.MAX_VALUE,Double.MAX_VALUE);
        contenido_View.setCenter(bp);
    }
    
}
