/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

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
 * @author Home
 */
public class SabonnerController implements Initializable {

    @FXML
    private Button ab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void sabonner(ActionEvent event) {
        Personne p = new Personne("ines", "yeddes", "ines.yeddes@esprit.tn");

        //REDIRECTION
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Abonnement.fxml"));

        try {
            Parent root = loader.load();
            AbonnementController ac = loader.getController();
            ac.setIdNom(p.getNom());
            ac.setIdPrenom(p.getPrenom());
            ac.setMail(p.getMail());
            ab.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
