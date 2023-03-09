/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import cinepro.entities.reservation;
import cinepro.entities.reservation_place;
import cinepro.services.reservationCRUD;
import cinepro.services.reservation_placeCRUD;
import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.salle;
import edu.connexion3A18.services.SalleCRUD;
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

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class PlaceController implements Initializable {

    @FXML
    private GridPane idmatrice;
    private int ROWS;
    private int COLS;
    @FXML
    private Label msg;
    @FXML
    private Label lista;
    public static PlaceController insatance = new PlaceController();
    private int id_salle;
    public static PlaceController getInstance() {
        return insatance;
    }
    @FXML
    private Button Logout;
    private int id_projection;
    private int id_film;

    public void setId_film(int id_film) {
        insatance.id_film = id_film;
    }

    public void setId_projection(int id_projection) {
        insatance.id_projection = id_projection;
    }

    Set<String> placereservees = new HashSet<String>();

    public void setId_salle(int id_salle) {
        insatance.id_salle = id_salle;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }

        salle s = new SalleCRUD().entitiesList3(insatance.id_salle);
        ROWS = s.getLongueur();
        COLS = s.getLargeur();
        ArrayList<String> coordMap = new ArrayList<String>();

        List<reservation_place> list = new reservation_placeCRUD().getplaces(insatance.id_projection);
        for (reservation_place place:list) {
            coordMap.add(place.getCoordonnee());
        }

        
        //permet d'organiser des nœuds graphiques en lignes et en colonnes.cellule peut contenir un nœud graphique.
      //  GridPane matrice = new GridPane();
       
        idmatrice.setHgap(10);
        idmatrice.setVgap(10);

        // Creation 
        for (int row = 1; row <= ROWS; row++) {
            for (int col = 1; col <= COLS; col++) {


                Pane pane = new Pane();
                Rectangle rectangle = new Rectangle(30, 30);
                System.out.println("colorrrrr");
                rectangle.setFill(Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                pane.getChildren().add(rectangle);
                idmatrice.add(pane, col, row);

                if(coordMap.contains(""+row+","+col)) {
                    rectangle.setFill(Color.web("#7899b9"));
                    rectangle.setStroke(Color.BLACK);
                    pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                        msg.setText("impossible cette place est déja reservé");
                        lista.setText(placereservees.toString()  );

                    });

                }else {
                    pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                        int clickedRow = GridPane.getRowIndex(pane);
                        int clickedCol = GridPane.getColumnIndex(pane);
                        rectangle.setFill(Color.web("#eb2e66"));
                        rectangle.setStroke(Color.BLACK);

                        msg.setText("chaise cliquée : " + clickedRow + "," + clickedCol);
                        lista.setText(placereservees.toString()  );

                        String b = clickedRow + "," + clickedCol;
                        if (placereservees.contains(b)) {
                            placereservees.remove(b);
                            msg.setText("annulation");
                            System.out.println("annulation"+placereservees);
                            Set<String> k =placereservees;

                            lista.setText(placereservees.toString()  );

                            rectangle.setFill(Color.WHITE);
                            rectangle.setStroke(Color.BLACK);

                        } else {
                            placereservees.add(b);
                            System.out.println("ajouut"+placereservees);
                            lista.setText(placereservees.toString()  );
                        }

                    });
                }




                }

        }

       // idmatrice.getChildren().add(matrice);
    }

    @FXML
    private void reserver(ActionEvent actionEvent) {
        PanierSnackController.getInstance().setId_projection(insatance.id_projection);
        PanierSnackController.getInstance().setId_film(insatance.id_film);
        PanierSnackController.getInstance().setPlacereservees(placereservees);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/PanierSnack.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


  @FXML
    
    private void back(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("annuler ?");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous annuler ?       |o_O|");

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
