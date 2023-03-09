/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Badge;
import cinerpo.entities.Type_Badge;
import cinerpo.services.BadgeCRUD;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


/**
 * FXML Controller class
 *
 * @author Home
 */

public class UpdateBadgeController implements Initializable {

    @FXML
    private TextField idUser;
    @FXML
    private VBox vbox;
    @FXML
    private ChoiceBox<Type1> cbox;
    @FXML
    private Button update;
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
        cbox.getItems().addAll(Type1.values());

        // Set a default value for the ChoiceBox
        cbox.setValue(Type1.Bronze);

        // Create a layout for the ChoiceBox
        vbox.getChildren().addAll(cbox);
        // TODO
        // TODO
    }
    public boolean isValidId(String id) {
    for (int i = 0; i < id.length(); i++) {
     if(!Character.isDigit(id.charAt(i))) {
        return false;
    }else{
        return true;
    }
    }
    return true;
    }

    @FXML
    private void updateBadge(ActionEvent event) {
        erreur.setText("");
        if (idUser.getText().isEmpty()) {
            erreur.setText("Entrez le User ID!");
        } else {
            String id = idUser.getText();
            Boolean valid=isValidId(id);
                if(!valid){
                    er.setText("Entrez un entier!");
                }
                else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("Êtes-vous sûr de vouloir modifier cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {

                
                
                Type1 type = cbox.getValue();
                Badge b = new Badge();
                switch (type) {
                    case Gold:
                        b.setType(Type_Badge.gold);
                        break;
                    case Silver:
                        b.setType(Type_Badge.silver);
                        break;
                    case Bronze:
                        b.setType(Type_Badge.bronze);
                        break;
                }

                BadgeCRUD bcd = new BadgeCRUD();
                bcd.updateEntity(b, Integer.parseInt(id));
                }

            }

        }
    }
}
