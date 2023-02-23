/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.reservation;
import cinepro.services.reservationCRUD;
import cinepro.utils.cineproConnexion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class DisplayController implements Initializable {
  
    @FXML
    private TableColumn<reservation, Integer> col1;
    @FXML
    private TableColumn<reservation, Float> col2;
    @FXML
    private TableColumn<reservation, Integer> col3;
    @FXML
    private TableColumn<reservation, Integer> col4;
    @FXML
    private TableColumn<reservation, Boolean> col5;
    @FXML
    private TableColumn<reservation, Timestamp> col6;
    @FXML
    private TableColumn<reservation, Timestamp> col7;
    @FXML
    private TableView<reservation> tableview;
    public   ObservableList<reservation> data = FXCollections.observableArrayList();
    @FXML
    private Button btnresplace;
    @FXML
    private Button btnressnack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       reservationCRUD rs = new reservationCRUD();
       List<reservation> ls = new ArrayList<reservation>();
       ls=rs.entitiesList();
    for(reservation i:ls){
        data.add(i);
    }
    
    col1.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_reservation"));
    col2.setCellValueFactory(new PropertyValueFactory<reservation, Float>("prix_final"));
    col3.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_user"));
    col4.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_film"));
    col5.setCellValueFactory(new PropertyValueFactory<reservation, Boolean>("state"));
    col6.setCellValueFactory(new PropertyValueFactory<reservation, Timestamp>("start_time"));
    col7.setCellValueFactory(new PropertyValueFactory<reservation, Timestamp>("end_time"));
        
    tableview.setItems(data);

    }    
      // Define the TableView and TableColumn objects from the FXML file
   public void showPlaceTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailPlace.fxml"));
       try{
       Parent root = loader.load(); 

        btnresplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    public void showSnackTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailSnack.fxml"));
       try{
       Parent root = loader.load(); 

        btnresplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    // Define a method to load the data and populate the TableView
    public void loadData() throws SQLException {
        // Connect to your database
       
        
       
    }
}

