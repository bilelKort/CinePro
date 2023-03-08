/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.entities.Badge;
import cinerpo.services.BadgeCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private TableColumn<Badge, Integer> nb;
    @FXML
    private TableColumn<Badge, Integer> idu;
    public ObservableList<Badge> k = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cbox;
    @FXML
    private Button fbtn;
    private Button stat;
    @FXML
    private Button bc;
    @FXML
    private Button cancel;

    //private Button idbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BadgeCRUD bcd = new BadgeCRUD();
        List<Badge> liste = new ArrayList<Badge>();
        liste = bcd.entitiesList();
        for (Badge i : liste) {
            k.add(i);
        }

        idb.setCellValueFactory(new PropertyValueFactory<Badge, Integer>("id_badge"));
        type.setCellValueFactory(new PropertyValueFactory<Badge, String>("type"));
        nb.setCellValueFactory(new PropertyValueFactory<Badge, Integer>("nbr_reservation"));
        tableview.setItems(k);
        cbox.getItems().addAll("gold", "silver", "bronze");
        cbox.setValue("Bronze");
        //vbox.getChildren().addAll(cbox);
        TableView<Badge> tableview = new TableView<>();
        //ObservableList<Abonnement> abs = FXCollections.observableArrayList(); // Initialisation de la liste des abonnements
        tableview.setItems(k);
        // TODO
        cancel.setOnAction(e -> {
    Stage stage = (Stage) cancel.getScene().getWindow();
    stage.close();
});
    }
      @FXML
    public void filtrer(){
        FilteredList<Badge> bFiltres;
        bFiltres = k.filtered((Badge p) -> p.trans().equals(cbox.getValue()));
      
        tableview.setItems(bFiltres);
        cbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            bFiltres.setPredicate(p -> p.trans().equals(newValue));
        });
        
        
    }
    
  
    
   

        

    @FXML
    private void stat(ActionEvent event) {
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("statb.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) bc.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
}
