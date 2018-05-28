/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelo.Estante;
import Modelo.Lector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author jnfco
 */
public class ConfigurarEstanteController implements Initializable {

    @FXML
    private Button buttonAceptar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Label labelCantidad;
    @FXML
    private Button buttonAgregar;
    @FXML
    private Button buttonQuitar;
    
    private String cantidadNiveles;
    private String numero;
    private String codigo;
    private String codigoEstante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //System.out.println("Cantidad niveles: "+cantidadNiveles);
        labelCantidad.setText(cantidadNiveles);
    }    

    @FXML
    private void aceptar(ActionEvent event) {
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }
    
    public void setCantidadNiveles(String cantidadNiveles,String numero)
    {
        try {
            System.out.println("Niveles: "+cantidadNiveles);
            System.out.println("Numero: "+numero);
            this.cantidadNiveles=cantidadNiveles;
            obtenerDatosEstante(numero);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
     public void obtenerDatosEstante(String numero)throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
     URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.obtenerEstanteUnicoPHP);
     Map<String,Object> params = new LinkedHashMap<>();
     params.put("numero", numero.trim()); 
        
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
    //System.out.println(in);
    for (int c; (c = in.read()) >= 0;)
       response=response + (char)c;
    
    //Convierte el json enviado (decodigicado)
    JSONObject obj = new JSONObject(response);
    String mensaje = obj.getString("mensaje");
    
    if(mensaje.equals("false")){
        //System.out.println("no hay nada");
    }else{
        List<Estante> estantes = new ArrayList<Estante>();
        Estante estante;
        JSONArray jsonArray = obj.getJSONArray("datos");
            numero = jsonArray.getJSONObject(0).getString("numero")==null?"":jsonArray.getJSONObject(0).getString("numero");
            //String cantidadNiveles=jsonArray.getJSONObject(0).getString("cantidadniveles")==null?"":jsonArray.getJSONObject(0).getString("cantidadniveles");
          
            //Editar
            this.labelCantidad.setText(cantidadNiveles);
            this.cantidadNiveles=cantidadNiveles;
            this.numero=numero;
    }
        
    }

    @FXML
    private void onClick_buttonAgregar(ActionEvent event) {
        System.out.println(codigo);
        try {
            agregarNivelEnEstante(codigo);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     //Listo
    public void agregarNivelEnEstante(String codigo) throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.crearNivelPHP);
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("codigo", codigo.trim());
    
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
    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    
    String response="";
    System.out.println(in);
    for (int c; (c = in.read()) >= 0;)
       response=response + (char)c;
    
    //Convierte el json enviado (decodigicado)
          System.out.println(response);
          //System.out.println("ISBN; "+isbn);
    JSONObject obj = new JSONObject(response);
    String mensaje = obj.getString("mensaje");
    String accion = obj.getString("accion");
    if(accion.equals("true")){
        int n = (Integer.parseInt(labelCantidad.getText().trim())+1);
        this.labelCantidad.setText(n+"");
    }
    
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Informacion");
    alerta.setContentText(mensaje);
    alerta.showAndWait();
   // System.out.println(response);
}
         
    public void quitarNivelesEnLaBd()throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.eliminarNivelPHP);
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("codigo", codigo.trim());
    
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
    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    
    String response="";
    System.out.println(in);
    for (int c; (c = in.read()) >= 0;)
       response=response + (char)c;
    
    //Convierte el json enviado (decodigicado)
          System.out.println(response);
          //System.out.println("ISBN; "+isbn);
    JSONObject obj = new JSONObject(response);
    String mensaje = obj.getString("mensaje");
    String accion = obj.getString("accion");
    if(accion.equals("true")){
        int n = (Integer.parseInt(labelCantidad.getText().trim())-1);
        this.labelCantidad.setText(n+"");
    }
    
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    alerta.setTitle("Informacion");
    alerta.setContentText(mensaje);
    alerta.showAndWait();
   // System.out.println(response);
}
   
    public void setCodigo(String codigo)
    {
        this.codigo=codigo;
    }
    
    public void setCodigoEstante(String codigoEstante)
    {
        this.codigoEstante=codigoEstante;
    }

    @FXML
    private void onClick_buttonQuitar(ActionEvent event) {
        if(this.labelCantidad.getText().equals("1")){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Alerta");
            alerta.setContentText("Un estante debe tener minimo un nivel");
            alerta.showAndWait();
        }else{
            
            try {
                quitarNivelesEnLaBd();
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
