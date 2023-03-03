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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SalleController implements Initializable {

    private static final SalleController instance = new SalleController();
    private int id;
    @FXML
    private GridPane placeid;
    @FXML
    private Label longeurid;
    @FXML
    private Label largeurid;
    @FXML
    private Label nom;
    @FXML
    private Label etatid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static SalleController getInstance() {
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("test" + instance.id);
        SalleCRUD s = new SalleCRUD();
        salle actuel = s.entitiesList3(instance.id).get(0);
        nom.setText(actuel.getNom());
        largeurid.setText(Integer.toString(actuel.getLongueur()));

        placeid.setHgap(10);
        placeid.setVgap(10);
        
        if (actuel.isAcces() == false) {
            etatid.setText("la salle n'est pas disponible");
            for (int row = 0; row < actuel.getLongueur(); row++) {
                for (int col = 0; col < actuel.getLargeur(); col++) {

                    Pane pane = new Pane();
                    Rectangle rectangle = new Rectangle(20, 20);
                    rectangle.setFill(Color.RED);
                    rectangle.setStroke(Color.BLACK);

                    pane.getChildren().add(rectangle);

                    placeid.add(pane, col, row);
                }

            }

        } else {
            etatid.setText("la salle est disponible");
            for (int row = 0; row < actuel.getLongueur(); row++) {
                for (int col = 0; col < actuel.getLargeur(); col++) {

                    Pane pane = new Pane();
                    Rectangle rectangle = new Rectangle(30, 30);
                    rectangle.setFill(Color.WHEAT);
                    rectangle.setStroke(Color.BLACK);

                    pane.getChildren().add(rectangle);

                    placeid.add(pane, col, row);
                }

            }

        }

        longeurid.setText(Integer.toString(actuel.getLargeur()));

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

    @FXML
    private void update(ActionEvent event) {
        System.out.println("test" + instance.id);
        SalleCRUD s = new SalleCRUD();
        salle actuel = s.entitiesList3(instance.id).get(0);

        if (actuel.isAcces()) {

            s.updateEntity(instance.id, Boolean.FALSE);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update ");
            alert.setHeaderText(null);
            alert.setContentText("updated");
            alert.showAndWait();

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
        
        else {
            s.updateEntity(instance.id, Boolean.TRUE);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update  ?");
            alert.setHeaderText(null);
            alert.setContentText("updated ");
            alert.showAndWait();

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
