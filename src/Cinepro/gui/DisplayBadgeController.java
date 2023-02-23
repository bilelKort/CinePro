/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Badge;
import cinerpo.services.BadgeCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class DisplayBadgeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Badge> tableview;
    @FXML
    private TableColumn<Badge, Integer> idb;
    @FXML
    private TableColumn<Badge, String> type;
    @FXML
    private TableColumn<Badge,Integer> nb;
    @FXML
    private TableColumn<Badge,Integer> idu;
    public ObservableList<Badge> k=FXCollections.observableArrayList();
    

    
   
    //private Button idbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
      BadgeCRUD bcd= new BadgeCRUD();
      List<Badge> liste=new ArrayList<Badge>();
      liste=bcd.entitiesList();
      for(Badge i:liste){
         k.add(i);
     }
        
       idb.setCellValueFactory(new PropertyValueFactory<Badge,Integer>("id_badge"));
       type.setCellValueFactory(new PropertyValueFactory<Badge,String>("type"));
       nb.setCellValueFactory(new PropertyValueFactory<Badge,Integer>("nbr_reservation"));
       idu.setCellValueFactory(new PropertyValueFactory<Badge,Integer>("id_user"));
       tableview.setItems(k);
        // TODO
    } 
    
}
