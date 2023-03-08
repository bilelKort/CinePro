/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.gui;

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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnafficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Feedback.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnajouter.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifier.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnmodifier.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void supprimer(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("supprimer.fxml"));
              
             try {
                 Parent root = loader.load();
                  Scene scene = new Scene (root);
         Stage stage = (Stage) btnsupprimer.getScene().getWindow();
         stage.setScene(scene);
         
             } catch (IOException ex) {
                 System.out.println(ex.getMessage());
             }       
        
    }

    @FXML
    private void afficher(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherdetails.fxml"));
              
             try {
                 Parent root = loader.load();
                  Scene scene = new Scene (root);
         Stage stage = (Stage) btnafficher.getScene().getWindow();
         stage.setScene(scene);
         
             } catch (IOException ex) {
                 System.out.println(ex.getMessage());
             }       
        
        
    }
    

}
