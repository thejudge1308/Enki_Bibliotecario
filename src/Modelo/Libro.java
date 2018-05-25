/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controladores.ConfigurarLibrosController;
import Controladores.DetalleCopiaController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 *
 * @author jnfco
 */
public class Libro 
{
    private String isbn;
    private String titulo;
    private String autor;
    private String edicion;
    private String anio;
    private String ncopias;
    private Button buttonDetalle;
    private Button buttonConfigurar;

    public Libro(String isbn, String titulo, String autor, String edicion,String anio,String ncopias) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.anio = anio;
        this.ncopias = ncopias;
        this.buttonDetalle = new Button("Ver detalle");
        this.buttonConfigurar = new Button("Modificar");
        
        this.buttonConfigurar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/enki/ConfigurarLibros.fxml"));
                    
                    Parent principalParent = fxmlLoader.load();
                    
                    ConfigurarLibrosController controller = fxmlLoader.getController();      
                    
                    //System.out.println("ISBN; "+isbn);
                    Scene scene = null;
                    scene = new Scene(principalParent);
                  
                    Stage configurarLibro = new Stage();
                    configurarLibro.setMinWidth(600);
                    configurarLibro.setMinHeight(325);
                    configurarLibro.setTitle("Modificar Libro");
                    configurarLibro.setScene(scene);
                    configurarLibro.initModality(Modality.APPLICATION_MODAL);
                    controller.setIsbn(isbn);
                    
                    //controller.
                    configurarLibro.show();
                } catch (IOException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        this.buttonDetalle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FXMLLoader fxmlLoader2 = new FXMLLoader();
                    fxmlLoader2.setLocation(getClass().getResource("/enki/DetalleCopia.fxml"));
                    
                    Parent principalParent = fxmlLoader2.load();
                    
                    DetalleCopiaController controller = fxmlLoader2.getController();
                    //System.out.println("ISSBN: "+isbn);
                    
                    Scene scene2 = null;
                    scene2 = new Scene(principalParent);
                    Stage detalleCopia = new Stage();
                    detalleCopia.setMinWidth(695);
                    detalleCopia.setMinHeight(500);
                    detalleCopia.setTitle("Detalle de la copia");
                    detalleCopia.setScene(scene2);
                    detalleCopia.initModality(Modality.APPLICATION_MODAL);
                    controller.setIsbn(isbn);
                    controller.setTitulo(titulo);
                    controller.setAutor(autor);
                    controller.setAño(anio);
                    controller.setEdicion(anio);
                    controller.showTabla();
                    detalleCopia.show();
                } catch (IOException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
    }

    

    public Button getButtonConfigurar() {
        return buttonConfigurar;
    }

    public void setButtonConfigurar(Button buttonConfigurar) {
        this.buttonConfigurar = buttonConfigurar;
    }

    public String getisbn() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
       
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    
    public Button getButtonDetalle() {
        return buttonDetalle;
    }

    public void setButtonDetalle(Button buttonDetalle) {
        this.buttonDetalle = buttonDetalle;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

  
    
    
    
}
