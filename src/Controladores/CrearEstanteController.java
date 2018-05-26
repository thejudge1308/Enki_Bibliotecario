/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.CrearLectorController.isValidEmailAddress;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mati_
 */
public class CrearEstanteController implements Initializable {

    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField textFieldNumeroEstante;
    @FXML
    private TextField textFieldCantidadEstantes;
    @FXML
    private TextField textFieldDeweyInf;
    @FXML
    private TextField textFieldDeweySup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    private void crearEstante(){
        //String codigo = getSaltString().equals("")?"":getSaltString();
        String numero=textFieldNumeroEstante.getText().equals("")?"":textFieldNumeroEstante.getText();
        String intervaloInf=textFieldDeweyInf.getText().equals("")?"":textFieldDeweyInf.getText();
        String intervaloSup=textFieldDeweySup.getText().equals("")?"":textFieldDeweySup.getText();
        String cantidadNiveles = textFieldCantidadEstantes.getText().equals("")?"":textFieldCantidadEstantes.getText();
        if(!numero.equals("")){
            
                try {
                this.crearEstanteEnBaseDeDatos(numero, intervaloInf, intervaloSup, cantidadNiveles);
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
               // Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println(ex);
                //Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(CrearEstanteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
            }
        else
        {
            Alert alert = new Alert(Alert.AlertType.NONE, "Ingrese email correctamente", ButtonType.OK);
alert.showAndWait();
        }
            
        
        
    }
    
    
    
    //TODO: Decodificar JSON
    /**
     * 
     * @param rut
     * @param nombre
     * @param apellidoPat
     * @param apellidoMat
     * @param direccion
     * @param email
     * @param telefono 
     */
    
    public void crearEstanteEnBaseDeDatos(String numero,String intervaloInf ,
                                         String intervaloSup,String cantidadNiveles) throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.crearEstantePHP);
    Map<String,Object> params = new LinkedHashMap<>();
   //params.put("codigo",codigo.trim());
    params.put("numero", numero.trim());
    params.put("intervaloinf", intervaloInf.trim());
    params.put("intervalosup", intervaloSup.trim());
    params.put("cantidadniveles", cantidadNiveles.trim());
    StringBuilder postData = new StringBuilder();
    for (Map.Entry<String,Object> param : params.entrySet()) {
        if (postData.length() != 0) postData.append('&');
        postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
        postData.append('=');
        postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
    }

    // Convierte el array, para ser enviendo
    byte[] postDataBytes = postData.toString().getBytes("UTF-8");

    // Conectar al server
    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    
    // Configura
    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
    conn.setDoOutput(true);
    conn.getOutputStream().write(postDataBytes);

    // Obtiene la respuesta del servidor
    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    
    String response="";
    System.out.println(in);
    for (int c; (c = in.read()) >= 0;)
       response=response + (char)c;
    
    //Convierte el json enviado (decodigicado)
    JSONObject obj = new JSONObject(response);
    String mensaje = obj.getString("mensaje");
    
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Mensaje");
    alerta.setContentText(mensaje);
    alerta.showAndWait();
   // System.out.println(response);
         
       
     }

    @FXML
    private void guardar(ActionEvent event) {
        crearEstante();
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
     protected String getSaltString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
    
}
