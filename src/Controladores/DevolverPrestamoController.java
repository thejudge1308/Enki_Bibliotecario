/* 
 * To change this license header, choose License Headers in Project Properties. 
 * To change this template file, choose Tools | Templates 
 * and open the template in the editor. 
 */ 
package Controladores; 
 
import Modelo.Copia;
import java.io.IOException;
import java.net.URL; 
import java.util.ArrayList;
import java.util.ResourceBundle; 
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable; 
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
 
/** 
 * FXML Controller class 
 * 
 * @author mati_ 
 */ 
public class DevolverPrestamoController implements Initializable { 
 
    /** 
     * Initializes the controller class. 
     */ 
    
    @FXML
    private Button more, less, finishButton;
    @FXML
    private Label prestamo;
    @FXML
    private Label fecha1;
    @FXML
    private Label fecha2;
    @FXML
    private TableView table1;
    @FXML
    private TableView table2;
    @FXML
    private TableColumn col1;
    @FXML
    private TableColumn col2;
    @FXML
    private TableColumn col3;
    @FXML
    private TableColumn col4;
    @FXML
    private TextField tfCode;
    
    
    private ObservableList<Copia> listCopias1;
    private ObservableList<Copia> listCopias2;
    ArrayList<Copia> copias1;
    ArrayList<Copia> copias2;
    private String codigoCopia;
    private BorderPane padre;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) { 
        
        tfCode.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
        
            @Override
            public void handle(KeyEvent event) {
                if (!tfCode.getText().isEmpty()){
                    //PRIMERO VALIDAD QUE SE ENCUENTRE EN "NO DEVUELTOS"

                    for (int i=0; i<copias1.size(); i++){
                        if (copias1.get(i).getCodigo().equals(tfCode.getText())){
                            copias2.add(copias1.get(i));
                            copias1.remove(copias1.get(i));
                            createUsersTableForm();
                            tfCode.setText("");
                            return;
                        }
                    }


                    createUsersTableForm();
                }
        
        }});
        
        
        
        this.fecha1.setText("25 de mayo de 2018");
        this.fecha2.setText("28 de mayo de 2018");
        
        copias1 = new ArrayList<>();
        
        Copia c = new Copia("100","Papelucho","Habilitada","Ubicacion 10", "Pablo Neruda", "234-2354-2453");
        Copia c2 = new Copia("101","Papelucho 2","Habilitada","Ubicacion 11", "Pablo Neruda", "234-2354-2454");

        copias1.add(c);
        copias1.add(c2);
        
        copias2 = new ArrayList<>();
        
        Copia c3 = new Copia("103","Papelucho 3","Habilitada","Ubicacion 12", "Pablo Neruda", "234-2354-2455");

        copias2.add(c3);
        
        createUsersTableForm();
        
    }     
    
    public void createUsersTableForm() {
        
        col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col2.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        col3.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col4.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        
        this.loadUsersInformation();
    }

    private void loadUsersInformation() {
        this.listCopias1 = FXCollections.observableArrayList();
        this.listCopias2 = FXCollections.observableArrayList();
        
        if (copias1.size()>0){
            for (Copia  copia: copias1){
                this.listCopias1.add(copia);
            }
            this.more.setDisable(false);
        }
        else {
            this.more.setDisable(true);
        }
        this.table1.setItems(listCopias1);
        
        if (copias2.size()>0){
            for (Copia  copia: copias2){
                this.listCopias2.add(copia);
            }
            this.less.setDisable(false);
        }
        else {
            this.less.setDisable(true);
        }
        this.table2.setItems(listCopias2);
    }
    
    @FXML
    private void onClick_buttonMore(ActionEvent event) {
        
        if (!table1.getSelectionModel().isEmpty()){
            copias2.add((Copia) table1.getSelectionModel().getSelectedItem() );
            copias1.remove((Copia) table1.getSelectionModel().getSelectedItem() );
            this.createUsersTableForm();
        }
    }
    
    
    
    @FXML
    private void onClick_buttonLess(ActionEvent event) {
        
        if (!table2.getSelectionModel().isEmpty()){
            copias1.add((Copia) table2.getSelectionModel().getSelectedItem() );
            copias2.remove((Copia) table2.getSelectionModel().getSelectedItem() );
            this.createUsersTableForm();
        }
    }
    
    @FXML
    private void onClick_buttonFinalizar(ActionEvent event) throws Throwable {
        
        if (copias1.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Éxito!");
            alert.setHeaderText("Prestamo finalizado correctamente");
            alert.setContentText("¡Todas las copias fueron devueltas!");
            alert.showAndWait();
            
            //ACTUALIZAR BD
            
            BorderPane bp = null;
            try {
                bp = FXMLLoader.load(getClass().getResource("/enki/ListaPrestamos.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.padre.setCenter(bp);

            
            this.finalize();
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Precaución");
            alert.setHeaderText("Existen copias por devolver");
            alert.setContentText("¡El prestamo quedará pendiente mientras se realice la devolución de todas las copias!!");
            alert.showAndWait();
            
            //ACTUALIZAR BD
            
            BorderPane bp = null;
            try {
                bp = FXMLLoader.load(getClass().getResource("/enki/ListaPrestamos.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.padre.setCenter(bp);
        }
        
    }
    
    @FXML
    private void onClick_buttonVolver(ActionEvent event) throws Throwable {
        BorderPane bp = null;
        try {
            bp = FXMLLoader.load(getClass().getResource("/enki/ListaPrestamos.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.padre.setCenter(bp);
    }
    
    @FXML
    private void onClick_buttonRegistrar(ActionEvent event) throws Throwable {
        
        if (!tfCode.getText().isEmpty()){
            //PRIMERO VALIDAD QUE SE ENCUENTRE EN "NO DEVUELTOS"
            
            for (int i=0; i<this.copias1.size(); i++){
                if (this.copias1.get(i).getCodigo().equals(tfCode.getText())){
                    copias2.add(this.copias1.get(i));
                    copias1.remove(this.copias1.get(i));
                    this.createUsersTableForm();
                    this.tfCode.setText("");
                    return;
                }
            }
            
            
            this.createUsersTableForm();
        }
        
    }
    

    public String getCodigoCopia() {
        return codigoCopia;
    }

    public void setCodigoCopia(String codigoCopia) {
        this.codigoCopia = codigoCopia;
    }

    public void setCodigo(String codigoCopia) {
        this.codigoCopia = codigoCopia;
        this.prestamo.setText(codigoCopia);
        tfCode.requestFocus();
        
        //Buscar el prestamo en bd
        //Prestamo prestamo = bd.buscarPrestamo(codigoCopia);
        //this.fecha1.setText(prestamo.getFechaPrestamo());
        //this.fecha2.setText(prestamo.getFechaDevolucion());
        
        //en copias1 dejar las que aun no se devuelven
        
        //en copias2 dejar las copias ya devueltas
        
        this.createUsersTableForm();
    }

    void setPadre(BorderPane contenido_View) {
        this.padre = contenido_View;
    }

    
    
    
     
} 