/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.code;
import edu.cinepro.services.CineproCRUD;
import edu.cinepro.services.Mail;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
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
public class SignInController implements Initializable {

    @FXML
    private PasswordField password;
    @FXML
    private TextField pseudo;
    @FXML
    private Button signInBtn;
    @FXML
    private Hyperlink suLink;
    @FXML
    private ImageView image1;
    @FXML
    private Hyperlink mdpoublie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        File file = new File("src/edu/cinepro/gui/images/image1.jpg");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image1.setImage(new Image(localURL));
        
      /*  Image image = new Image("..\\images\\image1.png");
        image1.setImage(image); */
      
      
      
        
        // TODO
    }    

    @FXML
    private void signUp(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));
        
        try {
            Parent root = loader.load();
           password.getScene().setRoot(root);
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void login(ActionEvent event) {
  
        
        String resPseudo = pseudo.getText();
        String resPassword = password.getText();
        
        CineproCRUD ccd = new CineproCRUD();
        
        Cinepro user = ccd.login(resPseudo, resPassword);
        
        int id = ccd.getUserByPseudo(resPseudo);
       // System.out.println(id);
        
        UserSession.getInstace(id); 
        
        switch (UserSession.getInstace().getRole()) {
        
            case "Client": {
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
                
                try {
                Parent root = loader.load();
                Stage myWindow = (Stage) pseudo.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("Espace Client");
                myWindow.show();
                
            
                } catch (IOException ex) {
                 System.out.println(ex.getMessage());
                 }
                //Notification
                Notifications notifications = Notifications.create();
        // notifications.graphic(new ImageView(notif));
        notifications.text("You are now logged in!");
        notifications.title("Success message");
        notifications.hideAfter(Duration.seconds(4));
        notifications.position(Pos.BOTTOM_LEFT);
        //notifications.darkStyle();
        notifications.show();
        
                break;
                
            }
            case "Admin": {
            
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminIndex.fxml"));
                
                try {
                Parent root = loader.load();
                Stage myWindow = (Stage) pseudo.getScene().getWindow();
                Scene sc = new Scene(root);
                myWindow.setScene(sc);
                myWindow.setTitle("Espace Admin");
                myWindow.show();
                
            
                } catch (IOException ex) {
                 System.out.println(ex.getMessage());
                 }
                //Notification
                Notifications notifications = Notifications.create();
                // notifications.graphic(new ImageView(notif));
                notifications.text("You are now logged in!");
                notifications.title("Success message");
                notifications.hideAfter(Duration.seconds(4));
                notifications.position(Pos.BOTTOM_LEFT);
                //notifications.darkStyle();
                notifications.show();
        
                
                break;
            }
            
            
        }
        
               
        
        
        
        
    }

    @FXML
    private void mdpOublie(ActionEvent event) {
        String resPseudo = pseudo.getText();
        
        CineproCRUD ccd = new CineproCRUD();
        int id = ccd.getUserByPseudo(resPseudo);
        UserSession.getInstace(id); 
        
        
        String email = ccd.getEmailByPseudo(resPseudo);
        
        Mail mail = new Mail();
        
        Random random = new Random();
        int num = random.nextInt(100000);
        String formatted = String.format("%05d", num);
        System.out.println(formatted);
        
        mail.envoyer(formatted,email);
        
        code.getInstace(formatted);
        //code.getInstace().setCodeConfirmation(formatted);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MdpOublie.fxml"));
                
        try {
            Parent root = loader.load();
            Stage myWindow = (Stage) password.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Modifier mdp");
            myWindow.show();
                
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
        
    }
    
    
}
