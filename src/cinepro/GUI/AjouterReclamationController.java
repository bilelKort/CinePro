/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;

import cinepro.entities.Feedback;
import cinepro.entities.Reclamation;
import cinepro.services.FeedbackCRUD;
import cinepro.services.ReclamationCRUD;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AjouterReclamationController implements Initializable {

   
    @FXML
    private Label idfilm;
    @FXML
    private Label desc;
    @FXML
    private Button btnajouter;
    @FXML
    private TextField id_film;
    @FXML
    private TextField description;
    @FXML
    private Label erreur;
    @FXML
    private Label user;
    @FXML
    private TextField id_user;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void ajouterReclamation(ActionEvent event) {
        erreur.setText("");
        if (description.getText().isEmpty()) {
            erreur.setText("Entrez reclamation!");
        } else if (id_user.getText().isEmpty()) {
            erreur.setText("Entrez id_user!");
        } else if (id_film.getText().isEmpty()) {
            erreur.setText("Entrez id_film!");
        } else {

            

                String resReclamation = description.getText();
                int resId_user = Integer.valueOf(id_user.getText());
                int resId_film = Integer.valueOf(id_film.getText());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);
                ReclamationCRUD rcd = new ReclamationCRUD();
                Reclamation f = new Reclamation(resReclamation, resId_user, resId_film, date,false);
                rcd.addEntity(f);
                
                System.out.println("Done!!");

               
        }

    }
    }

    

    

