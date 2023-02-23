/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.reservation_place;
import cinepro.entities.reservation_snack;
import cinepro.services.reservation_placeCRUD;
import cinepro.services.reservation_snackCRUD;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class ReservationSnackController implements Initializable {

    @FXML
    private TextField prixsnack;
    @FXML
    private TextField idres;
    @FXML
    private TextField idsnack;
    @FXML
    private Button btnsnack;
    @FXML
    private TextField quantite;
    @FXML
    private Button btnup;
    @FXML
    private TextField idressnack;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    private boolean isFieldNotEmpty(String field) {
    return field != null && !field.isEmpty();
    }
    
    private boolean validateForm() {
         String prixSnack = prixsnack.getText();
        String idRes= idres.getText();
        String idSnack = idsnack.getText();
        String idResSnack = idressnack.getText();
        String Quantite = quantite.getText();
        
        
        if  (!isFieldNotEmpty(prixSnack) || !isFieldNotEmpty(idRes) || !isFieldNotEmpty(idSnack) || 
                !isFieldNotEmpty(idResSnack) || !isFieldNotEmpty(Quantite)) {
            
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
    public void saveReservationSnack (ActionEvent event) {        
            
        if(validateForm()){
        int qte = Integer.valueOf(quantite.getText());
        float Prix = Float.valueOf(prixsnack.getText());
        int idRes = Integer.valueOf(idres.getText());
        int ids = Integer.valueOf(idsnack.getText());
        if(qte>0 && Prix>0){
        reservation_snack res =new reservation_snack(qte,Prix,idRes,ids);
        System.out.println(res);
        reservation_snackCRUD pcd = new reservation_snackCRUD();
        
        pcd.addEntity(res);
        }
        }
        else{
            System.out.println("Invalid champ");
        }
    }
    
    @FXML
    public void updateSnack(ActionEvent event){
        
       int qte = Integer.valueOf(quantite.getText());
       float Prix = Float.valueOf(prixsnack.getText());
       int idRes = Integer.valueOf(idres.getText());
       int ids = Integer.valueOf(idsnack.getText());
       int idreservationsnack = Integer.valueOf(idressnack.getText());
       
        if(qte>0 && Prix>0){
        reservation_snack res =new reservation_snack(qte,Prix,idRes,ids);
        reservation_snackCRUD pcd = new reservation_snackCRUD();
        pcd.updateEntity(qte, Prix, idRes,ids,idreservationsnack);
        }
        else{
            System.out.println("Invalid champ");
        }
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
    public void supprimerReservationSnack (ActionEvent event) {
        

        if(confirmDelete()){
        int idreservationsnack = Integer.valueOf(idressnack.getText());
        reservation_snackCRUD pcd = new reservation_snackCRUD();
        pcd.deleteEntity(idreservationsnack);
        }
        }
}



