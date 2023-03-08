/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import edu.cinepro.entities.cinema;
import edu.cinepro.entities.snack;
import edu.connexion3A18.services.CinemaCRUD;
import edu.connexion3A18.services.SnackCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AffichSnackController implements Initializable {

    @FXML
    private TableColumn<snack, Integer> id_snack;
    @FXML
    private TableColumn<snack, String> nom;
    @FXML
    private TableColumn<snack, Float> prix;
    @FXML
    private TableColumn<snack, Integer> quantite;
    @FXML
    private TableColumn btn;
    @FXML
    private TableView<snack> snack;
    public ObservableList<snack> k = FXCollections.observableArrayList();
    private static final AffichSnackController instance = new AffichSnackController();
    private int id;
    @FXML
    private TableColumn<snack, String> photo;
    @FXML
    private TableColumn updatebtn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static AffichSnackController getInstance() {
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Addnotif();
         snack.getItems().clear();
        SnackCRUD cd = new SnackCRUD();
        List<snack> liste = new ArrayList<snack>();
        liste = cd.entitiesList2(instance.id);
        for (snack i : liste) {
            k.add(i);
        }

        id_snack.setCellValueFactory(new PropertyValueFactory<snack, Integer>("id_snack"));
        photo.setCellValueFactory(new PropertyValueFactory<snack, String>("photo"));

        nom.setCellValueFactory(new PropertyValueFactory<snack, String>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<snack, Float>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<snack, Integer>("quantite"));

        snack.setItems(k);
        snack.getSelectionModel().select(2);

        Callback<TableColumn<snack, Void>, TableCell<snack, Void>> delete
                = new Callback<TableColumn<snack, Void>, TableCell<snack, Void>>() {
            @Override
            public TableCell<snack, Void> call(final TableColumn<snack, Void> param) {
                final TableCell<snack, Void> cell = new TableCell<snack, Void>() {
                    private final Button btn = new Button("supprimer ");

                    {
                        btn.setStyle("-fx-color: white;");

                        btn.setOnAction((ActionEvent event) -> {
                           


                            

                                //  ViewmorecinemaController dc = loader.getController();
                                int rowIndex = getTableRow().getIndex();
                                Integer idsnackValue = id_snack.getCellObservableValue(rowIndex).getValue();

                                deletesnack(idsnackValue);

                                System.out.println("ID snack : " + idsnackValue);

                                //     SnackCRUD cd = new SnackCRUD();
                                //    cd.deleteEntity(idsnackValue);
                            

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

        btn.setCellFactory(delete);
        
        
        
        
        
          Callback<TableColumn<cinema, Void>, TableCell<cinema, Void>> update
                = new Callback<TableColumn<cinema, Void>, TableCell<cinema, Void>>() {
            @Override
            public TableCell<cinema, Void> call(final TableColumn<cinema, Void> param) {
                final TableCell<cinema, Void> cell = new TableCell<cinema, Void>() {
                    private final Button btn = new Button("update ");

                    {
                        btn.setStyle("-fx-color: white;");

                        btn.setOnAction((ActionEvent event) -> {
                            /* 
    TableCell<cinema, String> cell = (TableCell<cinema, String>) event.getTarget();
    int rowIndex = cell.getIndex();
    System.out.println("Ligne cliquée : " + rowIndex);
                             */


                            int rowIndex = getTableRow().getIndex();// Code pour gérer l'action du bouton
                            //redirection
                            Integer idsnackValue = id_snack.getCellObservableValue(rowIndex).getValue();
                            CinemaCRUD cd = new CinemaCRUD();
                                                                      SnackupdateController.getInstance().setIdinstance(idsnackValue);
                      
                                try {
                                    Parent root = FXMLLoader.load(getClass().getResource("Snackupdate.fxml"));
                                    Scene scene = new Scene(root);
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException ex) {
                                    System.out.println(ex.getMessage());
                                }
                            
                                
                            
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

        this.updatebtn.setCellFactory(update);
    }
    
    

    public void Affiche() {

    }

    public void deletesnack(int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Snack");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this Snack ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == ButtonType.OK) {

            SnackCRUD cd = new SnackCRUD();
            System.out.println(id);
            cd.deleteEntity(id);
        }
        initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());
    }

    public void Addnotif() {
        String msg = "";
        String msg2 = "";

        SnackCRUD pwd = new SnackCRUD();

        List<snack> liste2 = pwd.entitiesListquantit5();

        List<snack> liste = pwd.entitiesListquantit0();
        System.out.println(liste);
        if (liste.size() > 0) {

            for (snack sn : liste) {
                msg = msg + "   " + sn.getNom();

            }
            Notifications notifications = Notifications.create();
            notifications.text("il n'y a plus beaucoup de quantité pour " + msg);
            notifications.title("alert");
            notifications.hideAfter(Duration.seconds(8));
            notifications.position(Pos.BASELINE_RIGHT);
            notifications.darkStyle();
            notifications.show();
        }
        if (liste2.size() > 0) {

            for (snack sn : liste2) {
                msg2 = msg2 + "   " + sn.getNom();

            }

            /*
            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("pad du snack");
                            alert.setHeaderText(null);
                            alert.setContentText("il n'ya pas du snack pour " +msg);
                           alert.showAndWait();

            */

            Notifications notifications = Notifications.create();
            notifications.text("il n'y a plus beaucoup de quantité pour " + msg2);
            notifications.title("alert");
            notifications.hideAfter(Duration.INDEFINITE);
            notifications.position(Pos.TOP_CENTER);
            notifications.show();
        }


    }

    @FXML
    private void back(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Home Page");
        alert.setHeaderText(null);
        alert.setContentText("voulez vous revenir à la page d'accueil ?       |o_O|");

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
}
