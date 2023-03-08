/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.services.AbonnementCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AfficheController implements Initializable {

    @FXML
    private TableView<Abonnement> tableview;
    @FXML
    private TableColumn<Abonnement, Integer> ida;
    @FXML
    private TableColumn<Abonnement, Integer> idu;
    public ObservableList<Abonnement> k = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Abonnement, Integer> type;
    @FXML
    private TableColumn<Abonnement, Timestamp> dateD;
    @FXML
    private TableColumn<Abonnement, Timestamp> dateE;
    private VBox vbox;
    @FXML
    private ComboBox<String> cbox;
    @FXML
    private Button fbtn;
    @FXML
    private AnchorPane stat;
    @FXML
    private Button statbtn;

    //private Button idbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

        AbonnementCRUD acd = new AbonnementCRUD();
        List<Abonnement> liste = new ArrayList<Abonnement>();
        liste = acd.entitiesList();
        for (Abonnement i : liste) {
            k.add(i);
        }

        ida.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        idu.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_user"));
        type.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("type"));
        dateD.setCellValueFactory(new PropertyValueFactory<Abonnement, Timestamp>("dateDebut"));
        dateE.setCellValueFactory(new PropertyValueFactory<Abonnement, Timestamp>("dateExpiration"));
        tableview.setItems(k);
        //
        cbox.getItems().addAll("1 mois", "3 mois", "6 mois", "1 an");
        cbox.setValue("1 mois");
        //vbox.getChildren().addAll(cbox);
        TableView<Abonnement> tableview = new TableView<>();
        //ObservableList<Abonnement> abs = FXCollections.observableArrayList(); // Initialisation de la liste des abonnements
        tableview.setItems(k);
        

        
        
    
    }
    @FXML
    public void filtrer(){
        FilteredList<Abonnement> absFiltres;
        absFiltres = k.filtered((Abonnement p) -> p.trans().equals(cbox.getValue()));
      
        tableview.setItems(absFiltres);
        cbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            absFiltres.setPredicate(p -> p.trans().equals(newValue));
        });
        
    }
    
  
    
    @FXML
    private void stat(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) statbtn.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

   

}
