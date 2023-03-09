/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.cinepro.gui;

import com.opencsv.CSVReader;
import edu.cinepro.entities.UserSession;
import edu.cinepro.entities.snack;
import edu.connexion3A18.services.SnackCRUD;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AjoutsnackcsvController implements Initializable {

    @FXML
    private Button idbtn;

    @FXML
    private TableColumn<snack, String> nom;
    @FXML
    private TableColumn<snack, Float> prix;
    @FXML
    private TableColumn<snack, Integer> quantite;
    @FXML
    private TableColumn<snack, String> photo;
    @FXML
    private TableView<snack> snack;
    public ObservableList<snack> k = FXCollections.observableArrayList();
    private List<snack> data1 = new ArrayList<>();
    private String ereur = "";
    @FXML
    private Label veriff;
    private static final AjoutsnackcsvController instance = new AjoutsnackcsvController();
    private int id1;
boolean err=false;
    @FXML
    private Button Logout;
    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public static AjoutsnackcsvController getInstance() {
        return instance;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void load(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select CSV file");

        // Show FileChooser dialog
        File selectedFile = fileChooser.showOpenDialog(idbtn.getScene().getWindow());

        if (selectedFile != null) {
            // Load CSV data
            List<String[]> data = loadDataFromCsv(selectedFile);

            // Flatten data into a single list
            List<String> flatData = new ArrayList<>();
            for (String[] row : data) {

                flatData.addAll(Arrays.asList(row));
            }
            List<String[]> separatedTable = new ArrayList<>();
            for (String row : flatData) {
                String[] rowValues = row.split(";");

                separatedTable.add(rowValues);
            }
            boolean firstLine = true;

            // Affichage des éléments de la liste de tableaux
            try {
                for (String[] row : separatedTable) {

                    if (firstLine) {
                        firstLine = false;
                        continue; // skip first line
                    }
                    System.out.println("affich" + Arrays.toString(row));
                    if ((row[0].equals(""))|| (row[1].equals("")) || (row[2].equals("")) || (row[3].equals(""))
                            
                            ) {
                        System.out.println("dddd");
data1.clear();
err=true;
                        break;
                    } else {
                        String nom = row[0];
                        float prix = Float.parseFloat(row[1]);
                        int quantite = Integer.parseInt(row[2]);
                        String photo = row[3];
                        snack sc = new snack(nom, prix, quantite, photo, instance.id1);
                        data1.add(sc);
                        err=false;
                    }
                }

            } catch (Exception e) {
                data1.clear();
err=true;
            }
            for (snack i : data1) {
                k.add(i);
            }
            System.out.println(data1);
            this.affiche();        // Set ListView data
            /*  ObservableList<String> observableData = FXCollections.observableArrayList(flatData);
                listView.setItems(observableData);*/
        }

    }

    private void affiche() {
        photo.setCellValueFactory(new PropertyValueFactory<snack, String>("photo"));

        nom.setCellValueFactory(new PropertyValueFactory<snack, String>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<snack, Float>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<snack, Integer>("quantite"));

        snack.setItems(k);
if(err){
                            veriff.setText("verifier les champs du snack");

    
    
}
else {veriff.setText("");}
    }

    private List<String[]> loadDataFromCsv(File file) {
        List<String[]> data = new ArrayList<>();

        try ( CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            while ((line = reader.readNext()) != null) {

                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @FXML
    private void save(ActionEvent event) {
        System.out.println(data1);
        SnackCRUD pc = new SnackCRUD();

        for (snack i : data1) {
            System.out.println(i);
            pc.addEntitycsv(i);
        }
        affiche();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AfficherReclamation.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void cinema(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/CinemaAffiche.fxml"));

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
