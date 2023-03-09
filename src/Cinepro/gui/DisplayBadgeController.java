/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.entities.Badge;
import cinerpo.services.BadgeCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.cinepro.entities.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class DisplayBadgeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Badge> tableview;
    @FXML
    private TableColumn<Badge, Integer> idb;
    @FXML
    private TableColumn<Badge, String> type;
    @FXML
    private TableColumn<Badge, Integer> nb;
    @FXML
    private TableColumn<Badge, Integer> idu;
    public ObservableList<Badge> k = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> cbox;
    private Button stat;
    @FXML
    private Button bc;


    //private Button idbtn;
    /**
     * Initializes the controller class.
     */
    @FXML
    private Button Logout;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }


        BadgeCRUD bcd = new BadgeCRUD();
        List<Badge> liste = new ArrayList<Badge>();
        liste = bcd.entitiesList();
        for (Badge i : liste) {
            k.add(i);
        }

        idb.setCellValueFactory(new PropertyValueFactory<Badge, Integer>("id_badge"));
        type.setCellValueFactory(new PropertyValueFactory<Badge, String>("type"));
        nb.setCellValueFactory(new PropertyValueFactory<Badge, Integer>("nbr_reservation"));
        tableview.setItems(k);
        cbox.getItems().addAll("gold", "silver", "bronze");
        cbox.setValue("Bronze");
        //vbox.getChildren().addAll(cbox);
        TableView<Badge> tableview = new TableView<>();
        //ObservableList<Abonnement> abs = FXCollections.observableArrayList(); // Initialisation de la liste des abonnements
        tableview.setItems(k);
        // TODO


        cbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrer();
        });
    }

    public void filtrer(){
        FilteredList<Badge> bFiltres;
        bFiltres = k.filtered((Badge p) -> p.trans().equals(cbox.getValue()));
      
        tableview.setItems(bFiltres);
        cbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            bFiltres.setPredicate(p -> p.trans().equals(newValue));
        });
        
        
    }
    
  
    
   

        

    @FXML
    private void stat(ActionEvent event) {
        
    FXMLLoader loader = new FXMLLoader(getClass().getResource("statb.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) bc.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
