/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.entities.TypeAbonnement;
import cinerpo.services.AbonnementCRUD;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Home
 */
enum Type {
    Un_mois, Trois_mois, Six_mois, Un_an
}

public class AbonnementController implements Initializable {

    @FXML
    private TextField idNom;
    @FXML
    private TextField idPrenom;
    @FXML
    private TextField mail;
    @FXML
    private Button valider;
    @FXML
    private VBox vBox;
    @FXML
    private ChoiceBox<Type> choiceBox;
    @FXML
    private Button cancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Create a new ChoiceBox
        // Add the enum values to the ChoiceBox
        choiceBox.getItems().addAll(Type.values());

        // Set a default value for the ChoiceBox
        choiceBox.setValue(Type.Un_mois);

        // Create a layout for the ChoiceBox
        vBox.getChildren().addAll(choiceBox);

        // TODO
    }

    @FXML
    private void saveAbonnement(ActionEvent event) {
        //SAUVEGARDE

        Type type = choiceBox.getValue();
        AbonnementCRUD acd = new AbonnementCRUD();
        TypeAbonnement ta = new TypeAbonnement();
        switch (type) {
            case Un_mois:
                ta.setNb_mois(1);
                ta.setPrix(50);
                break;
            case Trois_mois:
                ta.setNb_mois(3);
                ta.setPrix(150);
                break;
            case Six_mois:
                ta.setNb_mois(6);
                ta.setPrix(300);
                break;
            case Un_an:
                ta.setNb_mois(12);
                ta.setPrix(500);
                break;
        }
        Abonnement a = new Abonnement(1, ta);
        acd.addEntity(a);
    }

    public void setIdNom(String idNom) {
        this.idNom.setText(idNom);
    }

    public void setIdPrenom(String idPrenom) {
        this.idPrenom.setText(idPrenom);
    }

    public void setMail(String mail) {
        this.mail.setText(mail);
    }

    public TextField getIdNom() {
        return idNom;
    }

    public TextField getIdPrenom() {
        return idPrenom;
    }

    public TextField getMail() {
        return mail;
    }

}
