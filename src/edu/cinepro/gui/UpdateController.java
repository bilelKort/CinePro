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
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class UpdateController implements Initializable {
    @FXML
    private Label passwordStrength;
    @FXML
    private TextField modifierNom;
    @FXML
    private TextField modifierPrenom;
    @FXML
    private TextField modifierEmail;
    @FXML
    private DatePicker modifierDateN;
    @FXML
    private TextField modifierTel;
    @FXML
    private TextField modifierPseudo;
    @FXML
    private PasswordField modifierPassword;
    @FXML
    private Button updateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        modifierPassword.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!"".equals(newValue)) {
                updatePasswordStrength(newValue);
            } else {
                passwordStrength.setText("");
            }
        });
        
        
        // TODO
    }    
    
     private boolean validateForm() {
        
        if (modifierPassword.getText().equals("") || modifierNom.getText().equals("") || modifierPrenom.getText().equals("")
                || modifierEmail.getText().equals("") || modifierDateN.getValue() == null || Integer.valueOf(modifierTel.getText()) == null
                || modifierPseudo.getText().equals("") ) {
            
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
            if (!validateNom(modifierNom.getText())) {
                
                alert.setTitle("Validate Nom");
                alert.setHeaderText(null);
                alert.setContentText("Please check last name! (Must starts with UPPERCASE) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validatePrenom(modifierPrenom.getText())) {
                alert.setTitle("Validate Prenom");
                alert.setHeaderText(null);
                alert.setContentText("Please check first name! (Must starts with UPPERCASE) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validateEmail(modifierEmail.getText())) {
                alert.setTitle("Validate EMail");
                alert.setHeaderText(null);
                alert.setContentText("Please check the mail! (Format: ***@***.**) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validateTel(modifierTel.getText())) {
                alert.setTitle("Validate Phone number");
                alert.setHeaderText(null);
                alert.setContentText("Please check the phone number! (Must be 8 digit number!) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validatePseudo(modifierPseudo.getText())) {
                alert.setTitle("Validate Pseudo");
                alert.setHeaderText(null);
                alert.setContentText("Please check the Pseudo! (Must be LOWERCASE with no spaces!) ");
                alert.showAndWait();
                flag = false;
            }
            if (!validatePassword(modifierPassword.getText())) {
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
    private void updateUser(ActionEvent event) {
        if (modifierNom.getText().length()==0){
        
            modifierNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            modifierNom.setStyle(null);
        }
        
        //Prenom
        if (modifierPrenom.getText().length()==0){
        
            modifierPrenom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            modifierPrenom.setStyle(null);
        }
        
        //Email
        if (modifierEmail.getText().length()==0){
        
            modifierEmail.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            modifierEmail.setStyle(null);
        }
        
        //DateN
       /* if (ajouterDateN.getValue().l==0){
        
            ajouterNom.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            ajouterNom.setStyle(null);
        } */
       
       //Pseudo
       if (modifierPseudo.getText().length()==0){
        
            modifierPseudo.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            modifierPseudo.setStyle(null);
        }
       
       //Password
       if (modifierPassword.getText().length()==0){
        
            modifierPassword.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            modifierPassword.setStyle(null);
        }
       
       
       if(isFormValid()) {
        
        String resNom = modifierNom.getText();
        String resPrenom = modifierPrenom.getText();
        String resEmail = modifierEmail.getText();
        String resDateN = modifierDateN.getValue().toString();
        int resTel = Integer.valueOf(modifierTel.getText());
        String resPseudo = modifierPseudo.getText();
        String resPassword = modifierPassword.getText();

        //Ajout
        CineproCRUD ccd = new CineproCRUD();
        int id =UserSession.getInstace().getId();
        String role = UserSession.getInstace().getRole();
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Modify " + id + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
        
            ccd.updateEntity(id,resEmail,resPassword,resNom,resPrenom,resDateN,resPassword,resTel,role,0);
        }
      
        
        UserSession.getInstace(id);
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        
        try {
            
            Parent root = loader.load();
            modifierNom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        };
      }
    }
    
    
    
}
