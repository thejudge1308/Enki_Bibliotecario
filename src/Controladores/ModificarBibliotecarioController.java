/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author Mn_go
 */
public class ModificarBibliotecarioController {

    @FXML
    private Button buttonAceptar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField textBoxRut;
    @FXML
    private TextField textBoxNombre;
    @FXML
    private TextField textBoxApellidoPaterno;
    @FXML
    private TextField textBoxApellidoMaterno;
    @FXML
    private TextField textBoxEmail;
    @FXML
    private TextField textBoxTelefono;
    @FXML
    private TextField textBoxDIreccion;
    @FXML
    private TextField textBoxNombreContactoEmergencia;
    @FXML
    private TextField textBoxTelefonoContactoEmergencia;
    @FXML
    private TextField textBoxContraseña;

    @FXML
    private void onClick_buttonAceptar(ActionEvent event) {
    }

    @FXML
    private void onClick_buttonCancelar(ActionEvent event) {
    }
    
    void obtenerDatos()
    {
        
    }
    
    public void llenarCampos(String rut, String nombre, String aPaterno, String aMaterno, String email, String telefono,
            String direccion, String nombreContacto, String telefonoContacto, String contrasena)
    {
        this.textBoxRut.setText(rut);
        this.textBoxNombre.setText(nombre);
        this.textBoxApellidoPaterno.setText(aPaterno);
        this.textBoxApellidoMaterno.setText(aMaterno);
        this.textBoxEmail.setText(email);
        this.textBoxTelefono.setText(telefono);
        this.textBoxDIreccion.setText(direccion);
        this.textBoxNombreContactoEmergencia.setText(nombreContacto);
        this.textBoxTelefonoContactoEmergencia.setText(telefonoContacto);
        this.textBoxContraseña.setText(contrasena);
    }
    
}
