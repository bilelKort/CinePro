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
public class PlaceController implements Initializable {

    @FXML
    private GridPane idmatrice;
    private static final int ROWS = 9;
    private static final int COLS =9;
    @FXML
    private Label msg;
    @FXML
    private Label lista;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
}
