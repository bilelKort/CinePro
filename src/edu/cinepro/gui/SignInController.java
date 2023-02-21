/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.entities.UserSession;
import edu.cinepro.services.CineproCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        
        
        // System.out.println(UserSession.getInstace(id, user.getRole()).toString()); // nbadel user.getRole() b variable role = ccd.getRole(pseudo)
       
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        
        try {
            Parent root = loader.load();
            pseudo.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
