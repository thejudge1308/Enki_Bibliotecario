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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class ConfigurarLibrosController implements Initializable {

    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private BorderPane borderPaneConfigurarLibros;
    @FXML
    private TextField textFieldTitulo;
    @FXML
    private TextField textFieldEdicion;
    @FXML
    private TextField textFieldAutor;
    @FXML
    private TextField textFieldAnio;
    
    private String isbn;

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
     //Stage stage = (Stage) borderPaneConfigurarLibros.getScene().getWindow();
     //stage.initModality(Modality.APPLICATION_MODAL);
    }    

    @FXML
    private void guardar(ActionEvent event) {
        
        
        modificarLibro();
        ((Stage)borderPaneConfigurarLibros.getScene().getWindow()).close();
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        ((Stage)borderPaneConfigurarLibros.getScene().getWindow()).close();
    }

     private void modificarLibro(){
         
        
        String autor=textFieldAutor.getText().equals("")?"":textFieldAutor.getText();
        String anio=textFieldAnio.getText().equals("")?"":textFieldAnio.getText();
        String titulo = textFieldTitulo.getText().equals("")?"":textFieldTitulo.getText();
        String edicion= textFieldEdicion.getText().equals("")?"":textFieldEdicion.getText();
        
        
        
        
            
            try {
                this.modificarLibroEnBaseDeDatos(isbn, autor, anio, titulo, edicion);
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
               // Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println(ex);
                //Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ConfigurarLibrosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        
        
        
        
    }
     
     
      public void modificarLibroEnBaseDeDatos(String isbn,String autor,String anio,
                                         String titulo,String edicion) throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.modificarLibroPHP);
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("isbn", isbn);
    params.put("autor", autor);
    params.put("anio", anio);
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
          System.out.println(response);
          //System.out.println("ISBN; "+isbn);
    JSONObject obj = new JSONObject(response);
    String mensaje = obj.getString("mensaje");
    
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Mensaje");
    alerta.setContentText(mensaje);
    alerta.showAndWait();
   // System.out.println(response);
}
     
     public void setIsbn(String isbn)
        {
            this.isbn=isbn;
        }
    
}
