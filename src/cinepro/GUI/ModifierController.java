/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;

import cinepro.entities.Feedback;
import cinepro.services.FeedbackCRUD;
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
public class ModifierController implements Initializable {

    @FXML
    private TextField id_user;
    @FXML
    private TextField feedback;
    @FXML
    private Button btnmodifier;
    @FXML
    private Label user;

    @FXML
    private Label Feedback;
    private Label Date;
    @FXML
    private Label id_feedback;
    @FXML
    private TextField id_f;
    @FXML
    private TextField idf;
    @FXML
    private TextField id_date;
    @FXML
    private Label erreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void modifier(ActionEvent event) {
        erreur.setText("");
        if (feedback.getText().isEmpty()) {
            erreur.setText("Entrez feedback!");
        } else if (id_user.getText().isEmpty()) {
            erreur.setText("Entrez id_user!");
        } else if (idf.getText().isEmpty()) {
            erreur.setText("Entrez id_film!");
        } else if (id_f.getText().isEmpty()) {
            erreur.setText("Entrez id_feedback!");
        } else if (id_date.getText().isEmpty()) {
            erreur.setText("Entrez date!");

        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de modification");
            alert.setHeaderText("Êtes-vous sûr de  modifier cet élément ?");

            // Configurez les boutons et attendez la réponse de l'utilisateur
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            // Si l'utilisateur a cliqué sur "Oui", effectuez l'opération de modification
            if (result.get() == ButtonType.YES) {
                String Feedback = feedback.getText();
                String id_feedback = id_f.getText();
                String user
                        = id_user.getText();
                String id_film
                        = idf.getText();
                String date
                        = id_date.getText();
                Feedback f = new Feedback(Integer.parseInt(id_feedback), Feedback, Integer.parseInt(user), Integer.parseInt(id_film), date);
                FeedbackCRUD pcd = new FeedbackCRUD();
                pcd.updateCommentaire(Integer.parseInt(id_feedback), Feedback, Integer.parseInt(user), Integer.parseInt(id_film), date);
                System.out.println(f);
            }
        }
    }
}
