/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.services.barchart;
import cinepro.services.chartPie;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class MenuController implements Initializable {

    @FXML
    private Button affres;
    @FXML
    private Button affresplace;
    @FXML
    private Button affressnack;
    @FXML
    private Button crudplace;
    @FXML
    private Button crudsnack;
    @FXML
    private Button crudglobal;
    @FXML
    private Button btnpie;
    @FXML
    private Button btnbar;
    @FXML
    private PieChart pieChart;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
          @FXML
      public void showreservation(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Display.fxml"));
       try{
       Parent root = loader.load(); 

        affres.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    
      @FXML
       public void showreservationPlace(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailPlace.fxml"));
       try{
       Parent root = loader.load(); 

        affresplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
       
        @FXML
       public void showreservationSnack(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailSnack.fxml"));
       try{
       Parent root = loader.load(); 

        affressnack.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
       
   @FXML
       public void showreservationCRUD(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
       try{
       Parent root = loader.load(); 

        crudglobal.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }    
       
   @FXML
       public void showreservationPlaceCRUD(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation_place.fxml"));
       try{
       Parent root = loader.load(); 

        crudplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }  
       
       @FXML
       public void showreservationSnackCRUD(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ReservationSnack.fxml"));
       try{
       Parent root = loader.load(); 

        crudsnack.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }    
    @FXML
    public void DisplayChartPie() throws Exception{
        chartPie chartpie = new chartPie();
        Stage stage = new Stage();
        chartpie.start(stage);
        
    }
    @FXML
    public void DisplayBarChart() throws Exception{
        barchart barchart = new barchart();
        Stage stage = new Stage();
        barchart.start(stage);
    }
}
