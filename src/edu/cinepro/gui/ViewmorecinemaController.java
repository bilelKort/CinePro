/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.cinema;
import edu.connexion3A18.services.CinemaCRUD;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ViewmorecinemaController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private WebView map;
    @FXML
    private ImageView image;

    private String nom1;

    public String getNom1() {
        return nom1;
    }

    public void setNom1(String nom1) {
        this.nom.setText(nom1);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(String path) {
       try {
            File file = new File(path);
            
// --> file:/C:/MyImages/myphoto.jpg
String localUrl = file.toURI().toURL().toString();
//imageid.setImage(new Image("C:\Users\rayen\Pictures\cinema.jpg"));
image.setImage(new Image(localUrl));
        } catch (MalformedURLException ex) {
            Logger.getLogger(ImageController.class.getName()).log(Level.SEVERE, null, ex);
        };
    }
    
    
    public TextField getId() {
        return id;
        // TODO
    }    

    /**
     * Initializes the controller class.
     */
    public void setId(String i) {
        this.id.setText(i);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

CinemaCRUD cd = new CinemaCRUD();
        System.out.println(id.getText());
        cinema liste = cd.cinemabyid(1);
    }    
    
}
