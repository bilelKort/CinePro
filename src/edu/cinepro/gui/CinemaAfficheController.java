/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.cinema;
import edu.cinepro.entities.salle;
import edu.connexion3A18.services.CinemaCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CinemaAfficheController implements Initializable {

   
    @FXML
    private TableView<cinema> tableview;
   
    @FXML
    private TableColumn<cinema, Integer> id_cinema;
    @FXML
    private TableColumn<cinema, String> nom;
    @FXML
    private TableColumn<cinema, String>localisation;
    @FXML
    private TableColumn<cinema, String> description;
    @FXML
    private TableColumn<cinema, String> photo;
    @FXML
    private TableColumn<cinema, Integer> id_user;
    public ObservableList<cinema> k=FXCollections.observableArrayList();
    @FXML
    private Button idbtn;
    @FXML
    private TableColumn edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  CinemaCRUD cd= new CinemaCRUD();
List<cinema> liste=new ArrayList<cinema>();
      liste=  cd.entitiesList();
     for(cinema i:liste){
         k.add(i);
     }
        
       id_cinema.setCellValueFactory(new PropertyValueFactory<cinema,Integer>("id_cinema"));
       
  nom.setCellValueFactory(new PropertyValueFactory<cinema,String>("nom"));
    description.setCellValueFactory(new PropertyValueFactory<cinema,String>("description"));
    localisation.setCellValueFactory(new PropertyValueFactory<cinema,String>("localisation"));
    id_user.setCellValueFactory(new PropertyValueFactory<cinema,Integer>("id_user"));


  tableview.setItems( k);
        
        
        }    

    @FXML
    private void redirection(ActionEvent event) {
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
        try {
            Parent root = loader.load();
            CinemaController dc= loader.getController();
                  idbtn      .getScene().setRoot(root);

        } catch (IOException ex) {
            System.err.println(ex.getMessage());        }
        
                
        
        
    }*/
       
       //Alert a =new Alert(Alert.AlertType., "ddd", ButtonType.OK);
       //a.showAndWait();
    }
    
}
