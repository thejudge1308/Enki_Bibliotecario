/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



/**
 *
 * @author jnfco
 */
public class Libro 
{
    private String ISBN;
    private String titulo;
    private String autor;
    private String edicion;
    private int nCopias;
    private Button buttonDetalle;
    private Button buttonConfigurar;

    public Libro(String ISBN, String titulo, String autor, String edicion, int nCopias) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.nCopias = nCopias;
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
        configurarLibro.setTitle("Configurar Libro");
        configurarLibro.setScene(scene);
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
        detalleCopia.setTitle("Detalle copia");
        detalleCopia.setScene(scene2);
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

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

    public int getNCopias() {
        return nCopias;
    }

    public void setNCopias(int nCopias) {
        this.nCopias = nCopias;
    }

    public Button getButtonDetalle() {
        return buttonDetalle;
    }

    public void setButtonDetalle(Button buttonDetalle) {
        this.buttonDetalle = buttonDetalle;
    }
    
    
    
}
