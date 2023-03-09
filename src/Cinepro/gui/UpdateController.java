/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.entities.TypeAbonnement;
import cinerpo.services.AbonnementCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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

public class UpdateController implements Initializable {

    @FXML
    private Button vbtn;
    @FXML
    private TextField idab;
    @FXML
    private VBox vbox;
    @FXML
    private ChoiceBox<Type> cbox;
    @FXML
    private Label erreur;
    @FXML
    private Label er;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add the enum values to the ChoiceBox
        cbox.getItems().addAll(Type.values());

        // Set a default value for the ChoiceBox
        cbox.setValue(Type.Un_mois);

        // Create a layout for the ChoiceBox
        vbox.getChildren().addAll(cbox);
        // TODO
    }
    public boolean isValidId(String id) {
    for (int i = 0; i < id.length(); i++) {
    if (!Character.isDigit(id.charAt(i))) {
        return false;
    }else{
        return true;
    }
    }
    return true;
    }
    
    @FXML
    private void update(ActionEvent event) {
        erreur.setText("");
        if (idab.getText().isEmpty()) {
            erreur.setText("Entrez le User ID!");
        } else {
            String id = idab.getText();
            Boolean valid=isValidId(id);
                if(!valid){
                    er.setText("Entrez un entier!");
                }
                else{
            // Créez une boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("Êtes-vous sûr de vouloir modifier cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {

                
                TypeAbonnement ta = new TypeAbonnement();
                Type type = cbox.getValue();
                switch (type) {
                    case Un_mois:
                        ta.setNb_mois(1);
                        break;
                    case Trois_mois:
                        ta.setNb_mois(3);
                        break;
                    case Six_mois:
                        ta.setNb_mois(6);
                        break;
                    case Un_an:
                        ta.setNb_mois(12);
                        break;
                }

                Abonnement a = new Abonnement(ta);
                AbonnementCRUD acd = new AbonnementCRUD();
                acd.updateEntity(a, Integer.parseInt(id));   
            }
        }

        }}}


