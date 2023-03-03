/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.salle;
import edu.cinepro.entities.snack;
import edu.connexion3A18.services.SalleCRUD;
import edu.connexion3A18.services.SnackCRUD;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SnackController implements Initializable {

    private File f;
    @FXML
    private Button ftimage;
    @FXML
    private Label labelsinglefile;
    @FXML
    private TextField idnom;
    @FXML
    private TextField idprix;
    @FXML
    private TextField idquantite;
    @FXML
    private Button idbuttonsavesnack;
    @FXML
    private ImageView imageview;
    
     private static final SnackController instance = new SnackController();
    private int id;
public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static SnackController getInstance() {
        return instance;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                System.out.println(instance.id);
}

    @FXML
    private void importimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers image", "*.png", "*.jpg", "*.gif", "*.bmp"));
        f = fc.showOpenDialog(null);
        if (f != null) {
            labelsinglefile.setText("Select file" + f.getAbsolutePath());

            try {
                File file = new File(f.getAbsolutePath());

// --> file:/C:/MyImages/myphoto.jpg
                String localUrl = file.toURI().toURL().toString();
//imageid.setImage(new Image("C:\Users\rayen\Pictures\cinema.jpg"));
                imageview.setImage(new Image(localUrl));
            } catch (MalformedURLException ex) {
                System.out.println(ex.getMessage());
            };

            System.out.println(f.getAbsolutePath());
        }

    }

    @FXML
    private void savesnack(ActionEvent event) {
        boolean conditionnom = false;
        boolean conditionprix = false;
        boolean conditionQuantite = false;
        boolean conditionurl = false;

        String resNom = idnom.getText();
        String resPrix = idprix.getText();
        String resQuantite = idquantite.getText();
                float restPrixF = Integer.valueOf(resPrix);
                 int restquantiteF = Integer.valueOf(resQuantite);

        if (resNom.equals("")) {
            System.out.println("il faut entrer un nom");

        } else {
            conditionnom = true;
        }

        if (resPrix.equals("")) {
            System.out.println("il faut entrer prix");

        } else {
            try {
                System.out.println(restPrixF);
                
                if (restPrixF>0){
                conditionprix = true;
                }
            } catch (NumberFormatException ex) {
                System.out.println("il faut entrer un nombre valide ");
            }
        }

        if (resQuantite.equals("")) {
            System.out.println("il faut entrer la quantité");

        } else {
            try {
                 if (restquantiteF>0){
                conditionQuantite = true;
                }
                System.out.println(restquantiteF);
            } catch (NumberFormatException ex) {
                System.out.println("il faut entrer un nombre valide ");
            }
        }

        if (labelsinglefile.getText().equals("URL:")) {
            System.out.println("il faut entrer un url");

        } else {
            conditionurl = true;
        }

        if ((conditionQuantite) && (conditionnom) && (conditionprix) && (conditionurl)) {
            System.out.println("ok");
            snack cc = new snack(resNom, restPrixF, restquantiteF,labelsinglefile.getText() , instance.id);
           
            System.out.println(instance.id);
            SnackCRUD pc = new SnackCRUD();
            pc.addEntity(cc);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier    |o_O|");

            alert.showAndWait();
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

    @FXML
    private void csv(ActionEvent event) {
        
        try {
        AjoutsnackcsvController.getInstance().setId1(instance.id);

                Parent root = FXMLLoader.load(getClass().getResource("Ajoutsnackcsv.fxml"));
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        
    }

}
