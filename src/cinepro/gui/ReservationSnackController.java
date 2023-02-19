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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class ReservationSnackController implements Initializable {

    @FXML
    private TextField idsnack;
    @FXML
    private TextField prixsnack;
    @FXML
    private TextField idres;
    @FXML
    private Button btnsnack;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void saveReservationSnack (ActionEvent event) {
        
        int idSnack = Integer.valueOf(idsnack.getText());
        float Prix = Float.valueOf(prixsnack.getText());
        int idRes = Integer.valueOf(idres.getText());
        
        reservation_snack res =new reservation_snack(idSnack,Prix,idRes);
        reservation_snackCRUD pcd = new reservation_snackCRUD();
        pcd.addEntity(res);
    }
}
