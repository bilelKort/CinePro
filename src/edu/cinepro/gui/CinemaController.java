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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

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
    private TextField iduser;
    @FXML
    private WebView map;

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
            System.out.println("il faut entrer un nom ");
        }
        if (resdescription.equals("")) {
            System.out.println("il faut entrer une description");

        }
        if (labelsinglefile.getText().equals("URL:")) {
            System.out.println("il faut entrer un url");

        }

        if (((map.getEngine().getLocation().equals("https://www.google.com/maps/@35.8088704,10.6135552,14z")))
                || (map.getEngine().getLocation().equals("https://www.google.com/maps"))) {
            System.out.println("il faut entrer une localisation");

        }

        if (((labelsinglefile.getText().equals("URL:")) == false)
                && (((map.getEngine().getLocation()
                        .equals("https://www.google.com/maps/@35.8088704,10.6135552,14z")) == false)
                && ((map.getEngine().getLocation().equals("https://www.google.com/maps")) == false))
                && ((resdescription.equals("")) == false)
                && ((resNom.equals("")) == false)) {
            cinema c = new cinema(1, resNom, map.getEngine().getLocation(), resdescription, f.getAbsolutePath());
            System.out.println(c);

            CinemaCRUD PCD = new CinemaCRUD();
            PCD.addEntity(c);
            System.out.println("done !");
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
