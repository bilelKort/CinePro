/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.gui;

import cinepro.entities.Feedback;
import cinepro.entities.Reclamation;
import cinepro.services.FeedbackCRUD;
import cinepro.services.ReclamationCRUD;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import edu.cinepro.entities.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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

    @FXML
    private void logout(ActionEvent event) {
        UserSession.getInstace().cleanUserSession();
        //System.out.println(UserSession.getInstace().getId());

           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account");
            alert.setHeaderText(null);
            alert.setContentText("You have logged out successfully!");
            alert.showAndWait(); */
        Notifications notifications = Notifications.create();
        // notifications.graphic(new ImageView(notif));
        notifications.text("You have logged out successfully!");
        notifications.title("Success message");
        notifications.hideAfter(Duration.seconds(4));
        notifications.position(Pos.BOTTOM_LEFT);
        //notifications.darkStyle();
        notifications.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));

        try {
            Parent root = loader.load();
            Stage myWindow = (Stage) desc.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Sign In");
            myWindow.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void film(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/SearchMovies.fxml"));

        try {
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AfficherReclamation.fxml"));

        try {
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void cinema(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/CinemaAffiche.fxml"));

        try {
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void profile(ActionEvent actionEvent) {
        FXMLLoader loader= new FXMLLoader();
        if (UserSession.getInstace().getId()==0) {
            loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/SignIn.fxml"));
        }else {
            if (UserSession.getInstace().getRole().equals("Client")) {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/Index.fxml"));

            }else if (UserSession.getInstace().getRole().equals("Admin")) {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/AdminIndex.fxml"));
            }
        }

        try {
            Parent root = loader.load();
            desc.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }

    

    

