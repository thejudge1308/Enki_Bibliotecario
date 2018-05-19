/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Button buttonDetalle;
    private Button buttonConfigurar;

    public Libro(String isbn, String titulo, String autor, String edicion,String anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.anio = anio;
        this.buttonDetalle = new Button("Ver detalle");
        this.buttonConfigurar = new Button("Configurar");
        
        this.buttonConfigurar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/enki/ConfigurarLibros.fxml"));
        Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
        Stage configurarLibro = new Stage();
        configurarLibro.setMinWidth(650);
        configurarLibro.setMinHeight(413);
        configurarLibro.setTitle("Configurar Libro");
        configurarLibro.setScene(scene);
        configurarLibro.initModality(Modality.APPLICATION_MODAL);
        configurarLibro.show();
            }
        });
        
        
        this.buttonDetalle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader2 = new FXMLLoader();
        fxmlLoader2.setLocation(getClass().getResource("/enki/DetalleCopia.fxml"));
        Scene scene2 = null;
                try {
                    scene2 = new Scene(fxmlLoader2.load(), 600, 400);
                } catch (IOException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
        Stage detalleCopia = new Stage();
        detalleCopia.setMinWidth(750);
        detalleCopia.setMinHeight(365);
        detalleCopia.setTitle("Detalle copia");
        detalleCopia.setScene(scene2);
        detalleCopia.initModality(Modality.APPLICATION_MODAL);
        detalleCopia.show();
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
