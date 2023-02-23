/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

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
 * @author Home
 */
public class ChoixController implements Initializable {

    @FXML
    private Button updatebtn;
    @FXML
    private Button deletebtn;
    @FXML
    private Button displaybtn;
    @FXML
    private Button addbtn;
    @FXML
    private Button updatebtn2;
    @FXML
    private Button deletebtn2;
    @FXML
    private Button displaybtn2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void supprimer(ActionEvent event) {

        //REDIRECTION
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) deletebtn.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Update.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) updatebtn.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void afficher(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affiche.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) displaybtn.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void deleteBadge(ActionEvent event) {

        //REDIRECTION
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteBadge.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) deletebtn2.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void updateBadge(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateBadge.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) updatebtn2.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void displayBadge(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DisplayBadge.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) displaybtn2.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void addBadge(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADDBadge.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) addbtn.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
