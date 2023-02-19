/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.reservation;
import cinepro.entities.reservation_place;
import cinepro.services.reservationCRUD;
import cinepro.services.reservation_placeCRUD;
import java.io.IOException;
import java.net.URL;
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
public class Reservation_placeController implements Initializable {

    @FXML
    private TextField coordonne;
    @FXML
    private TextField prix_place;
    @FXML
    private TextField idres;
    @FXML
    private Button btnres;
    @FXML
    private Button snackbtn;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void saveReservationPlace (ActionEvent event) {
        
        String seatNum = coordonne.getText();
        float Prix = Float.valueOf(prix_place.getText());
        int idRes = Integer.valueOf(idres.getText());
        
        reservation_place res =new reservation_place(seatNum,Prix,idRes);
        reservation_placeCRUD pcd = new reservation_placeCRUD();
        pcd.addEntity(res);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("reservationSnack.fxml"));
       try{
       Parent root = loader.load(); 

       snackbtn.getScene().setRoot(root);

       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    } 
    }
}
