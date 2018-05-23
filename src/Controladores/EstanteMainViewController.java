
package Controladores;

import Modelo.Estante;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Matias
 */
public class EstanteMainViewController implements Initializable {

   /* @FXML
    private TableView<Estante> tableViewListaEstantes;
    @FXML
    private TableColumn<Estante,Integer> tableColumnNumero;
    @FXML
    private TableColumn<Estante,Integer> tableColumnNiveles;
    @FXML
    private TableColumn<Estante,Integer> tableColumnRangoInferior;
    @FXML
    private TableColumn<Estante,Integer> tableColumnRangoSuperior;
    @FXML
    private TableColumn<Estante,String> tableColumnConfigurar;

    private Button buttonConfigurar;*/

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        /*ObservableList<Estante> estantes=FXCollections.observableArrayList(new Estante(1,5,200,299));
        //tableColumnConfigurar.setCellValueFactory(new PropertyValueFactory<Libro,String>("buttonConfigurar"));
        tableColumnNumero.setCellValueFactory(new PropertyValueFactory<Estante,Integer>("numero"));
        tableColumnNiveles.setCellValueFactory(new PropertyValueFactory<Estante,Integer>("niveles"));
        tableColumnRangoInferior.setCellValueFactory(new PropertyValueFactory<Estante,Integer>("rangoInferior"));
        tableColumnRangoSuperior.setCellValueFactory(new PropertyValueFactory<Estante,Integer>("rangoSuperior"));        
        
        tableColumnConfigurar.setCellValueFactory(new PropertyValueFactory<Estante,String>("buttonConfigurar"));        
        
        tableViewListaEstantes.setItems(estantes);*/
    } 
    
}
