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
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class Reservation_placeController implements Initializable {

    @FXML
    private TextField id_place;
    @FXML
    private TextField coordonne;
    @FXML
    private TextField prix_place;
    @FXML
    private TextField idres;
    @FXML
    private TextField starttime;
    @FXML
    private TextField endtime;
    @FXML
    private Button btnres;
    @FXML
    private Button snackbtn;
    @FXML
    private Button btnUpdate;
     @FXML
    private Button btnsupp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void saveReservationPlace (ActionEvent event) {
        
        String seatNum = coordonne.getText();
        float Prix = Float.valueOf(prix_place.getText());
        int idRes = Integer.valueOf(idres.getText());
       
        reservation_place res = new reservation_place(seatNum, Prix, idRes);
        reservation_placeCRUD pcd = new reservation_placeCRUD();
        pcd.addEntity(res); 
    }
    
    /****************************************************************************************************/
    /****************************************************************************************************/
    
    @FXML
    public void saveReservationPlaceSnack (ActionEvent event) {
        
        String seatNum = coordonne.getText();
        float Prix = Float.valueOf(prix_place.getText());
        int idRes = Integer.valueOf(idres.getText());
        
        
        reservation_place res = new reservation_place(seatNum, Prix, idRes);
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

    
    @FXML
    public void updateReservationPlace (ActionEvent event) {
        
        String seatNum = coordonne.getText();
        float Prix = Float.valueOf(prix_place.getText());
        int idRes = Integer.valueOf(idres.getText());
       
        int idpl = Integer.valueOf(id_place.getText());

        
        reservation_place res = new reservation_place(idpl,seatNum, Prix, idRes);
        reservation_placeCRUD pcd = new reservation_placeCRUD();
        pcd.updateEntity(idpl,idRes, seatNum, Prix);
    }
    
    
    private boolean confirmDelete() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirm Delete");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this element?");

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
    }
    
    @FXML
    public void supprimerReservationPlace (ActionEvent event) {
        if(confirmDelete()){
            int idresplace = Integer.valueOf(id_place.getText());      
            reservation_placeCRUD pcd = new reservation_placeCRUD();
            pcd.deleteEntity(idresplace); 
        }
       
    }
    
    
}


  