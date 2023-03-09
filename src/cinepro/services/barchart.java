/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.services;

import static cinepro.services.reservationCRUD.countFilmReservations;

import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 *
 * @author kortb
 */
public class barchart extends Application{
           
    @Override
        public void start(Stage stage) throws Exception {
            
         Map<String, Integer> filmReservationCounts = countFilmReservations();

        if (filmReservationCounts != null) {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            final BarChart<String,Number> chart = new BarChart<>(xAxis,yAxis);
            chart.setTitle("Film Reservations");
            xAxis.setLabel("Film");
            yAxis.setLabel("Reservations");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Reservations");

            for (Map.Entry<String, Integer> entry : filmReservationCounts.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                
            }

            Scene scene  = new Scene(chart,800,600);
            chart.getData().add(series);

            stage.setScene(scene);
            stage.show();
        }
    
}
     public static void main(String[] args) {
        launch(args);
    }
}
