package edu.cinepro.gui;

import cinepro.gui.MovieDetailsController;
import edu.cinepro.entities.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GerantIndexController implements Initializable {

    @FXML
    private Button search11;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    @FXML
    public void ajoutMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjoutProjection.fxml"));
            Parent root =loader.load();
            search11.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void listProjections(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/ListProjections.fxml"));
            Parent root =loader.load();
            search11.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void afficherCinemas(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CinemaAffiche.fxml"));
            Parent root =loader.load();
            search11.getScene().setRoot(root);
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
            Stage myWindow = (Stage) search11.getScene().getWindow();
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
            search11.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));
        try {
            Parent root = loader.load();
            search11.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
