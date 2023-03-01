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
import static cinepro.services.reservationCRUD.countFilmReservations;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author kortb
 */
public class chartPie extends Application{
  
     @Override
        public void start(Stage stage) throws Exception {
            
         Map<String, Integer> filmReservationCounts = countFilmReservations();

         PieChart pieChart = new PieChart();

         // Add the data to the PieChart
    for (Map.Entry<String, Integer> entry : filmReservationCounts.entrySet()) {
        String filmName = entry.getKey();
        int reservationCount = entry.getValue();
        pieChart.getData().add(new PieChart.Data(filmName, reservationCount));
    }

    // Create a Scene object and add the PieChart to it
    Scene scene = new Scene(new Group(pieChart), 500, 400);

    // Display the Scene using a Stage object
    
    stage.setScene(scene);
    stage.show();
            
    
}
     public static void main(String[] args) {
        launch(args);
    }
}
