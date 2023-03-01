/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.*;
import cinepro.services.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Projection;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
    @FXML
    private Button Menu;
    @FXML
    private Button update;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
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
        if  (!isFieldNotEmpty(residUser) || !isFieldNotEmpty(residFilm) || 
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
        
        int c1 = pcd.checkUser(res);
        int c2 = pcd.checkFilm(res);
       String reservationInfo = "id reservation: " + res.getId_reservation() + "\n id user: " + res.getId_user() + "\n id_film: " 
                + res.getId_film() + "\n start time: " + res.getStart_time() + "\n end time: " + res.getEnd_time();
               // String reservationInfo = "John Doe, 123 Main St, Anytown USA";

        if(c1>0 && c2>0){
       FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation_place.fxml"));
       try{
       Parent root = loader.load(); 

        btn.getScene().setRoot(root);
        ////////////////////////////////////////////////////////////////
        QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
        ImageView imageView = null;
        ImageView qrCodeImageView = null;

        qrCodeImageView = qrCodeGenerator.generateQRCode(reservationInfo);
        Stage qrCodeStage = new Stage();
        qrCodeStage.setTitle("Reservation QR Code");

    // Check if qrCodeImageView is null
    if (qrCodeImageView != null) {
        // Add ImageView object to the scene
        Scene scene = new Scene(new Group(qrCodeImageView));
        qrCodeStage.setScene(scene);

        // Show the new stage
        qrCodeStage.show();
    }
       }catch(IOException ex){
        System.out.println(ex.getMessage());
    } 
        }
        }
        else{
           System.out.println("Invalid champ");
        }
       
    }
    
    public void updateReservation(ActionEvent event){
        
       int iduser = Integer.valueOf(residuser.getText());
        int idfilm = Integer.valueOf(residfilm.getText());
        boolean state = Boolean.valueOf(resstate.getText());
        Timestamp start_time = Timestamp.valueOf(starttime.getText());
        Timestamp end_time = Timestamp.valueOf(endtime.getText());
        reservationCRUD pcd = new reservationCRUD();
        reservation res = new reservation(0.0f,iduser,idfilm,state,start_time,end_time);
       
        
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
    public void supprimerReservation (ActionEvent event) {
        if(confirmDelete()){
          int idres = Integer.valueOf(idreservation.getText());
          reservationCRUD pcd = new reservationCRUD();
          pcd.deleteEntity(idres);  
        }   
    } 
    
    
    @FXML
       public void showMenu(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
       try{
       Parent root = loader.load(); 

        Menu.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
       
       
       
       
}
