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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class SignUpController implements Initializable {

    @FXML
    private PasswordField ajouterPassword;
    @FXML
    private TextField ajouterNom;
    @FXML
    private Button signInBtn;
    @FXML
    private TextField ajouterPrenom;
    @FXML
    private TextField ajouterEmail;
    @FXML
    private DatePicker ajouterDateN;
    @FXML
    private TextField ajouterTel;
    @FXML
    private TextField ajouterPseudo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void addUser(ActionEvent event) {
        String resNom = ajouterNom.getText();
        String resPrenom = ajouterPrenom.getText();
        String resEmail = ajouterEmail.getText();
        String resDateN = ajouterDateN.getValue().toString();
        int resTel = Integer.valueOf(ajouterTel.getText());
        String resPseudo = ajouterPseudo.getText();
        String resPassword = ajouterPassword.getText();
        
        CineproCRUD ccd = new CineproCRUD();
        Cinepro c = new Cinepro(resEmail,resPassword,resNom,resPrenom,resDateN,resPseudo,resTel,"Client",0);
        
        
        ccd.addEntity(c);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("User ajoutée");
        alert.show();
        // System.out.println("Done!");
        
      /*  Cinepro user = ccd.login(resPseudo, resPassword);
        UserSession.getInstace(user.getId_user(), user.getRole()); */
        
       int id = ccd.getUserByPseudo(resPseudo);
       // System.out.println(id);
        
        UserSession.getInstace(id);
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        
        try {
            Parent root = loader.load();
            ajouterNom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        };
    }

    @FXML
    private void login(ActionEvent event) {
    }
    
}
