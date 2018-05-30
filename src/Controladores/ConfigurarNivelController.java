
package Controladores;

import Modelo.Estante;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Jnfo
 */
public class ConfigurarNivelController implements Initializable {

    @FXML
    private Button buttonGuardar;
    @FXML
    private Button buttonCancelar;
    @FXML
    private ComboBox<String> comboBoxEstante;
    @FXML
    private ComboBox<String> comboBoxNivel;
    @FXML
    private Label idLabel;

    private String codigo;
    
    private List<Estante> estantes ;
    private String codigoEstante;
    private String codigoNivel;
    private String codigoCopia;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void onClick_buttonGuardar(ActionEvent event) {
        
    }

    @FXML
    private void onClick_buttonCancelar(ActionEvent event) {
        
    }
    
    public void setCodigo(String codigo){
        try {
            this.codigo = codigo;
            this.idLabel.setText(codigo);
            getEstantes();
            this.comboBoxEstante.getSelectionModel().select(0);
            
            for(int i=0;i<estantes.size();i++)
            {
            if(comboBoxEstante.getSelectionModel().getSelectedItem().equals(estantes.get(i).getCodigo()))
                
            {
                codigoEstante=estantes.get(i).getCodigo();
               
            }
            }
            
            
            obtenerNiveles(codigoEstante, estantes);
            
            this.comboBoxNivel.getSelectionModel().select(0);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ConfigurarNivelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(ConfigurarNivelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarNivelController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ConfigurarNivelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void getEstantes() throws MalformedURLException, UnsupportedEncodingException, ProtocolException, IOException, JSONException{
        
    URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.obtenerEstantePHP);
    Map<String,Object> params = new LinkedHashMap<>();
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
         System.out.println("mensaje "+mensaje);
    if(mensaje.equals("false")){
        //System.out.println("no hay nada");
    }else{
        estantes = new ArrayList<Estante>();
        Estante estante;
        JSONArray jsonArray = obj.getJSONArray("datos");
        for(int i = 0; i < jsonArray.length(); i++){
                        String id = jsonArray.getJSONObject(i).getString("codigo")==null?"":jsonArray.getJSONObject(i).getString("codigo");
            String numero = jsonArray.getJSONObject(i).getString("numero")==null?"":jsonArray.getJSONObject(i).getString("numero");
            String niveles=jsonArray.getJSONObject(i).getString("cantidadniveles")==null?"":jsonArray.getJSONObject(i).getString("cantidadniveles");
            String codigo=jsonArray.getJSONObject(i).getString("codigo")==null?"":jsonArray.getJSONObject(i).getString("codigo");
            
            String intervaloInf=jsonArray.getJSONObject(i).getString("intervaloInf")==null?"":jsonArray.getJSONObject(i).getString("intervaloInf");
            String intervaloSup=jsonArray.getJSONObject(i).getString("intervaloSup")==null?"":jsonArray.getJSONObject(i).getString("intervaloSup");
           
              estante= new Estante(id,numero,niveles,intervaloInf,intervaloSup);
            //System.out.println(lector.getRut());
            estantes.add(estante);
         
            comboBoxEstante.getItems().addAll(numero);
            //System.out.println("Codigo EStante: "+codigo);
            //System.out.println("Numero EStante: "+numero);
            
            
        }
        
        
        
        
        
    }
}
    
    public void obtenerNiveles(String codigo,List<Estante> estantes)
    {
        
        try {
            URL url = new URL(Valores.SingletonServidor.getInstancia().getServidor()+"/"+Valores.ValoresEstaticos.obtenerNivelPHP);
            Map<String,Object> params = new LinkedHashMap<>();
            params.put("codigoEstante",codigo);
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
            System.out.println("mensaje "+mensaje);
            if(mensaje.equals("false")){
                //System.out.println("no hay nada");
            }else{
                
                
                JSONArray jsonArray = obj.getJSONArray("datos");
                String numero;
                for(int i = 0; i < jsonArray.length(); i++){
                    numero = jsonArray.getJSONObject(i).getString("codigo")==null?"":jsonArray.getJSONObject(i).getString("codigo");
                    comboBoxNivel.getItems().addAll(numero);
                    
                }
                
                
            }       } catch (MalformedURLException ex) {
            Logger.getLogger(CrearLibroCopiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CrearLibroCopiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException ex) {
            Logger.getLogger(CrearLibroCopiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrearLibroCopiaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(CrearLibroCopiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onClick_comboBoxEstante(ActionEvent event) {
        
         for(int i=0;i<estantes.size();i++)
        {
            if(comboBoxEstante.getSelectionModel().getSelectedItem().equals(estantes.get(i).getCodigo()))
                
            {
                codigoEstante=estantes.get(i).getCodigo();
               
            }
        }
        this.comboBoxNivel.getSelectionModel().clearSelection();
        obtenerNiveles(codigoEstante,estantes);
        codigoEstante = codigoEstante;
    }

    @FXML
    private void onClick_comboBoxNivel(ActionEvent event) {
        
    }
    
}
