/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

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
    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salle s = new SalleCRUD().entitiesList3(insatance.id_salle).get(0);
        ROWS = s.getLongueur();
        COLS = s.getLargeur();
        ArrayList<String> coordMap = new ArrayList<String>();
        Set<String> placereservees = new HashSet<String>();

        coordMap.add("5,5");
        coordMap.add("5,7");
        coordMap.add("5,9");
        coordMap.add("5,8");
        coordMap.add("4,8");
        coordMap.add("3,5");
        System.out.println(coordMap);

        
        //permet d'organiser des nœuds graphiques en lignes et en colonnes.cellule peut contenir un nœud graphique.
      //  GridPane matrice = new GridPane();
       
        idmatrice.setHgap(10);
        idmatrice.setVgap(10);

        // Creation 
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                for (String i : coordMap) {
                    Pane pane = new Pane();
                    Rectangle rectangle = new Rectangle(30, 30);
                    rectangle.setFill(Color.WHITE);
                    rectangle.setStroke(Color.BLACK);
                    
                    
                    pane.getChildren().add(rectangle);
                   
                    
                    idmatrice.add(pane, col, row);

                   
                    
                    String coordMatriceVide = row + "," + col;

                    if (coordMatriceVide.equals(i)) {
                        rectangle.setFill(Color.web("#7899b9"));
                        rectangle.setStroke(Color.BLACK);
                        pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                            msg.setText("impossible cette place est déja reservé");
                            lista.setText(placereservees.toString()  );

                        });
                        break;

                    } else {

// action onClick pour reserver une place
                        pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                            int clickedRow = GridPane.getRowIndex(pane)+1;
                            int clickedCol = GridPane.getColumnIndex(pane)+1;
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
        }

       // idmatrice.getChildren().add(matrice);
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
