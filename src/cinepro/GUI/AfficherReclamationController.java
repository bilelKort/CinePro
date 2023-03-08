/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.gui;

import cinepro.entities.Feedback;
import cinepro.entities.Reclamation;
import cinepro.services.FeedbackCRUD;
import cinepro.services.ReclamationCRUD;
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
 * @author acer
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation,String> description;
    @FXML
    private TableColumn<Reclamation,Integer> id_reclamation;
    @FXML
    private TableColumn<Reclamation,Integer> id_user;
    @FXML
    private TableColumn<Reclamation,Integer> id_film;
    @FXML
    private TableColumn<Reclamation,String> date;
    public ObservableList<Reclamation> k = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reclamation,String> etat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          ReclamationCRUD cd = new ReclamationCRUD();
        List<Reclamation> liste =new ArrayList<Reclamation>();
        liste=cd.EntityList();
        
        System.out.println(liste);
        for (Reclamation f : liste) {
            k.add(f);
        }
        
        id_user.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_user"));
        id_film.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id_film"));
        description.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("date"));
        id_reclamation.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id_reclamation"));
        etat.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("etat"));
        
        tableview.setItems(k);
    }    

    public TableColumn<Reclamation, String> getEtat() {
        return etat;
    }

    public void setEtat(TableColumn<Reclamation, String> etat) {
        this.etat = etat;
    }

    public TableView<Reclamation> getTableview() {
        return tableview;
    }

    public TableColumn<Reclamation, String> getDescription() {
        return description;
    }

    public TableColumn<Reclamation, Integer> getId_reclamation() {
        return id_reclamation;
    }

    public TableColumn<Reclamation, Integer> getId_user() {
        return id_user;
    }

    public TableColumn<Reclamation, Integer> getId_film() {
        return id_film;
    }

    public TableColumn<Reclamation, String> getDate() {
        return date;
    }

    public ObservableList<Reclamation> getK() {
        return k;
    }

    public void setTableview(TableView<Reclamation> tableview) {
        this.tableview = tableview;
    }

    public void setDescription(TableColumn<Reclamation, String> description) {
        this.description = description;
    }

    public void setId_reclamation(TableColumn<Reclamation, Integer> id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setId_user(TableColumn<Reclamation, Integer> id_user) {
        this.id_user = id_user;
    }

    public void setId_film(TableColumn<Reclamation, Integer> id_film) {
        this.id_film = id_film;
    }

    public void setDate(TableColumn<Reclamation, String> date) {
        this.date = date;
    }

    public void setK(ObservableList<Reclamation> k) {
        this.k = k;
    }
    
    
}
