/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.entities.UserSession;
import edu.cinepro.services.CineproCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class IndexController implements Initializable {

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
    private void logout(ActionEvent event) {
        UserSession.getInstace().cleanUserSession();
        //System.out.println(UserSession.getInstace().getId());
       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account");
            alert.setHeaderText(null);
            alert.setContentText("You have logged out successfully!");
            alert.showAndWait();
        
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
    private void modifier(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Update.fxml"));
        
        try {
            Parent root = loader.load();
            affichagePseudo.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
