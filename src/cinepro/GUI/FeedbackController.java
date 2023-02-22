/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;

import cinepro.entities.Feedback;
import cinepro.services.FeedbackCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FeedbackController implements Initializable {

    @FXML
    private TextField feedback;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField id_user;
    @FXML
    private TextField id_film;
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
    private void ajouterfeedback(ActionEvent event) {
        erreur.setText("");
        if (feedback.getText().isEmpty()) {
            erreur.setText("entrez feedback!");
        } else {
            

            try {

                String resFeedback = feedback.getText();

                int resId_user = Integer.valueOf(id_user.getText());
                int resId_film = Integer.valueOf(id_film.getText());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);
                FeedbackCRUD pcd = new FeedbackCRUD();
                Feedback f = new Feedback(resFeedback, resId_user, resId_film, ("02/02/2022-14:00"));
                pcd.addCommentaire(f);
                System.out.println("Done!!");

                //redirection
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
                Parent root = loader.load();
                DetailsController dc = loader.getController();
                dc.setFeedback(resFeedback);
                dc.setDate(date);

                feedback.getScene().setRoot(root);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }
}
