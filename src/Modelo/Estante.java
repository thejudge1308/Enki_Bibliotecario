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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author mati_
 */
public class Estante {
    
    int codigoEstante;
    int numeroNiveles;
    int rangoInferior;
    int rangoSuperior;
    Button buttonConfigurar;

    public Estante(int codigoEstante, int numeroNiveles, int rangoInferior, int rangoSuperior) {
        this.codigoEstante = codigoEstante;
        this.numeroNiveles = numeroNiveles;
        this.rangoInferior = rangoInferior;
        this.rangoSuperior = rangoSuperior;
        this.buttonConfigurar = new Button("Modificar");
        
        this.buttonConfigurar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/enki/ConfigurarEstante.fxml"));
        Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 600, 400);
                } catch (IOException ex) {
                    Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
                }
        Stage configurarLibro = new Stage();
        configurarLibro.setMinWidth(650);
        configurarLibro.setMinHeight(413);
        configurarLibro.setTitle("Configurar Estante");
        configurarLibro.setScene(scene);
        configurarLibro.initModality(Modality.APPLICATION_MODAL);
        configurarLibro.show();
            }
        });

    }

    public Estante(String numero, String nivel, String rangoInf, String rangoSup) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getCodigoEstante() {
        return codigoEstante;
    }

    public void setCodigoEstante(int codigoEstante) {
        this.codigoEstante = codigoEstante;
    }

    public int getNumeroNiveles() {
        return numeroNiveles;
    }

    public void setNumeroNiveles(int numeroNiveles) {
        this.numeroNiveles = numeroNiveles;
    }

    public int getRangoInferior() {
        return rangoInferior;
    }

    public void setRangoInferior(int rangoInferior) {
        this.rangoInferior = rangoInferior;
    }

    public int getRangoSuperior() {
        return rangoSuperior;
    }

    public void setRangoSuperior(int rangoSuperior) {
        this.rangoSuperior = rangoSuperior;
    }

    public Button getButtonConfigurar() {
        return buttonConfigurar;
    }

    public void setButtonConfigurar(Button buttonConfigurar) {
        this.buttonConfigurar = buttonConfigurar;
    }
    
    
    
    
}
