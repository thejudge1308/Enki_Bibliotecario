/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class CrearLibroCopiaController implements Initializable {

    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private TextField textBoxISBN;
    @FXML
    private TextField textBoxAutor;
    @FXML
    private TextField textBoxAño;
    @FXML
    private TextField textBoxDewey;
    @FXML
    private TextField textBoxTitulo;
    @FXML
    private TextField textBoxEdicion;
    @FXML
    private ComboBox<?> comboBoxEstante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardar(ActionEvent event) {
        
      
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
 
    
    
    
     private void crearLibror(){
        String isbn = textBoxISBN.getText().equals("")?"":textBoxISBN.getText();
        String autor=textBoxAutor.getText().equals("")?"":textBoxAutor.getText();
        String anio=textBoxAño.getText().equals("")?"":textBoxAño.getText();
        String dewey=textBoxDewey.getText().equals("")?"":textBoxDewey.getText();
        String titulo = textBoxTitulo.getText().equals("")?"":textBoxTitulo.getText();
        String  edicion= textBoxEdicion.getText().equals("")?"":textBoxEdicion.getText();
        
        if(!isbn.equals("")){
            
            try {
                this.crearLibroEnBaseDeDatos(isbn, autor, anio,dewey , titulo, edicion);
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
               // Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println(ex);
                //Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText("No se puede realizar esta operación.");
            alerta.setContentText("EL campo ISBN esta vacio, ingrese un ISBN valido.");
            alerta.showAndWait();
        }
        
        
    }
    
    
    //TODO: Decodificar JSON
    /**
     * 
     * @param ISBN
     * @param Autor
     * @param Año
     * @param Dewey
     * @param Titulo
     * @param Edición
     */
    
    public void crearLibroEnBaseDeDatos(String isbn,String autor,String anio,String dewey,
                                         String titulo,String edicion) throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+Valores.ValoresEstaticos.crearLectorPHP);
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("isbn", isbn);
    params.put("autor", autor);
    params.put("anio", anio);
    params.put("dewey", dewey);
    params.put("titulo",titulo);
    params.put("edicion", edicion);
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
    
}
