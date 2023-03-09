/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import cinepro.entities.reservation;
import cinepro.entities.reservation_place;
import cinepro.entities.reservation_snack;
import cinepro.services.reservationCRUD;
import cinepro.services.reservation_placeCRUD;
import cinepro.services.reservation_snackCRUD;
import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.snack;
import edu.connexion3A18.services.SnackCRUD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class PanierSnackController implements Initializable {

    @FXML
    private VBox vboxpanier;

    @FXML
    private Label totalPriceLabel;
    @FXML
    private VBox productList;
    @FXML
    private Button removeButton;
    @FXML
    private Label prixgold;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button Logout;
    public static PanierSnackController instance = new PanierSnackController();

    public static PanierSnackController getInstance() {
        return instance;
    }

    private int id_film;
    private int id_projection;
    Set<String> placereservees = new HashSet<String>();
    private int salle_id;

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public void setId_projection(int id_projection) {
        this.id_projection = id_projection;
    }

    public void setPlacereservees(Set<String> placereservees) {
        this.placereservees = placereservees;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Affiche();
        
    
    // Ajout du ScrollPane à la scène
   
    }
    ObservableList<snack> selection = FXCollections.observableArrayList();

    List<reservation_snack> b = new ArrayList<>();
    private void updateTotalPrice(Label totalPriceLabel, ObservableList<snack> selection) {
        double totalPrice = 0.0;
        for (snack product : selection) {
            totalPrice += product.getPrix();
        }



        ///////////////
        List<snack> selection2 = selection;
        System.out.println(selection);

        for (snack i : selection2) {
            Boolean duplicate=false;
            reservation_snack snackreservation1 = new reservation_snack(1, i.getPrix(), 0, i.getId_snack());
            for (reservation_snack s:b) {
                System.out.println("i: " + i.getId_snack());
                System.out.println("s: " + s.getId_snack());
                if (s.getId_snack() == i.getId_snack()) {
                    s.setQuantite(s.getQuantite() + 1);
                    s.setPrix(s.getPrix()+i.getPrix());
                    duplicate = true;
                }
            }
            if (!duplicate) {
                b.add(snackreservation1);
            }
        }
        System.out.println("test2: " + b);
        totalPriceLabel.setText("Prix total : " + totalPrice);
         prixgold.setText("prix du badge spéciale"+(totalPrice*0.7));
    }

    @FXML
    public void reserver(ActionEvent actionEvent) {
        reservationCRUD r = new reservationCRUD();
        r.addEntity(new reservation(UserSession.getInstace().getId(), instance.id_film, true, instance.id_projection));
        List<String> list = new ArrayList<>(instance.placereservees);

        reservation_placeCRUD rp = new reservation_placeCRUD();
        for(String c:list){
            System.out.println("test: " + c);
            reservation_place place = new reservation_place(c, 10, r.getlast());
            rp.addEntity(place);
        }

        reservation_snackCRUD rsc = new reservation_snackCRUD();
        for (reservation_snack rs:b) {
            rs.setId_reservation(r.getlast());
            rsc.addEntity(rs);
        }
    }

    private void vide(Label totalPriceLabel) {
        double totalPrice = 0.0;

        totalPriceLabel.setText("Prix total : " + totalPrice);
    }

    public void Affiche() {
        ListView<snack> selectionListView = new ListView<>(selection);
        SnackCRUD cr = new SnackCRUD();
        List<snack> products = cr.entitiesList2(instance.id_projection);
        for (snack product : products) {
            try {
                Label nameLabel = new Label("        "+product.getNom());
                //    Label descriptionLabel = new Label(product.);
                Label priceLabel = new Label("        "+Double.toString(product.getPrix()));
Button addButton = new Button("Ajouter au panier");
addButton.setStyle("-fx-background-color: #f9a460; -fx-text-fill: white; -fx-font-size: 13px;");

                Label espas = new Label();

                File file = new File(product.getPhoto());

// --> file:/C:/MyImages/myphoto.jpg
                String localUrl = file.toURI().toURL().toString();
                ImageView imageView = new ImageView();
//imageid.setImage(new Image("C:\Users\rayen\Pictures\cinema.jpg"));
                imageView.setImage(new Image(localUrl));
                imageView.setFitWidth(300);
                imageView.setFitHeight(228);

                VBox productBox = new VBox(imageView, nameLabel, priceLabel, addButton, espas);
                productBox.setSpacing(10.0);
                


                addButton.setOnAction(event -> {
                                    addButton.setStyle("-fx-background-color: brown; -fx-text-fill: white;");

                    // Ajout du produit sélectionné à la liste des produits sélectionnés
                    if (product.getNom() != null) {
                        selection.add(product);
                        updateTotalPrice(totalPriceLabel, selection);
                    }
                });
                productList.getChildren().add(productBox);
                anchor.setPrefHeight(3000);
                
            } catch (MalformedURLException ex) {
                Logger.getLogger(PanierSnackController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
removeButton.setStyle("-fx-background-color: #228B22; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;");

        removeButton.setOnAction(event -> {
            // Suppression du produit sélectionné de la liste des produits sélectionnés

            try {
                
                snack produitSelectionne = selectionListView.getSelectionModel().getSelectedItem();

                if (produitSelectionne != null) {
                    selection.remove(produitSelectionne);
                    updateTotalPrice(totalPriceLabel, selection);

                    System.out.println(selectionListView.getItems().get(0).getId_snack());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            

        });
        vboxpanier.getChildren().add(selectionListView);
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
