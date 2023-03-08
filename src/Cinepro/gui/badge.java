/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Badge;
import cinerpo.entities.Type_Badge;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Home
 */
public class badge extends Application {
    
    
    private boolean isError = true; // condition
    Badge b0 =new Badge(5,Type_Badge.gold,100,2);
    Badge b3=new Badge(5,Type_Badge.silver,100,2);
    Badge b =new Badge(5,Type_Badge.bronze,100,2);

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Définir le style CSS conditionnel
        if (b.getType().equals(Type_Badge.gold)) {
            root.setStyle("-fx-background-color: gold;");
        } else if (b.getType().equals(Type_Badge.silver)) {
            root.setStyle("-fx-background-color: silver;");
        }else{
            root.setStyle("-fx-background-color: darkkhaki;");
        }
        
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
   
    
}
