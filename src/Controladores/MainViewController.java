

package Controladores;

import Valores.SingletonUsuario;
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
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class MainViewController implements Initializable {
    @FXML
    private BorderPane contenido_View;
    @FXML
    private Button buttonCrearLibroCopia;
    @FXML
    private Button buttonListaLibros;
    @FXML
    private Button buttonListarEstantes;
     @FXML
    private Button buttonLector;
    @FXML
    private Button buttonBibliotecario;
    @FXML
    private MenuItem menuItemCerrarSesion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        //Privilegios de bibliotecarios
        if(SingletonUsuario.usuario.getTipoUsuario().equals("bib")){
            buttonBibliotecario.setVisible(false);
            buttonCrearLibroCopia.setVisible(false);
        }
        
        BorderPane bp=null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/ListaLibros.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        contenido_View.setCenter(bp);
        
    }    

    
    @FXML
    private void crearCopia(ActionEvent event) throws IOException
    {
        
        
       BorderPane bp2 = FXMLLoader.load(getClass().getResource("/enki/CrearLibroCopia.fxml"));
       contenido_View.setCenter(bp2);
    }
    
    @FXML
    private void listaLibros(ActionEvent event)throws IOException
    {
       BorderPane bp2 = FXMLLoader.load(getClass().getResource("/enki/ListaLibros.fxml"));
        contenido_View.setCenter(bp2);
    }

    @FXML
    private void cerrar(ActionEvent event) {
        
        System.exit(0);
    }
    
     @FXML
     private void onClick_buttonListarEstantes(ActionEvent event){
          BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/ListaEstantes.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contenido_View.setCenter(bp);
     }
     
     @FXML
     private void onClick_buttonCrearEstante(ActionEvent event){
          BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/CrearEstante.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contenido_View.setCenter(bp);
     }
             
     @FXML
     private void onClick_buttonCrearPrestamo(ActionEvent event){
          BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/CrearPrestamo.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contenido_View.setCenter(bp);
     }

    
     @FXML
     private void onClick_buttonLector(ActionEvent event) throws IOException{
          BorderPane bp2 = FXMLLoader.load(getClass().getResource("/enki/ListaLector.fxml"));
        contenido_View.setCenter(bp2);
     }

    @FXML
    private void onActionBibliotecario(ActionEvent event) throws IOException {
        
        BorderPane bp2 = FXMLLoader.load(getClass().getResource("/enki/ListaBibliotecarios.fxml"));
       contenido_View.setCenter(bp2);
    }
            
            @FXML
     private void onClick_buttonListarPrestamo(ActionEvent event){
          BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/ListaPrestamos.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        contenido_View.setCenter(bp);
     }
     
     @FXML
     private void onClick_menuItemCerrarSesion(ActionEvent event){
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Confirmación");
         alert.setHeaderText("Estas seguro que deseas salir?");
         alert.setContentText("Si aceptas, se cerrara la sesion actual.");

         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK){
            //((Node)(event.getSource())).getScene().getWindow().hide(); //Cierra la ventana actual
            System.exit(0);
         } else {
             
         }

     }
     
}
