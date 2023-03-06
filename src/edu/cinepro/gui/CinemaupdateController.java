/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.cinema;
import edu.connexion3A18.services.CinemaCRUD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class CinemaupdateController implements Initializable {

    private File f;

    @FXML
    private TextField ftdescription;
    @FXML
    private WebView map;
    @FXML
    private TextField ftnom;
    @FXML
    private Label labelsinglefile;
    private static final CinemaupdateController instance = new CinemaupdateController();
    private int id;
    private String erreurnom;
    private String erreurdescription;

    private String erreurmap;
    private String erreurphoto;

    public static CinemaupdateController getInstance() {
        return instance;
    }
    @FXML
    private ImageView image;

    public int getId() {
        return id;
        // TODO
    }

    /**
     * Initializes the controller class.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(instance.id);
        this.affiche();
    }

    @FXML
    private void importimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif", "*.bmp"));
        f = fc.showOpenDialog(null);
        if (f != null) {
            labelsinglefile.setText( f.getAbsolutePath());
            System.out.println(f.getAbsolutePath());
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
                System.out.println(ex.getMessage());
            }
        }
    }

    public void affiche() {
        CinemaCRUD cinema = new CinemaCRUD();
        cinema c = cinema.cinemabyid(instance.id);
 labelsinglefile.setText("Select file" + c.getPhoto());
        ftnom.setText(c.getNom());
        System.out.println(c.getNom());
        ftdescription.setText(c.getDescription());
        map.getEngine().load(c.getLocalisation());
        try {
            File file = new File(c.getPhoto());

// --> file:/C:/MyImages/myphoto.jpg
            String localUrl = file.toURI().toURL().toString();
//imageid.setImage(new Image("C:\Users\rayen\Pictures\cinema.jpg"));
            image.setImage(new Image(localUrl));
        } catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());

        };
    }

    @FXML
    private void update(ActionEvent event) {

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
            erreurnom = "il faut entrer un nom !! ";

        } else {
            nom = true;
            erreurnom = "";
        }
        if (resdescription.equals("")) {
            erreurdescription = "il faut entrer une description";

        } else {
            description = true;

            erreurdescription = "";

        }
        if (labelsinglefile.getText().equals("")) {
            erreurphoto = "il faut entrer un url";

        } else {
            url = true;
            erreurphoto = "";

        }
        if (((map.getEngine().getLocation().equals("https://www.google.com/maps/@36.798464,10.1679104,14z")))
                || (map.getEngine().getLocation().equals("https://www.google.com/maps"))) {
            erreurmap = "il faut entrer une localisation !!!";

        } else {
            mapb = true;
            erreurmap = "";

        }
        if ((mapb == false)
                || (url == false)
                || (description == false) || (nom == false)) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("noo");
            alert1.setHeaderText(null);
            alert1.setContentText("impossible verifier " + erreurnom + "  " + erreurdescription + "  " + erreurmap + "  " + erreurphoto + "  ");
            alert1.showAndWait();

        }
        if ((mapb == true)
                && (url == true)
                && (description == true) && (nom == true)) {
            cinema c = new cinema(1, resNom, map.getEngine().getLocation(), resdescription, labelsinglefile.getText());

            CinemaCRUD PCD = new CinemaCRUD();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("  >.<  VERIF");
            alert.setHeaderText(null);
            alert.setContentText("dooneee   (^_^)");

            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    PCD.updateEntity(instance.id, c);
                    System.out.println("done !");
                    Parent root = FXMLLoader.load(getClass().getResource("CinemaAffiche.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
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

}
