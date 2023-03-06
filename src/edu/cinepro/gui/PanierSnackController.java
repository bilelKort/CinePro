/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.snack;
import edu.connexion3A18.services.SnackCRUD;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Affiche();
        
    
    // Ajout du ScrollPane à la scène
   
    }
    ObservableList<snack> selection = FXCollections.observableArrayList();

    private void updateTotalPrice(Label totalPriceLabel, ObservableList<snack> selection) {
        double totalPrice = 0.0;
        for (snack product : selection) {
            totalPrice += product.getPrix();
        }
        
        totalPriceLabel.setText("Prix total : " + totalPrice);
         prixgold.setText("prix du badge spéciale"+(totalPrice*0.7));
    }

    private void vide(Label totalPriceLabel) {
        double totalPrice = 0.0;

        totalPriceLabel.setText("Prix total : " + totalPrice);
    }

    public void Affiche() {
        ListView<snack> selectionListView = new ListView<>(selection);
        SnackCRUD cr = new SnackCRUD();
        List<snack> products = cr.entitiesList2(9);
        for (snack product : products) {
            try {
                Label nameLabel = new Label(product.getNom());
                //    Label descriptionLabel = new Label(product.);
                Label priceLabel = new Label(Double.toString(product.getPrix()));
Button addButton = new Button("Ajouter au panier");
addButton.setStyle("-fx-background-color: #3CB371; -fx-text-fill: white; -fx-font-size: 13px;");

                Label espas = new Label();

                File file = new File(product.getPhoto());

// --> file:/C:/MyImages/myphoto.jpg
                String localUrl = file.toURI().toURL().toString();
                ImageView imageView = new ImageView();
//imageid.setImage(new Image("C:\Users\rayen\Pictures\cinema.jpg"));
                imageView.setImage(new Image(localUrl));
                imageView.setFitWidth(280);
                imageView.setFitHeight(198);

                VBox productBox = new VBox(imageView, nameLabel, priceLabel, addButton, espas);
                productBox.setSpacing(10.0);
                

                addButton.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

                addButton.setOnAction(event -> {
                    // Ajout du produit sélectionné à la liste des produits sélectionnés
                    if (product.getNom() != null) {
                        selection.add(product);
                        updateTotalPrice(totalPriceLabel, selection);

                    }
                });
                productList.getChildren().add(productBox);
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

}
