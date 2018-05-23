/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controladores.LoginController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

/**
 *
 * @author Mn_go
 */
public class Bibliotecario 
{
    String rut;
    String nombre;
    String apellidoP;
    String apellidoM;
    Button buttonConfiguraciones;
    CheckBox habilitado;
    
    public Bibliotecario(String rut, String nombre, String apellidoP, String apellidoM,String habilitado)
    {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.buttonConfiguraciones = new Button("Modificar");
        this.habilitado = new CheckBox();
        
        this.buttonConfiguraciones.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/enki/ModificarBibliotecario.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setResizable(false);
                    stage.setTitle("Config. de conexion de servidor"); 
                    stage.setScene(scene);
                    stage.setAlwaysOnTop(true);
                    stage.showAndWait();

                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        this.habilitado.setSelected(true);
        this.habilitado.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hola");
            }
        });
    }
    
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public Button getButtonConfiguraciones() {
        return buttonConfiguraciones;
    }

    public void setButtonConfiguraciones(Button buttonConfiguraciones) {
        this.buttonConfiguraciones = buttonConfiguraciones;
    }

    public CheckBox getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(CheckBox habilitado) {
        this.habilitado = habilitado;
    }
}
