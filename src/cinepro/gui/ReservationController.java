/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.*;
import cinepro.services.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class ReservationController implements Initializable {

    @FXML
    private TextField resprix;
    @FXML
    private TextField residuser;
    @FXML
    private TextField residfilm;
    @FXML
    private TextField resstate;
     @FXML
    private TextField starttime;
    @FXML
    private TextField endtime;
    @FXML
    private Button btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 
    @FXML
    private void saveReservation(ActionEvent event) {
        //float prix = Float.valueOf(resprix.getText());
        int iduser = Integer.valueOf(residuser.getText());
        int idfilm = Integer.valueOf(residfilm.getText());
        boolean state = Boolean.valueOf(resstate.getText());
        Timestamp start_time = Timestamp.valueOf(starttime.getText());
        Timestamp end_time = Timestamp.valueOf(endtime.getText());
        
        if(iduser>0 && idfilm>0){
        reservationCRUD pcd = new reservationCRUD();
        reservation res = new reservation(0.0f,iduser,idfilm,state,start_time,end_time);
        pcd.addEntity(res);
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation_place.fxml"));
       try{
       Parent root = loader.load(); 

        btn.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    } 
        }
        else{
           System.out.println("Invalid champ");
        }
       
    }
    
      
}
