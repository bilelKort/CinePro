/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;

import cinepro.entities.Reclamation;
import cinepro.services.ReclamationCRUD;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class UpdateReclamationController implements Initializable {

    @FXML
    private TextField id_reclamation;
    @FXML
    private TextField id_user;
    @FXML
    private TextField id_film;
   
    @FXML
    private TextField description;
    @FXML
    private Button btnModifier;
    @FXML
    private Label erreur;
    @FXML
    private Label id_r;
    @FXML
    private Label desc;
    @FXML
    private Label user;
    @FXML
    private Label film;
    @FXML
    private CheckBox etat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ModifierReclamation(ActionEvent event) {
        erreur.setText("");
        if (description.getText().isEmpty()) {
            erreur.setText("Entrez description!");
        } else if (id_user.getText().isEmpty()) {
            erreur.setText("Entrez id_user!");
        } else if (id_film.getText().isEmpty()) {
            erreur.setText("Entrez id_film!");
        } else if (id_reclamation.getText().isEmpty()) {
            erreur.setText("Entrez id_reclamation!");
        

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("Êtes-vous sûr de  modifier cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {
                String desc= description.getText();
                String id_r = id_reclamation.getText();
                String user
                        = id_user.getText();
                String film
                        = id_film.getText();
               DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String daate = dtf.format(now);
                       
              boolean etaat
                        = etat.isSelected();
                
                Reclamation f = new Reclamation(Integer.parseInt(id_r), desc, Integer.parseInt(user), Integer.parseInt(film), daate,etaat);
                System.out.println(f);
                ReclamationCRUD rcd = new ReclamationCRUD();
               
                rcd.updateEntity(Integer.parseInt(id_r), desc, Integer.parseInt(user), Integer.parseInt(film), daate,etaat);
                System.out.println(f);
            }
        
    
    }
    
    }
}
