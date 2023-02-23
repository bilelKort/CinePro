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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class ReservationController implements Initializable {

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
    @FXML
    private Button suppbtn;
    @FXML
    private TextField idreservation;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private boolean isFieldNotEmpty(String field) {
    return field != null && !field.isEmpty();
    }
    
    private boolean validateForm() {
         String residUser = residuser.getText();
        String residFilm = residfilm.getText();
        String idReservation = idreservation.getText();
        String resState = resstate.getText();
        String startTime = starttime.getText();
        String endTime = endtime.getText();
        if  (!isFieldNotEmpty(residUser) || !isFieldNotEmpty(residFilm) || !isFieldNotEmpty(idReservation) || 
                !isFieldNotEmpty(resState) || !isFieldNotEmpty(startTime) || !isFieldNotEmpty(endTime)) {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Form");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled!");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    
    @FXML
    public void saveReservation(ActionEvent event) {
        //float prix = Float.valueOf(resprix.getText());
        
        
        if(validateForm()){    
        int iduser = Integer.valueOf(residuser.getText());
        int idfilm = Integer.valueOf(residfilm.getText());
        boolean state = Boolean.valueOf(resstate.getText());
        Timestamp start_time = Timestamp.valueOf(starttime.getText());
        Timestamp end_time = Timestamp.valueOf(endtime.getText());
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
    
    public void supprimerReservation (ActionEvent event) {
        
       int idres = Integer.valueOf(idreservation.getText());

        
        reservationCRUD pcd = new reservationCRUD();
        

        pcd.deleteEntity(idres);
    }  
}
