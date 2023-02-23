/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.reservation_snack;
import cinepro.services.reservation_snackCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class DetailSnackController implements Initializable {

 @FXML
    private TableColumn<reservation_snack, Integer> col1;
    @FXML
    private TableColumn<reservation_snack, Integer> col2;
    @FXML
    private TableColumn<reservation_snack, Float> col3;
    @FXML
    private TableColumn<reservation_snack, Integer> col4;  
    @FXML
    private TableView<reservation_snack> tableview;
    public   ObservableList<reservation_snack> data = FXCollections.observableArrayList();
     @FXML
    private Button btnres;
    @FXML
    private Button btnresplace;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         reservation_snackCRUD rs = new reservation_snackCRUD();
       List<reservation_snack> ls = new ArrayList<reservation_snack>();
       ls=rs.entitiesList();
    for(reservation_snack i:ls){
        data.add(i);
    }
      col1.setCellValueFactory(new PropertyValueFactory<reservation_snack, Integer>("id_res_snack")); 
      col2.setCellValueFactory(new PropertyValueFactory<reservation_snack, Integer>("quantite"));
      col3.setCellValueFactory(new PropertyValueFactory<reservation_snack, Float>("prix"));
      col4.setCellValueFactory(new PropertyValueFactory<reservation_snack, Integer>("id_reservation"));
   
      tableview.setItems(data);
    }    
    
    
       public void showPlaceTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailPlace.fxml"));
       try{
       Parent root = loader.load(); 

        btnresplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    public void showresTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Display.fxml"));
       try{
       Parent root = loader.load(); 

        btnres.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
}

