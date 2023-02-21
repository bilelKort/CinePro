/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class PlaceController implements Initializable {

    @FXML
    private GridPane idmatrice;
    private static final int ROWS = 10;
    private static final int COLS =10;

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
        coordMap.add("4,9");
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

                            System.out.println("impossible cette place est déja reservé");
                        });
                        break;

                    } else {

// action onClick pour reserver une place
                        pane.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
                            int clickedRow = GridPane.getRowIndex(pane);
                            int clickedCol = GridPane.getColumnIndex(pane);
                            rectangle.setFill(Color.web("#eb2e66"));
                            rectangle.setStroke(Color.BLACK);

                            System.out.println("chaise cliquée : " + clickedRow + "," + clickedCol);

                            String b = clickedRow + "," + clickedCol;
                            if (placereservees.contains(b)) {
                                placereservees.remove(b);
                                System.out.println("annulation");
                                rectangle.setFill(Color.WHITE);
                                rectangle.setStroke(Color.BLACK);

                            } else {
                                placereservees.add(b);
                                System.out.println(placereservees);
                            }

                        });

                    }
                }
            }
        }

       // idmatrice.getChildren().add(matrice);
    }

}
