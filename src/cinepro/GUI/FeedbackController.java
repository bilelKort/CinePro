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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        FeedbackCRUD c =new FeedbackCRUD();
       Traduction t = new Traduction();
        
       try {
            feedback.setText(t.traduire(feedback.getText()));
              } catch (IOException ex) {
                  System.out.println(ex.getMessage());
        
           
           
           
            erreur.setText("");
            if (feedback.getText().isEmpty()) {
            erreur.setText("Entrez feedback!");
            } else if (id_user.getText().isEmpty()) {
            erreur.setText("Entrez id_user!");
            } else if (id_film.getText().isEmpty()) {
            erreur.setText("Entrez id_film!");
            
            
            
            
            
            } 
           // else if(c.FeedbackCounter(Integer.valueOf(id_user.getText()))>=3){
           else if(c.FeedbackCounter()>=3){
               
           
              System.out.println("Vous n'avez pas respecté les reglements");
        
            Mail nvmail=new Mail();
            nvmail.envoyer();
            
           
            }
            else 
            {
            
            
            
            String resFeedback = feedback.getText();
            int resId_user = Integer.valueOf(id_user.getText());
            int resId_film = Integer.valueOf(id_film.getText());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            FeedbackCRUD pcd = new FeedbackCRUD();
            Feedback f = new Feedback(feedback.getText(),resId_user, resId_film, date);
            pcd.addCommentaire(f);
            System.out.println("Done!!");
            
            
            }
           
    }

}
}

