/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.entities.UserSession;
import edu.cinepro.services.CineproCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class IndexController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int id = UserSession.getInstace().getId();
        CineproCRUD ccd = new CineproCRUD();
       /* Cinepro c = new Cinepro();
        c = ccd.getUserById(id); */
       // System.out.println("Bonjour "+UserSession.getInstace().getNom()+" !");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Bonjour "+UserSession.getInstace().getNom()+" !");
        alert.show();
        
       
        
        UserSession.getInstace().cleanUserSession(); // Deconnecter
        System.out.println(UserSession.getInstace().getId());

        /*
        System.out.println("Bonjour "+UserSession.getInstace().getNom()+" !");
        */
        
    }    
    
}
