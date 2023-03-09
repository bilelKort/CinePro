/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import com.google.common.hash.Hashing;
import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.code;
import edu.cinepro.services.CineproCRUD;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class MdpOublieController implements Initializable {

    @FXML
    private TextField codeC;
    @FXML
    private PasswordField newPass;
    @FXML
    private PasswordField confirmerNewPass;
    @FXML
    private Button signInM;
    @FXML
    private Label passwordStrength;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        newPass.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!"".equals(newValue)) {
                updatePasswordStrength(newValue);
            } else {
                passwordStrength.setText("");
            }
        });
        
       // String codeConf = code.getInstace().getCodeConfirmation();
       // System.out.println(codeConf);
    }    
    
    private boolean validateForm() {
        
        if (codeC.getText().equals("") || newPass.getText().equals("") || confirmerNewPass.getText().equals("") ) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Form");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    private boolean validateCode(String Code){
    
        String codeConf = code.getInstace().getCodeConfirmation();
        //String resCode = codeC.getText();
        return Code.equals(codeConf);
    }
    
    private boolean validatePassword(String password) {
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^*&+=])(?=\\S+$).{8,}";
        return password.matches(pattern);
        
    }
    private boolean validatePassword2(String password) {
        
        return confirmerNewPass.getText().equals(password);
        
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
            
            if (!validateCode(codeC.getText())) {
                alert.setTitle("Validate code");
                alert.setHeaderText(null);
                alert.setContentText("Please check again the code! ");
                alert.showAndWait();
                flag = false;
            }
            
            if (!validatePassword(newPass.getText())) {
                alert.setTitle("Validate password");
                alert.setHeaderText(null);
                alert.setContentText("Please check the Password! Password must contain at least one(Digit, Lowercase, UpperCase and Special Character)");
                alert.showAndWait();
                flag = false;
            }
            
            if (!validatePassword2(newPass.getText())) {
                alert.setTitle("Validate password confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Please enter the same Password");
                alert.showAndWait();
                flag = false;
            }
            
        }
     
        return flag;
    }

    @FXML
    private void signInM(ActionEvent event) {
        if (codeC.getText().length()==0){
        
            codeC.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            codeC.setStyle(null);
        }
        
        if (newPass.getText().length()==0){
        
            newPass.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            newPass.setStyle(null);
        }
        
        if (confirmerNewPass.getText().length()==0){
        
            confirmerNewPass.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
        }
        else{
        
            confirmerNewPass.setStyle(null);
        }
        
        
        if (isFormValid()){
        
            String resPass = newPass.getText();
            String passwordCry = Hashing.sha256().hashString(resPass, StandardCharsets.UTF_8).toString();
            
           String pseudo = UserSession.getInstace().getPseudo();
           CineproCRUD ccd = new CineproCRUD();
           
           ccd.changePass(pseudo, passwordCry);
           
           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account");
            alert.setHeaderText(null);
            alert.setContentText("You have changed your password successfully!");
            alert.showAndWait(); */
           Notifications notifications = Notifications.create();
        // notifications.graphic(new ImageView(notif));
        notifications.text("You have changed your password successfully!");
        notifications.title("Success message");
        notifications.hideAfter(Duration.seconds(4));
        notifications.position(Pos.BOTTOM_LEFT);
        //notifications.darkStyle();
        notifications.show();
           
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        
        try {
            Parent root = loader.load();
            Stage myWindow = (Stage) codeC.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Sign In");
            myWindow.show();
            
            UserSession.getInstace().cleanUserSession();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
           
        }
    }

    @FXML
    void film(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/SearchMovies.fxml"));

        try {
            Parent root = loader.load();
            codeC.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AfficherReclamation.fxml"));

        try {
            Parent root = loader.load();
            codeC.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void cinema(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/CinemaAffiche.fxml"));

        try {
            Parent root = loader.load();
            codeC.getScene().setRoot(root);

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
            codeC.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
