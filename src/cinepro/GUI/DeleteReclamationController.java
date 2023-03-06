/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;

import cinepro.services.FeedbackCRUD;
import cinepro.services.ReclamationCRUD;
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
 * @author acer
 */
public class DeleteReclamationController implements Initializable {

    @FXML
    private Label id_rec;
    @FXML
    private TextField id_reclamation;
    @FXML
    private Button btnsupprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void supprimerReclamation(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation de suppression");
    alert.setHeaderText("Êtes-vous sûr de supprimer cet élément ?");

    // Configurez les boutons et attendez la réponse de l'utilisateur
    alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
    Optional<ButtonType> result = alert.showAndWait();
    // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
    if (result.get() == ButtonType.YES) {
         String description =id_reclamation.getText();
        ReclamationCRUD rcd = new ReclamationCRUD();
        rcd.deleteEntity(Integer.parseInt(description));
        System.out.println("reclamation deleted!!");
       

    }

    
    
}
        
    }
    

