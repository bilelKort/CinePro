/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.tests;

import cinepro.entities.*;
import cinepro.services.*;
import cinepro.utils.cineproConnexion;
import java.sql.Timestamp;
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
public class MainClass extends Application{
    /*public static void main(String[] args) {
        
        Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2023); // set the year to 2023
calendar.set(Calendar.MONTH, Calendar.FEBRUARY); // set the month to February (note that January is 0)
calendar.set(Calendar.DAY_OF_MONTH, 24); // set the day of the month to 20
calendar.set(Calendar.HOUR_OF_DAY, 3); // set the hour of the day to 10 (24-hour clock)
calendar.set(Calendar.MINUTE, 35); // set the minute to 30
calendar.set(Calendar.SECOND, 0); // set the second to 0
calendar.set(Calendar.MILLISECOND, 0); // set the millisecond to 0

Timestamp timestamp = new Timestamp(calendar.getTimeInMillis());

 Calendar calendar2 = Calendar.getInstance();
calendar2.set(Calendar.YEAR, 2023); // set the year to 2023
calendar2.set(Calendar.MONTH, Calendar.FEBRUARY); // set the month to February (note that January is 0)
calendar2.set(Calendar.DAY_OF_MONTH, 24); // set the day of the month to 20
calendar2.set(Calendar.HOUR_OF_DAY, 3); // set the hour of the day to 10 (24-hour clock)
calendar2.set(Calendar.MINUTE, 50); // set the minute to 30
calendar2.set(Calendar.SECOND, 0); // set the second to 0
calendar2.set(Calendar.MILLISECOND, 0); // set the millisecond to 0

Timestamp timestamp2 = new Timestamp(calendar2.getTimeInMillis());
        
        reservationCRUD pcd = new reservationCRUD();
        //reservation res = new reservation(1,1,true,timestamp,timestamp2);
       // System.out.println(pcd.checkUser(res));
        //System.out.println(pcd.checkFilm(res));
//pcd.addEntity(res);
        //pcd.deleteEntity(13,2,1);
        System.out.println(pcd.entitiesList()); 
        
        //reservation_place r1=new reservation_place("(11,11)",40.5f,57);
      //reservation_placeCRUD pcd = new reservation_placeCRUD();
        //pcd.addEntity(r1);
        //pcd.deleteEntity(1);
      // pcd.updateEntity(60, 49, "(4,4)", 15f, timestamp, timestamp);
       // System.out.println(pcd.check1(r1));
      // System.out.println(pcd.check2(r1));

//System.out.println(pcd.entitiesList()); 
        //pcd.updateEntity(5, "(10,10)", 60.9f, 7);
      //reservation_snack rr = new reservation_snack(30,50.9f,11,1);
      //reservation_snackCRUD pcs = new reservation_snackCRUD();
      //pcs.updateEntity(3, 20f, 58, 1, 15);
      
      /*chartPie chartDemo = new chartPie();
        chartDemo.launch(args);
      */
      
             
    //}*/
    public static void main(String[] args) {
        cineproConnexion mc = new cineproConnexion();
        launch(args);
    }

    @Override
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
    }  
}

