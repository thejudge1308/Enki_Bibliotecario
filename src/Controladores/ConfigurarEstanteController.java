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
    
    private String cantidadNiveles;
    private String numero;
    private String codigo;
    private String codigoEstante;
    @FXML
    private Button buttonAgregar;
    @FXML
    private Button buttonQuitar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Cantidad niveles: "+cantidadNiveles);
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
    private void agregar(ActionEvent event) {
        
        try {
            System.out.println("Hola");
            int niveles=Integer.parseInt(cantidadNiveles);
            System.out.println("Cantidad de niveles al agregar: "+cantidadNiveles);
            niveles=niveles+1;
            cantidadNiveles=String.valueOf(niveles);
            modificarEstante(cantidadNiveles, numero);
            obtenerDatosEstante(numero);
            setCodigo(cantidadNiveles);
            setCodigoEstante(numero);
            crearNivel(codigo,codigoEstante);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void quitar(ActionEvent event) {
    }
    
      private void modificarEstante(String cantidadNiveles,String numero){ 
        cantidadNiveles=cantidadNiveles.equals("")?"":cantidadNiveles;
            System.out.println("Cantidad niveles en modificar: "+cantidadNiveles);
            try {
                this.modificarEstanteEnBaseDeDatos(cantidadNiveles,numero);
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
               // Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.out.println(ex);
                //Logger.getLogger(CrearLectorController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
            }
  
        
    }
     
     
     public void modificarEstanteEnBaseDeDatos(String cantidadNiveles,String numero) throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.modificarEstantePHP);
    Map<String,Object> params = new LinkedHashMap<>();
    params.put("numero", numero.trim());
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
    
    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
    //alerta.setTitle("Mensaje");
    alerta.setContentText(mensaje);
    alerta.showAndWait();
   // System.out.println(response);
}
     
     
     
     private void crearNivel(String codigo, String codigoEstante){
        try {
            //String codigo = getSaltString().equals("")?"":getSaltString();
            int codigoInt = Integer.parseInt(codigo);
            codigo=String.valueOf(codigoInt);
            codigo=codigo.equals("")?"":codigo;
            codigoEstante=codigoEstante.equals("")?"":codigoEstante;
            
            this.crearNivelEnBaseDeDatos(codigo,codigoEstante);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ConfigurarEstanteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
           
            
            
        
        
            
        
        
    
    
    
    
    //TODO: Decodificar JSON
    /**
     * 
     *
     */
    
    public void crearNivelEnBaseDeDatos(String codigo,String codigoEstante) throws MalformedURLException, UnsupportedEncodingException, IOException, JSONException{
    
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.crearNivelPHP);
    Map<String,Object> params = new LinkedHashMap<>();
   //params.put("codigo",codigo.trim());
        System.out.println("CODIGO: "+codigo);
        System.out.println("CODIGO: "+codigoEstante);
    params.put("codigo", codigo.trim());
    params.put("codigoEstante", codigoEstante.trim());
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
    
    public void setCodigo(String codigo)
    {
        this.codigo=codigo;
    }
    
    public void setCodigoEstante(String codigoEstante)
    {
        this.codigoEstante=codigoEstante;
    }

}
