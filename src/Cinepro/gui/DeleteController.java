/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.services.AbonnementCRUD;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class DeleteController implements Initializable {

    @FXML
    private Button supp;
    @FXML
    private TextField idClient;
    @FXML
    private Label erreur;
    @FXML
    private Label er;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void Delete(ActionEvent event) {
        erreur.setText("");
        if (idClient.getText().isEmpty()) {
            erreur.setText("Entrez le client ID!");
            
        }String id = idClient.getText(); 
            Boolean valid=isValidId(id);
                if(!valid){
                    er.setText("Entrez un entier!");
                }
                else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppession");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {

                
                AbonnementCRUD acd = new AbonnementCRUD();
                acd.deleteEntity(Integer.parseInt(id));
                System.out.println("DELETE DONE!");
            }
        }
    }
}
