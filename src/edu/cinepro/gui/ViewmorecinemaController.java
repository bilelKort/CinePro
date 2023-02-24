/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.cinema;
import edu.cinepro.entities.salle;
import edu.connexion3A18.services.CinemaCRUD;
import edu.connexion3A18.services.SalleCRUD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ViewmorecinemaController implements Initializable {

    @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private WebView map;
    @FXML
    private ImageView image;
    @FXML
    private ListView<salle> idsalle;
    private GridPane idplace;
    @FXML
    private Label longeur;
    @FXML
    private Label largeur;

    /*public static ViewmorecinemaController getInstance() {
        return instance;
    }*/
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
            System.out.println(ex.getMessage());        };
    }

    public Label getNom() {
        return nom;
    }

    public void setNom(String nom1) {
        this.nom.setText(nom1);
    }

    public WebView getMap() {
        return map;
    }

    public void setMap(String urlmap) {
        map.getEngine().load(urlmap);
        map.setDisable(true);
        map.setCursor(Cursor.NONE);

    }

    public Label getId() {
        return id;
        // TODO
    }

    public GridPane getIdplace() {
        return idplace;
    }

    public void setIdplace(GridPane idplace) {
        this.idplace = idplace;
    }

    /**
     * Initializes the controller class.
     */
    public void setId(String i) {
        this.id.setText(i);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

       
    }

    @FXML
    private void affichesalle(ActionEvent event) {

        SalleCRUD cd = new SalleCRUD();
        int id2 = Integer.valueOf(id.getText());
        List<salle> b = cd.entitiesList2(id2);
        System.out.println(b.size());  
              
        if (b.size()==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("empty");
            alert.setHeaderText(null);
            alert.setContentText(" vide      >.< ");
            alert.showAndWait();
        } else {

              for (salle i : b) {
            idsalle.getItems().add(i);
            

        }
              

            idsalle.setOnMouseClicked(event2 -> {
                  try {
                      /*
                      int  index = idsalle.getSelectionModel().getSelectedIndex();
                      
                      if (index > -1) {
                      
                      Integer getLargeur = idsalle.getItems().get(index).getLargeur();
                      Integer getLongueur = idsalle.getItems().get(index).getLongueur();
                      idplace.getChildren().clear();
                      idplace.setHgap(10);
                      idplace.setVgap(10);
                      longeur.setText(Integer.toString(getLongueur));
                      largeur.setText(Integer.toString(getLargeur));
                      
                      if (idsalle.getItems().get(index).isAcces()) {
                      for (int row = 0; row < getLargeur; row++) {
                      for (int col = 0; col < getLongueur; col++) {
                      
                      Pane pane = new Pane();
                      Rectangle rectangle = new Rectangle(30, 30);
                      rectangle.setFill(Color.WHITE);
                      rectangle.setStroke(Color.BLACK);
                      
                      pane.getChildren().add(rectangle);
                      
                      idplace.add(pane, col, row);
                      }
                      }
                      } else {
                      for (int row = 0; row < getLargeur; row++) {
                      for (int col = 0; col < getLongueur; col++) {
                      
                      Pane pane = new Pane();
                      Rectangle rectangle = new Rectangle(30, 30);
                      rectangle.setFill(Color.DARKRED);
                      rectangle.setStroke(Color.BLACK);
                      
                      pane.getChildren().add(rectangle);
                      
                      idplace.add(pane, col, row);
                      }
                      }
                      }
                      
                      }
                      */
                      
                      int  index = idsalle.getSelectionModel().getSelectedIndex();
                      Integer idsalleValue = idsalle.getItems().get(index).getId_salle();
                    SalleController.getInstance().setId(idsalleValue);
                      Parent root = FXMLLoader.load(getClass().getResource("salle.fxml"));
                      Scene scene = new Scene(root);
                      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      stage.setScene(scene);
                      stage.show();
                  } catch (IOException ex) {
                      Logger.getLogger(ViewmorecinemaController.class.getName()).log(Level.SEVERE, null, ex);
                  }

            });

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
                System.out.println(ex.getMessage());            }
        }
    }

 

    @FXML
    private void ajouterunesalle(ActionEvent event) {
        int id2 = Integer.valueOf(id.getText());
        System.out.println("aaaaaaaaa"+id2);
  try{
   AjoutersalleController.getInstance().setId(id2);
                      Parent root = FXMLLoader.load(getClass().getResource("ajoutersalle.fxml"));
                      Scene scene = new Scene(root);
                      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      stage.setScene(scene);
                      stage.show();
                  } catch (IOException ex) {
                      Logger.getLogger(ViewmorecinemaController.class.getName()).log(Level.SEVERE, null, ex);
                  }
  
  
    
    }

    @FXML
    private void ajoutersnack(ActionEvent event) {
         try{
                      Parent root = FXMLLoader.load(getClass().getResource("snack.fxml"));
                      Scene scene = new Scene(root);
                      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      stage.setScene(scene);
                      stage.show();
                  } catch (IOException ex) {
                      Logger.getLogger(ViewmorecinemaController.class.getName()).log(Level.SEVERE, null, ex);
                  }
        
    }

}




/*button


              idsalle.setCellFactory(new Callback<ListView<salle>, ListCell<salle>>() {
    @Override
    public ListCell<salle> call(ListView<salle> listView) {
        return new ListCell<salle>() {
            private final Button button = new Button("Click Me");
            {
                button.setOnAction(event -> {
                    salle item = getItem();
                    // handle button click for this item
                });
            }

            @Override
            protected void updateItem(salle item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setGraphic(button);
                }
            }
        };
    }
});


*/
