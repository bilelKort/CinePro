/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.salle;
import edu.connexion3A18.services.SalleCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

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
    @FXML
    private Button Logout;

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
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }
        System.out.println("test"+instance.id);
        nomid.clear();
        longeurid.clear();
        largeurid.clear();
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
            alert.setContentText("il faut remplir le nom   |o_O|");

            alert.showAndWait();
        } else {
            nomtest = true;
        }

        if (longeurid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("il faut verifier  longeur  |o_O|");

            alert.showAndWait();
        } else {

            try {
                int longeurint = Integer.valueOf(longeurid.getText());
                System.out.println(longeurint);
                if(longeurint>0){
                longeurtest = true;
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText(null);
                alert.setContentText("il faut verifier longeur   |o_O|");

                alert.showAndWait();

            }
        }
        if (largeurid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("il faut verifier largeur   |o_O|");

            alert.showAndWait();
        } else {

            try {
                int largeur = Integer.valueOf(largeurid.getText());
                if(largeur>0){
largeurtest=true;
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error ");
                alert.setHeaderText(null);
                alert.setContentText("il faut verifier largeur  |o_O|");

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
                            initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());

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

}
