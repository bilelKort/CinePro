/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.cinema;
import edu.connexion3A18.services.CinemaCRUD;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CinemaController implements Initializable {

    @FXML
    private TextField ftnom;
    @FXML
    private TextField ftdescription;
    @FXML
    private Button ftsave;
    @FXML
    private Button ftimage;
    @FXML
    private Label labelsinglefile;

    private File f;
    @FXML
    private WebView map;
    @FXML
    private Label idnomerror;
    @FXML
    private Label iderrordecription;
    @FXML
    private Label iderrorlocation;
    @FXML
    private Label iderrorimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO   
        map.getEngine().load(" https://www.google.com/maps");
    }

    @FXML
    private void importimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif", "*.bmp"));
        f = fc.showOpenDialog(null);
        if (f != null) {
            labelsinglefile.setText("Select file" + f.getAbsolutePath());
            System.out.println(f.getAbsolutePath());
        }

    }

    @FXML
    private void savecinema(ActionEvent event) {

        Boolean nom = false;

        Boolean localisation = false;

        Boolean description = false;
        Boolean url = false;

        Boolean mapb = false;

        // String resNom = ftnom.getText();
        // String resdescription= ftdescription.getText();
        //    int resIdUser=Integer.valueOf(iduser.getText());
        // CinemaCRUD PCD = new CinemaCRUD();       
        //  System.out.println(map.getEngine().getLocation().equals("https://www.google.com/maps/@35.8088704,10.6135552,14z"));//https://www.google.com/maps   ou https://www.google.com/maps/@35.8088704,10.6135552,14z
        // System.out.println(labelsinglefile.getText().equals("URL:"));
        //       System.out.println("affiche:"+ resNom.equals("")+resdescription.equals(""));
        //https://www.google.com/maps/embed/Path%C3%A9+Azur+City
        String resNom = ftnom.getText();
        String resdescription = ftdescription.getText();
        // int resIdUser = Integer.valueOf(iduser.getText());
        if (resNom.equals("")) {
            idnomerror.setText("il faut entrer un nom !! ");

        } else {
            nom = true;
            idnomerror.setText("");
        }
        if (resdescription.equals("")) {
            iderrordecription.setText("il faut entrer une description");

        } else {
            description = true;

            iderrordecription.setText("");

        }
        if (labelsinglefile.getText().equals("URL:")) {
            iderrorimage.setText("il faut entrer un url");

        } else {
            url = true;
            iderrorimage.setText("");

        }
        if (((map.getEngine().getLocation().equals("https://www.google.com/maps/@36.7260284,10.2558798,17z")))
                || (map.getEngine().getLocation().equals("https://www.google.com/maps"))) {
            iderrorlocation.setText("il faut entrer une localisation !!!");
mapb = false;
        } else {
            mapb = true;
                        iderrorlocation.setText("");


        }
 if ((mapb == false)
               || (url == false)
                || (description == false) || (nom == false)) {
      Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("noo");
            alert1.setHeaderText(null);
            alert1.setContentText("impossible  ");
     alert1.showAndWait(); 
     
 }
        if ((mapb == true)
                && (url == true)
                && (description == true) && (nom == true)) {
            cinema c = new cinema(1, resNom, map.getEngine().getLocation(), resdescription, f.getAbsolutePath());
            System.out.println(c);

            CinemaCRUD PCD = new CinemaCRUD();
                    cinema k =PCD.cinemabyid(c.getId_cinema());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("  >.<  VERIF");
            alert.setHeaderText(null);
            alert.setContentText("dooneee   (^_^)");

            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    
                    PCD.addEntity(c);
                    
                    System.out.println("done !");
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            } 
            else {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());                }
            }

            // System.out.println(c);
            /*
        
        CinemaCRUD PCD = new CinemaCRUD();
        
        
        cinema c= new cinema( resIdUser, resNom, map.getEngine().getLocation(), resdescription, f.getAbsolutePath());
   
        System.out.println(c);        
  if(c.getNom().equals("")){
      System.out.println("remplire le champs");
  }
      
       PCD.addEntity(c);
        System.out.println("done !");

        /*  //REDIRECTION 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("details.fxml"));
        try {
            Parent root = loader.load();
            DetailsController dc= loader.getController();
            dc.setResultatnom(resNom);
            dc.setResultatprenom(resPrenom);
            ftnom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());        }
        
                
        
        
    }*/
        } 
    }

    @FXML
    private void back(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("annuler ?");
            alert.setHeaderText(null);
            alert.setContentText("voulez vous annuler ?       |o_O|");

            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    
                    System.out.println("ok !");
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CinemaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            
        
    }
}


