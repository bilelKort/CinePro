/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import cinepro.gui.AjoutProjectionController;
import edu.cinepro.entities.UserSession;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    @FXML
    private Button Logout;

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
Affiche();
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

            

        } else {
            s.updateEntity(instance.id, Boolean.TRUE);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("update  ?");
            alert.setHeaderText(null);
            alert.setContentText("updated ");
            alert.showAndWait();

           
        }
           initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());

    }

    public void Affiche() {
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
                    Rectangle rectangle = new Rectangle(30, 30);
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
    public void ajoutProjection(ActionEvent actionEvent) {
        try {
            AjoutProjectionController.getInstance().setId_salle(instance.id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjoutProjection.fxml"));
            Parent root =loader.load();
            placeid.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        UserSession.getInstace().cleanUserSession();
        //System.out.println(UserSession.getInstace().getId());

           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account");
            alert.setHeaderText(null);
            alert.setContentText("You have logged out successfully!");
            alert.showAndWait(); */
        Notifications notifications = Notifications.create();
        // notifications.graphic(new ImageView(notif));
        notifications.text("You have logged out successfully!");
        notifications.title("Success message");
        notifications.hideAfter(Duration.seconds(4));
        notifications.position(Pos.BOTTOM_LEFT);
        //notifications.darkStyle();
        notifications.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));

        try {
            Parent root = loader.load();
            Stage myWindow = (Stage) Logout.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Sign In");
            myWindow.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void film(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/SearchMovies.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AfficherReclamation.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void cinema(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/CinemaAffiche.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

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
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
