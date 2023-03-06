/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
public class MenuReclamationController implements Initializable {

    @FXML
    private Button btnajouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnafficher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void AjouterReclamation(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));

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
    private void ModifierReclamation(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateReclamation.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) btnModifier.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerReclamation(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteReclamation.fxml"));
              
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
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
              
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
