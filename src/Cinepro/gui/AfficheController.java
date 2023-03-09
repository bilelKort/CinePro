/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinerpo.entities.Abonnement;
import cinerpo.services.AbonnementCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Home
 */
public class AfficheController implements Initializable {

    @FXML
    private TableView<Abonnement> tableview;
    @FXML
    private TableColumn<Abonnement, Integer> ida;
    @FXML
    private TableColumn<Abonnement, Integer> idu;
    public ObservableList<Abonnement> k = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Abonnement, Integer> type;
    @FXML
    private TableColumn<Abonnement, Timestamp> dateD;
    @FXML
    private TableColumn<Abonnement, Timestamp> dateE;
    private VBox vbox;
    @FXML
    private ComboBox<String> cbox;
    @FXML
    private AnchorPane stat;
    @FXML
    private Button statbtn;

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
        cbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            filtrer();
        });
        

        AbonnementCRUD acd = new AbonnementCRUD();
        List<Abonnement> liste = new ArrayList<Abonnement>();
        liste = acd.entitiesList();
        for (Abonnement i : liste) {
            k.add(i);
        }

        ida.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        idu.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_user"));
        type.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("type"));
        dateD.setCellValueFactory(new PropertyValueFactory<Abonnement, Timestamp>("dateDebut"));
        dateE.setCellValueFactory(new PropertyValueFactory<Abonnement, Timestamp>("dateExpiration"));
        tableview.setItems(k);
        //
        cbox.getItems().addAll("1 mois", "3 mois", "6 mois", "1 an");
        cbox.setValue("1 mois");
        //vbox.getChildren().addAll(cbox);
        TableView<Abonnement> tableview = new TableView<>();
        //ObservableList<Abonnement> abs = FXCollections.observableArrayList(); // Initialisation de la liste des abonnements
        tableview.setItems(k);

        

        
        
    
    }

    public void filtrer(){
        FilteredList<Abonnement> absFiltres;
        absFiltres = k.filtered((Abonnement p) -> p.trans().equals(cbox.getValue()));
      
        tableview.setItems(absFiltres);
        cbox.valueProperty().addListener((observable, oldValue, newValue) -> {
            absFiltres.setPredicate(p -> p.trans().equals(newValue));
        });
        
    }
    
  
    
    @FXML
    private void stat(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));

        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) statbtn.getScene().getWindow();
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/SignIn.fxml"));

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
