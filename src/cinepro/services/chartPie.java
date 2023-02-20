/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.services;

import cinepro.utils.cineproConnexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cinepro.entities.reservation;
import cinepro.interfaces.entityCRUD;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author kortb
 */
public class chartPie extends Application{
         
    @Override
        public void start(Stage stage) throws Exception {
            
         PieChart.Data slice1 = new PieChart.Data("Apples", 10);
        PieChart.Data slice2 = new PieChart.Data("Bananas", 5);
        PieChart.Data slice3 = new PieChart.Data("Oranges", 15);
        PieChart.Data slice4 = new PieChart.Data("Pears", 8);

        // Add the data to the pie chart
        PieChart pieChart = new PieChart();
        pieChart.getData().add(slice1);
        pieChart.getData().add(slice2);
        pieChart.getData().add(slice3);
        pieChart.getData().add(slice4);

        // Create a layout for the scene
        VBox root = new VBox();
        root.getChildren().add(pieChart);

        // Create the scene and show the stage
        Scene scene = new Scene(root, 500, 500);
        stage.setScene(scene);
        stage.show();
    
}
     public static void main(String[] args) {
        launch(args);
    }
}
