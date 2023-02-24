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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    private TableColumn<cinema, String> localisation;
    @FXML
    private TableColumn<cinema, String> description;
    @FXML
    private TableColumn<cinema, String> photo;
    @FXML
    private TableColumn<cinema, Integer> id_user;
    public ObservableList<cinema> k = FXCollections.observableArrayList();
    @FXML
    private Button idbtn;
    @FXML
    private TableColumn edit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CinemaCRUD cd = new CinemaCRUD();
        List<cinema> liste = new ArrayList<cinema>();
        liste = cd.entitiesList();
        for (cinema i : liste) {
            k.add(i);
        }

        id_cinema.setCellValueFactory(new PropertyValueFactory<cinema, Integer>("id_cinema"));

        nom.setCellValueFactory(new PropertyValueFactory<cinema, String>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<cinema, String>("description"));
        localisation.setCellValueFactory(new PropertyValueFactory<cinema, String>("localisation"));
        id_user.setCellValueFactory(new PropertyValueFactory<cinema, Integer>("id_user"));

        tableview.setItems(k);
        tableview.getSelectionModel().select(2);

     

        Callback<TableColumn<cinema, Void>, TableCell<cinema, Void>> cellFactory
                = new Callback<TableColumn<cinema, Void>, TableCell<cinema, Void>>() {
            @Override
            public TableCell<cinema, Void> call(final TableColumn<cinema, Void> param) {
                final TableCell<cinema, Void> cell = new TableCell<cinema, Void>() {
                    private final Button btn = new Button("see more ");
                    

                    {
                       btn .setStyle("-fx-color: white;");

                        btn.setOnAction((ActionEvent event) -> {
                            /* 
    TableCell<cinema, String> cell = (TableCell<cinema, String>) event.getTarget();
    int rowIndex = cell.getIndex();
    System.out.println("Ligne cliquée : " + rowIndex);
                             */

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewmorecinema.fxml"));

                            try {
                                Parent root = loader.load();

                                ViewmorecinemaController dc = loader.getController();

                                int rowIndex = getTableRow().getIndex();
                                Integer idCinemaValue = id_cinema.getCellObservableValue(rowIndex).getValue();
                                System.out.println("ID Cinéma : " + idCinemaValue);
                  //  ViewmorecinemaController.getInstance().setId(Integer.toString(idCinemaValue));
                                String id = Integer.toString(idCinemaValue);
                               dc.setId(id);
                                cinema liste = cd.cinemabyid(idCinemaValue);
                             dc.setMap(liste.getLocalisation());

                                dc.setNom(liste.getNom());
                                dc.setImage(liste.getPhoto());
                                System.out.println(liste);
                                btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.err.println(ex.getMessage());
                            }

// Code pour gérer l'action du bouton
                            //redirection
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        edit.setCellFactory(cellFactory);

// Ajoute la colonne à la fin du TableView
    }

    @FXML

    private void redirection(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Cinema.fxml"));
        try {
            Parent root = loader.load();
            CinemaController dc= loader.getController();
                  idbtn.getScene().setRoot(root);
                  
                  

        } catch (IOException ex) {
            System.err.println(ex.getMessage());        }
        
                
        
        
    }

        //Alert a =new Alert(Alert.AlertType., "ddd", ButtonType.OK);
        //a.showAndWait();
    

    @FXML
    private void delete(ActionEvent event) throws SQLException {
           SelectionModel<cinema> selectionModel = tableview.getSelectionModel();
int selectedIndex = tableview.getSelectionModel().getSelectedIndex();

        System.out.println(selectedIndex);
        Integer idCinemaValue = id_cinema.getCellObservableValue(selectedIndex).getValue();
        
        if(idCinemaValue>0){
                CinemaCRUD cd = new CinemaCRUD();
                            System.out.println(idCinemaValue); 
                            
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("supprimer");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous supprimer ?       |o_O|");

        if (alert.showAndWait().get() == ButtonType.OK) {
                            
                            
        cd.deleteEntity(idCinemaValue);
        
       
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
        } 
        else {
        
        
         try {
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
        
        
        }
        
        
        }
        
        

        
        
    }

    @FXML
    private void metier(ActionEvent event) {
        
            try {

                System.out.println("ok !");
                Parent root = FXMLLoader.load(getClass().getResource("place.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
    

}
