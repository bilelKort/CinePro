/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import com.google.common.hash.Hashing;
import edu.cinepro.entities.Cinepro;
import edu.cinepro.entities.UserSession;
import edu.cinepro.services.CineproCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
    @FXML
    private Label passwordStrength;
    @FXML
    private ImageView image2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ajouterPassword.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!"".equals(newValue)) {
                updatePasswordStrength(newValue);
            } else {
                passwordStrength.setText("");
            }
        });
        
        File file = new File("src/edu/cinepro/gui/images/image2.jpg");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image2.setImage(new Image(localURL));
        // TODO
    }
    
    //COntrole saisie
    private boolean validateForm() {
        
        if (ajouterPassword.getText().equals("") || ajouterNom.getText().equals("") || ajouterPrenom.getText().equals("")
                || ajouterEmail.getText().equals("") || ajouterDateN.getValue() == null || Integer.valueOf(ajouterTel.getText()) == null
                || ajouterPseudo.getText().equals("") ) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Form");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    
    public boolean validateNom(String nom) {
        String pattern = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$";
        return nom.matches(pattern);
    }
    
    public boolean validatePrenom(String prenom) {
        String pattern = "^[A-Z][a-z]+(?: [A-Z][a-z]+)*$";
        return prenom.matches(pattern);
    }
    
    
    private boolean validateEmail(String email) {
        String pattern = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b";
        return email.matches(pattern);
    }
    
    private boolean validateTel(String num) {
        String pattern = "^[0-9]{8}$";
        return num.matches(pattern);
    } 
    
    
    private boolean validatePseudo(String pseudo) {
        String pattern = "[a-z]*$";
        return pseudo.matches(pattern);
    }  
    
    private boolean validatePassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
        
    }
    
    private int calculatePasswordStrength(String password) {

        //total score of password
        int iPasswordScore = 0;

        if (password.length() < 8) {
            return 0;
        } else if (password.length() >= 10) {
            iPasswordScore += 2;
        } else {
            iPasswordScore += 1;
        }

        //if it contains one digit, add 2 to total score
        if (password.matches("(?=.*[0-9]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one lower case letter, add 2 to total score
        if (password.matches("(?=.*[a-z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one upper case letter, add 2 to total score
        if (password.matches("(?=.*[A-Z]).*")) {
            iPasswordScore += 2;
        }

        //if it contains one special character, add 2 to total score
        if (password.matches("(?=.*[~!@#$%^&*()_-]).*")) {
            iPasswordScore += 2;
        } 

        return iPasswordScore;

    }
    private void updatePasswordStrength(String value) {
        if (calculatePasswordStrength(value) < 5) {
            passwordStrength.setText("( weak )");
            passwordStrength.setTextFill(Color.web("#ff0505"));
        } else if (calculatePasswordStrength(value) == 5) {
            passwordStrength.setText("( average )");
            passwordStrength.setTextFill(Color.web("#ed701b"));
        } else if (calculatePasswordStrength(value) >= 8) {
            passwordStrength.setText("( strong )");
            passwordStrength.setTextFill(Color.web("#6fb52c"));
        }
    }
    
    
    
    
    private boolean isFormValid() {
        
        Alert alert = new Alert(Alert.AlertType.WARNING);
        boolean flag = true;
        if (!validateForm()) {
            flag = false;
        } else {
            if (!validateNom(ajouterNom.getText())) {
                
                alert.setTitle("Validate Nom");
                alert.setHeaderText(null);
                alert.setContentText("Please check last name! (Must starts with UPPERCASE) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validatePrenom(ajouterPrenom.getText())) {
                alert.setTitle("Validate Prenom");
                alert.setHeaderText(null);
                alert.setContentText("Please check first name! (Must starts with UPPERCASE) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validateEmail(ajouterEmail.getText())) {
                alert.setTitle("Validate EMail");
                alert.setHeaderText(null);
                alert.setContentText("Please check the mail! (Format: ***@***.**) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validateTel(ajouterTel.getText())) {
                alert.setTitle("Validate Phone number");
                alert.setHeaderText(null);
                alert.setContentText("Please check the phone number! (Must be 8 digit number!) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validatePseudo(ajouterPseudo.getText())) {
                alert.setTitle("Validate Pseudo");
                alert.setHeaderText(null);
                alert.setContentText("Please check the Pseudo! (Must be LOWERCASE with no spaces!) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validatePassword(ajouterPassword.getText())) {
                alert.setTitle("Validate password");
                alert.setHeaderText(null);
                alert.setContentText("Please check the Password! Password must contain at least one(Digit, Lowercase, UpperCase and Special Character)");
                alert.showAndWait();
                flag = false;
            }
            
        }
     
        return flag;
    }
    
    
    
    


    @FXML
    private void addUser(ActionEvent event) {
        //Nom
        if (ajouterNom.getText().length()==0){
        
            ajouterNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterNom.setStyle(null);
        }
        
        //Prenom
        if (ajouterPrenom.getText().length()==0){
        
            ajouterPrenom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterPrenom.setStyle(null);
        }
        
        //Email
        if (ajouterEmail.getText().length()==0){
        
            ajouterEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterEmail.setStyle(null);
        }
        
        //DateN
       /* if (ajouterDateN.getValue().l==0){
        
            ajouterNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterNom.setStyle(null);
        } */
       
       //Pseudo
       if (ajouterPseudo.getText().length()==0){
        
            ajouterPseudo.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterPseudo.setStyle(null);
        }
       
       //Password
       if (ajouterPassword.getText().length()==0){
        
            ajouterPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterPassword.setStyle(null);
        }
        /*
       /if (ajouterTel.getText().length()==0){
        
            ajouterNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterNom.setStyle(null);
        } */
        
      if(isFormValid()) {
        
        String resNom = ajouterNom.getText();
        String resPrenom = ajouterPrenom.getText();
        String resEmail = ajouterEmail.getText();
        String resDateN = ajouterDateN.getValue().toString();
        int resTel = Integer.valueOf(ajouterTel.getText());
        String resPseudo = ajouterPseudo.getText();
        String resPassword = ajouterPassword.getText();

        String passwordCry = Hashing.sha256().hashString(resPassword, StandardCharsets.UTF_8).toString();
        //Ajout
        CineproCRUD ccd = new CineproCRUD();
        Cinepro c = new Cinepro(resEmail,passwordCry,resNom,resPrenom,resDateN,resPseudo,resTel,"Client",0);
        
        
        ccd.addEntity(c);
       /* Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("User ajoutée");
        alert.show();
         System.out.println("Done!"); */
        
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
      
      
      
    }

    @FXML
    private void login(ActionEvent event) {
    }
    
}