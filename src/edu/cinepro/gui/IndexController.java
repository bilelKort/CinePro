/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import cinerpo.entities.Type_Badge;
import cinerpo.services.BadgeCRUD;
import edu.cinepro.entities.User;
import edu.cinepro.entities.UserSession;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class IndexController implements Initializable {

    @FXML
    private ImageView badge;
    @FXML
    private TextField affichagePseudo;
    @FXML
    private TextField affichagePrenom;
    @FXML
    private TextField affichageNom;
    @FXML
    private TextField affichageEmail;
    @FXML
    private TextField affichageAge;
    @FXML
    private TextField affichageTel;
    @FXML
    private Button Logout;
    @FXML
    private Button modifier;
    @FXML
    private ImageView image3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(UserSession.getInstace().getId_badge());
        String urlBadge = "";
        switch (new BadgeCRUD().getBadge(UserSession.getInstace().getId())) {
            case "gold":
                urlBadge = "src/image/gold.png";
                break;
            case "silver":
                urlBadge = "src/image/silver.png";
                break;
            case "bronze":
                urlBadge = "src/image/bronze.png";
                break;
        }

        File filebadge = new File(urlBadge);
        String localURl = "";
        try {
            localURl = filebadge.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(localURl);
        badge.setImage(new Image(localURl));

        affichagePseudo.setText(UserSession.getInstace().getPseudo());
        affichageNom.setText(UserSession.getInstace().getNom());
        affichagePrenom.setText(UserSession.getInstace().getPrenom());
        affichageEmail.setText(UserSession.getInstace().getEmail());

        // Calcul d'age
        LocalDate today = LocalDate.now();

        String date = UserSession.getInstace().getDate_naissance();
        String[] x= date.split("-");
        int y = Integer.valueOf(x[0]);
        int m = Integer.valueOf(x[1]);
        int d = Integer.valueOf(x[2]);

        LocalDate birthday = LocalDate.of(y, m, d);
        int years = Period.between(birthday, today).getYears();

        affichageAge.setText(String.valueOf(years));
        affichageTel.setText(String.valueOf(UserSession.getInstace().getTel()));
      /*  int id = UserSession.getInstace().getId();
        CineproCRUD ccd = new CineproCRUD(); */
       /* Cinepro c = new Cinepro();
        c = ccd.getUserById(id); */
       // System.out.println("Bonjour "+UserSession.getInstace().getNom()+" !");
        
       /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bonjour "+UserSession.getInstace().getNom()+" !");
        alert.show(); */
        
       
        
      /*  UserSession.getInstace().cleanUserSession(); // Deconnecter
        System.out.println(UserSession.getInstace().getId()); */

        /*
        System.out.println("Bonjour "+UserSession.getInstace().getNom()+" !");
        */
        
        File file = new File("src/edu/cinepro/gui/images/image5.jpg");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image3.setImage(new Image(localURL));
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Update.fxml"));
        
        try {
            Parent root = loader.load();
            affichagePseudo.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
            Stage myWindow = (Stage) affichagePseudo.getScene().getWindow();
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
            affichagePseudo.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));

        try {
            Parent root = loader.load();
            affichagePseudo.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @FXML
    void afficherReservations(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/Display.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void sabonner(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader = new FXMLLoader(getClass().getResource("/cinepro/gui/Abonnement.fxml"));
            Parent root = loader.load();
            Logout.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
