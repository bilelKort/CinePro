/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.salle;
import edu.connexion3A18.services.SalleCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AjoutersalleController implements Initializable {

    @FXML
    private TextField nomid;
    @FXML
    private TextField longeurid;
    @FXML
    private TextField largeurid;
    private static final AjoutersalleController instance = new AjoutersalleController();
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static AjoutersalleController getInstance() {
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(instance.id);
    }

    @FXML
    private void ajouterid(ActionEvent event) {

        Boolean longeurtest = false;
        Boolean largeurtest = false;

        Boolean nomtest = false;
        if (nomid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier    |o_O|");

            alert.showAndWait();
        } else {
            nomtest = true;
        }

        if (longeurid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier    |o_O|");

            alert.showAndWait();
        } else {

            try {
                int longeurint = Integer.valueOf(longeurid.getText());
                System.out.println(longeurint);
                longeurtest = true;
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText(null);
                alert.setContentText("Verifier    |o_O|");

                alert.showAndWait();

            }
        }
        if (largeurid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier largeur   |o_O|");

            alert.showAndWait();
        } else {

            try {
                int largeur = Integer.valueOf(largeurid.getText());
largeurtest=true;
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText(null);
                alert.setContentText("Verifier  largeur  |o_O|");

                alert.showAndWait();

            }

        }

        if ((longeurtest) && (largeurtest) && (nomtest)) {
            int longeurint = Integer.valueOf(longeurid.getText());
            int largeurint = Integer.valueOf(largeurid.getText());
            int intid = Integer.valueOf(instance.id);

            salle cc = new salle(nomid.getText(), longeurint, largeurint, intid,false);
            SalleCRUD pc = new SalleCRUD();

            System.out.println(cc.getId_cinema());
            System.out.println(cc.getLargeur());
            System.out.println(cc.getLargeur());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("add");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous ajouter ?");

        if (alert.showAndWait().get() == ButtonType.OK) {

                    pc.addEntity(cc);
                    System.out.println("ok !");
                     try {
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            
        }
        
        else {
         try {
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            
        
        }
            }
            
    
        

    }

    @FXML
    private void back(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("retourner à la page précédente  ?");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous retourner ? ");

        if (alert.showAndWait().get() == ButtonType.OK) {
            try {

                System.out.println("ok !");
                Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
