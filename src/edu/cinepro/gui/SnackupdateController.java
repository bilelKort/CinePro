/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.snack;
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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class SnackupdateController implements Initializable {
 private static final SnackupdateController instance = new SnackupdateController();
    private int idinstance;
    @FXML
    private Button Logout;
    @FXML
    private TextField idquantite;
    @FXML
    private TextField idprix;
    @FXML
    private TextField idnom;
    @FXML
    private Label labelsinglefile;
  private File f;
    @FXML
    private ImageView imageview;

    public int getIdinstance() {
        return idinstance;
    }

    public void setIdinstance(int idinstance) {
        this.idinstance = idinstance;
    }
     public static SnackupdateController getInstance() {
        return instance;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }
        System.out.println(instance.idinstance);
        affiche();
    }    
    
    
    
    public void affiche(){
            SnackCRUD cd = new SnackCRUD();
snack b =cd.entitiesList3(instance.idinstance);
        System.out.println(b);
        idquantite.setText(Integer.toString(b.getQuantite()));
    idnom.setText(b.getNom());
            idprix.setText(Float.toString(b.getPrix()));
            
labelsinglefile.setText(b.getPhoto());
    }

    @FXML
    private void updatesnack(ActionEvent event) {
             boolean conditionnom = false;
        boolean conditionprix = false;
        boolean conditionQuantite = false;
        boolean conditionurl = false;
        
        try {
            
        if (idnom.getText().equals("")) {
            System.out.println("il faut entrer un nom");
                    throw new Exception("il faut entrer un nom");

        } else {
            conditionnom = true;
        }
        if (idprix.getText().equals("")) {
            throw new Exception("il faut entrer prix");

        } else {
            
                 String resPrix = idprix.getText();
                 try {
                 float restPrixF = Float.valueOf(resPrix);
                        System.out.println(restPrixF);

                if (restPrixF>0){
                    conditionprix = true;
                }
                else{
                    throw new Exception("il faut entrer prix valide");

                }
            } catch (Exception e) {
                                    throw new Exception("il faut entrer prix valide sans caractére");

            }
       
                
           
        }
        if (idquantite.getText().equals("")) {
            throw new Exception("il faut entrer la quantité");

        } else {
            try {
                String resQuantite = idquantite.getText();
        int restquantiteF = Integer.valueOf(resQuantite);
                if (restquantiteF>0){
                    conditionQuantite = true;
                }
                else {
            throw new Exception("il faut entrer une quantité valide");

                        }
            } catch (Exception e) {
                            throw new Exception("il faut entrer une quantité valide ");

            }
            
                
        }
        if (labelsinglefile.getText().equals("URL:")) {
           throw new Exception("il faut choisir une image");

        } else {
            conditionurl = true;
        }
        
        String resNom = idnom.getText();
        String resPrix = idprix.getText();
        String resQuantite = idquantite.getText();
        float restPrixF = Float.valueOf(resPrix);
        int restquantiteF = Integer.valueOf(resQuantite);
        if ((conditionQuantite) && (conditionnom) && (conditionprix) && (conditionurl)) {
            System.out.println("ok");
            snack cc = new snack(resNom, restPrixF, restquantiteF,labelsinglefile.getText() );
           
            System.out.println(instance.idinstance);
            SnackCRUD pc = new SnackCRUD();
            pc.updateEntity(instance.idinstance,cc);
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText("Verifier    |o_O|");

            alert.showAndWait();
        }
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setHeaderText(null);
            alert.setContentText(" il y'a un probléme il faut verrifier vos champs !   |o_O| \n"+e.getMessage());

            alert.showAndWait();
        }
        
        
        
    }

    @FXML
    private void ftimage(ActionEvent event) {
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
labelsinglefile.setText(f.getAbsolutePath());

        }
    }

    @FXML
    private void logout(ActionEvent event) {
        UserSession.getInstace().cleanUserSession();
        //System.out.println(UserSession.getInstace().getId());

           /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account");
            alert.setHeaderText(null);
            alert.setContentText("You have logged out successfully!");
            alert.showAndWait(); */
        Notifications notifications = Notifications.create();
        // notifications.graphic(new ImageView(notif));
        notifications.text("You have logged out successfully!");
        notifications.title("Success message");
        notifications.hideAfter(Duration.seconds(4));
        notifications.position(Pos.BOTTOM_LEFT);
        //notifications.darkStyle();
        notifications.show();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));

        try {
            Parent root = loader.load();
            Stage myWindow = (Stage) Logout.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("Sign In");
            myWindow.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void film(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/SearchMovies.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void profile(ActionEvent actionEvent) {
        FXMLLoader loader= new FXMLLoader();
        if (UserSession.getInstace().getId()==0) {
            loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/SignIn.fxml"));
        }else {
            if (UserSession.getInstace().getRole().equals("Client")) {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/Index.fxml"));

            }else if (UserSession.getInstace().getRole().equals("Admin")) {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/AdminIndex.fxml"));
            }else {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/GerantIndex.fxml"));

            }
        }

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
