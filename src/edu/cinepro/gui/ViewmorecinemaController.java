/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.cinema;
import edu.cinepro.entities.salle;
import edu.cinepro.entities.snack;
import edu.connexion3A18.services.CinemaCRUD;
import edu.connexion3A18.services.SalleCRUD;
import edu.connexion3A18.services.SnackCRUD;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ViewmorecinemaController implements Initializable {
 private static final ViewmorecinemaController instance = new ViewmorecinemaController();
    private int id1;
    @FXML
    private Label id;
    @FXML
    private Label nom;
    @FXML
    private WebView map;
    @FXML
    private ImageView image;
  /*  @FXML
    private ListView<salle> idsalle;*/
    private GridPane idplace;
    @FXML
    private Label longeur;
    @FXML
    private Label largeur;
    @FXML
    private TableColumn<salle, Boolean> acces;
    @FXML
    private TableColumn delete;
    @FXML
    private TableColumn<salle, String> name;
    @FXML
    private TableColumn<salle, Integer> idsal;
    @FXML
    private TableView<salle> tableview;
        public ObservableList<salle> k = FXCollections.observableArrayList();
    @FXML
    private TableColumn edit;
    @FXML
    private Button Logout;

    
     
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
    
    
    
    
    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }
    
      public static ViewmorecinemaController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("nnnn"+instance.id1);
        affichesalle();
        CinemaCRUD c =new CinemaCRUD();
       cinema a= c.cinemabyid(instance.id1);
      this.setMap(a.getLocalisation());

                          this.setNom(a.getNom());
                        this.setImage(a.getPhoto());
    }

    private void affichesalle() {
        
         tableview .getItems().clear();
        SalleCRUD cd = new SalleCRUD();
        List<salle> liste = new ArrayList<salle>();
        liste = cd.entitiesList2(instance.id1);
        for (salle i : liste) {
            k.add(i);
        }
                idsal.setCellValueFactory(new PropertyValueFactory<salle, Integer>("id_salle"));

        name.setCellValueFactory(new PropertyValueFactory<salle, String>("nom"));
        acces.setCellValueFactory(new PropertyValueFactory<salle,Boolean>("acces"));
        
          tableview.setItems(k);
        tableview.getSelectionModel().select(2);
        
        
Callback<TableColumn<snack, Void>, TableCell<snack, Void>> cellFactory
                = new Callback<TableColumn<snack, Void>, TableCell<snack, Void>>() {
            @Override
            public TableCell<snack, Void> call(final TableColumn<snack, Void> param) {
                final TableCell<snack, Void> cell = new TableCell<snack, Void>() {
                    private final Button btn = new Button("supprimer ");

                    {
                        btn.setStyle("-fx-color: white;");

                        btn.setOnAction((ActionEvent event) -> {
                            /* 
    TableCell<cinema, String> cell = (TableCell<cinema, String>) event.getTarget();
    int rowIndex = cell.getIndex();
    System.out.println("Ligne cliquée : " + rowIndex);
                             */


                            

                                int rowIndex = getTableRow().getIndex();
                                Integer idsalleValue = idsal.getCellObservableValue(rowIndex).getValue();
deletesalle(idsalleValue);

                                System.out.println("ID salle : " + idsalleValue);

                            

// Code pour gérer l'action du bouton
                            //redirection
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        delete.setCellFactory(cellFactory);
        
        
           Callback<TableColumn<cinema, Void>, TableCell<cinema, Void>> seesalle
                = new Callback<TableColumn<cinema, Void>, TableCell<cinema, Void>>() {
            @Override
            public TableCell<cinema, Void> call(final TableColumn<cinema, Void> param) {
                final TableCell<cinema, Void> cell = new TableCell<cinema, Void>() {
                    private final Button btn = new Button("see more ");

                    {
                        btn.setStyle("-fx-color: white;");

                        btn.setOnAction((ActionEvent event) -> {
                            /* 
    TableCell<cinema, String> cell = (TableCell<cinema, String>) event.getTarget();
    int rowIndex = cell.getIndex();
    System.out.println("Ligne cliquée : " + rowIndex);
                             */
                                int rowIndex = getTableRow().getIndex();
                                Integer idsalleValue = idsal.getCellObservableValue(rowIndex).getValue();
                                SalleController.getInstance().setId(idsalleValue);

                            FXMLLoader loader = new FXMLLoader(getClass().getResource("salle.fxml"));

                            try {
                                Parent root = loader.load();


                                
                                btn.getScene().setRoot(root);

                            } catch (IOException ex) {
                                System.err.println(ex.getMessage());
                            }

// Code pour gérer l'action du bouton
                            //redirection
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        edit.setCellFactory(seesalle);


                
    }
public void deletesalle(int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete salle");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this salle ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == ButtonType.OK) {

            SalleCRUD cd = new SalleCRUD();
            System.out.println(id);
            cd.deleteEntity2(id);
        }
        initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());
    }
    @FXML
    private void back(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("annuler ?");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous annuler ?  |o_O|");

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
        System.out.println("aaaaaaaaa"+instance.id1);
  try{
   AjoutersalleController.getInstance().setId(instance.id1);
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
             
   SnackController.getInstance().setId(instance.id1);

                      Parent root = FXMLLoader.load(getClass().getResource("snack.fxml"));
                      Scene scene = new Scene(root);
                      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      stage.setScene(scene);
                      stage.show();
                  } catch (IOException ex) {
                      Logger.getLogger(ViewmorecinemaController.class.getName()).log(Level.SEVERE, null, ex);
                  }
        
    }

    @FXML
    private void affichsnacks(ActionEvent event) {
     try{
             
   AffichSnackController.getInstance().setId(instance.id1);

                      Parent root = FXMLLoader.load(getClass().getResource("AffichSnack.fxml"));
                      Scene scene = new Scene(root);
                      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                      stage.setScene(scene);
                      stage.show();
                  } catch (IOException ex) {
                      Logger.getLogger(ViewmorecinemaController.class.getName()).log(Level.SEVERE, null, ex);
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
