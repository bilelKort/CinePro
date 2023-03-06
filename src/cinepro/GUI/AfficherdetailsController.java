/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;

import cinepro.entities.Feedback;
import cinepro.entities.Traduction;
import cinepro.services.FeedbackCRUD;
import cinepro.services.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AfficherdetailsController implements Initializable {

    @FXML
    private TableView<Feedback> tableview;
    @FXML
    private TableColumn<Feedback, String> feedback;
    @FXML
    private TableColumn<Feedback, Integer> id_user;
    @FXML
    private TableColumn<Feedback, Integer> id_film;
    @FXML
    private TableColumn<Feedback, String> date;
    public ObservableList<Feedback> k = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Feedback, Integer> id_feedback;
    @FXML
    private TableColumn<Feedback, Void> trad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        FeedbackCRUD cd = new FeedbackCRUD();
        List<Feedback> liste = new ArrayList<Feedback>();
        liste = cd.commentaireList();

        System.out.println(liste);
        for (Feedback f : liste) {
            k.add(f);
        }

        id_user.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("id_user"));
        id_film.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("id_film"));
        feedback.setCellValueFactory(new PropertyValueFactory<Feedback, String>("feedback"));
        date.setCellValueFactory(new PropertyValueFactory<Feedback, String>("date"));
        id_feedback.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("id_feedback"));

        tableview.setItems(k);
      
    }

    public TableView<Feedback> getTableview() {
        return tableview;
    }

    public TableColumn<Feedback, String> getFeedback() {
        return feedback;
    }

    public TableColumn<Feedback, Integer> getId_user() {
        return id_user;
    }

    public TableColumn<Feedback, Integer> getId_film() {
        return id_film;
    }

    public TableColumn<Feedback, String> getDate() {
        return date;
    }

    public TableColumn<Feedback, Integer> getId_feedback() {
        return id_feedback;
    }

    public void setTableview(TableView<Feedback> tableview) {
        this.tableview = tableview;
    }

    public void setFeedback(TableColumn<Feedback, String> feedback) {
        this.feedback = feedback;
    }

    public void setId_user(TableColumn<Feedback, Integer> id_user) {
        this.id_user = id_user;
    }

    public void setId_film(TableColumn<Feedback, Integer> id_film) {
        this.id_film = id_film;
    }

    public void setDate(TableColumn<Feedback, String> date) {
        this.date = date;
    }

    public void setId_feedback(TableColumn<Feedback, Integer> id_feedback) {
        this.id_feedback = id_feedback;
    }

}
