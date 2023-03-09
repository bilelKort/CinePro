/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.services;

import static cinepro.services.reservationCRUD.countFilmReservations;

import java.text.DecimalFormat;
import java.util.Map;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author kortb
 */

public class chartPie extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Map<String, Integer> filmReservationCounts = countFilmReservations();

        // Create a PieChart object
        PieChart pieChart = new PieChart();

        // Add the data to the PieChart and calculate the total reservation count
        int totalReservationCount = 0;
        for (Map.Entry<String, Integer> entry : filmReservationCounts.entrySet()) {
            String filmName = entry.getKey();
            int reservationCount = entry.getValue();
            pieChart.getData().add(new PieChart.Data(filmName, reservationCount));
            totalReservationCount += reservationCount;
        }

        // Create a Scene object and add the PieChart to it
        Scene scene = new Scene(new Group(pieChart), 500, 400);

        // Display the Scene using a Stage object
        stage.setScene(scene);
        stage.show();

        // Call addPercentageLabels after the stage is shown
        //addPercentageLabels(pieChart);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addPercentageLabels(PieChart chart) {
        if (chart == null || chart.getData() == null) {
            return;
        }
        final DecimalFormat df = new DecimalFormat("0.00%");
        double total = chart.getData().stream().mapToDouble(PieChart.Data::getPieValue).sum();

        for (PieChart.Data data : chart.getData()) {
            Label label = new Label(df.format(data.getPieValue() / total));
            label.setStyle("-fx-font-size: 12; -fx-font-weight: bold;");
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
                Point2D locationInScene = new Point2D(e.getSceneX(), e.getSceneY());
                label.relocate(locationInScene.getX(), locationInScene.getY() - 30);
                label.setVisible(true);
            });
            data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, e -> label.setVisible(false));
            chart.getScene().getRoot().getChildrenUnmodifiable().add(label);
        }
    }
}
