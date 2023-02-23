/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.services.AbonnementCRUD;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
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
        // TODO

    }

}
