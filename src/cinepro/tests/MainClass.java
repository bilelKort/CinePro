/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.tests;

import cinepro.entities.*;
import cinepro.services.*;
import cinepro.utils.cineproConnexion;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author kortb
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
       /* String timeString = "2023-01-03 16:12:12";
        Timestamp timestamp= Timestamp.valueOf(timeString);  

        LocalDateTime dateTime = timestamp.toLocalDateTime();

        String weatherData = WeatherAPI.getWeatherData("Tunisia", timestamp);
        System.out.println(WeatherAPI.displayWeather(timestamp, "Tunisia"));*/
       reservation r = new reservation(2,66,true);
       reservationCRUD res = new reservationCRUD();
       res.addEntity(r);
       
    }
    /*public static void main(String[] args) {
        cineproConnexion mc = new cineproConnexion();
        launch(args);
    }*/

    /*@Override
    public void start(Stage stage) throws Exception {
                String reservationInfo = "John Doe, 123 Main St, Anytown USA";

       QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        ImageView imageView = qrCodeGenerator.generateQRCode(reservationInfo);

        StackPane sp = new StackPane();
        sp.getChildren().add(imageView);

        Stage primaryStage = null;
        Scene scene = new Scene(sp, 300, 300);
        stage.setScene(scene);
        stage.show();
    }*/  
}

