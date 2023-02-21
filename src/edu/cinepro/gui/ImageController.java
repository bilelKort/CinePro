/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ImageController implements Initializable {

    @FXML
    private ImageView imageid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            File file = new File("C:/Users/rayen/Pictures/cinema.jpg");
            
// --> file:/C:/MyImages/myphoto.jpg
String localUrl = file.toURI().toURL().toString();
//imageid.setImage(new Image("C:\Users\rayen\Pictures\cinema.jpg"));
imageid.setImage(new Image(localUrl));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
