/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.gui;

import cinepro.entities.Feedback;
import cinepro.entities.Reclamation;
import cinepro.services.FeedbackCRUD;
import cinepro.services.ReclamationCRUD;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.log.Log;
import edu.cinepro.entities.User;
import edu.cinepro.entities.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableview;
    @FXML
    private TableColumn<Reclamation,String> description;
    @FXML
    private TableColumn<Reclamation,Integer> id_reclamation;
    @FXML
    private TableColumn<Reclamation,String> date;
    @FXML
    private TableColumn<Reclamation,String> etat;
    public ObservableList<Reclamation> k = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Logout;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableview.getItems().clear();
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }
        // TODO
          ReclamationCRUD cd = new ReclamationCRUD();
        List<Reclamation> liste =new ArrayList<Reclamation>();
        liste=cd.EntityList();

        System.out.println(liste);
        for (Reclamation f : liste) {
            k.add(f);
        }


        description.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("date"));
        id_reclamation.setCellValueFactory(new PropertyValueFactory<Reclamation,Integer>("id_reclamation"));
        etat.setCellValueFactory(new PropertyValueFactory<Reclamation,String>("etat"));

        tableview.setItems(k);
        System.out.println(tableview.getItems());
    }


    @FXML
    public void logout(ActionEvent event) {
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/SignIn.fxml"));

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));

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
            }else {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/GerantIndex.fxml"));

            }
        }

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updateEtat(ActionEvent actionEvent) {
        int selectedIndex = tableview.getSelectionModel().getSelectedIndex();
        Reclamation c = (Reclamation) tableview.getSelectionModel().getSelectedItem();
        new ReclamationCRUD().updateEtat(c.getId_reclamation(), !c.isEtat());
        initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());
    }
}
