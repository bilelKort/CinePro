/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import cinepro.utils.MyConnection;
import edu.cinepro.entities.User;
import edu.cinepro.entities.UserSession;
import edu.cinepro.services.CineproCRUD;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class AdminIndexController implements Initializable {

    private TextField affichageNom;
    @FXML
    private TableView<User> table_user;
    @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> pseudo;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> role;
    @FXML
    private TableColumn<User, String> dateN;
    @FXML
    private TableColumn<User, Integer> tel;
    @FXML
    private TableColumn<User, String> nom;
    
    private ObservableList<User> data;
    @FXML
    private Button delete;
    @FXML
    private TextField searchNom;
    @FXML
    private Button search;
    @FXML
    private ImageView image5;
    

    CineproCRUD ccd = new CineproCRUD();
    @FXML
    private Button Logout;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table_user.getItems().clear();
        searchNom.textProperty().addListener((observable, oldValue, newValue) -> {
            Search(searchNom.getText());
            show();
        });
        File file = new File("src/edu/cinepro/gui/images/image5.jpg");
        String localURL = "";
        try {
            localURL = file.toURI().toURL().toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        image5.setImage(new Image(localURL));
        
        
        InitUser();
        show();
        
        // TODO
    }    


    public void InitUser() {
        
        data = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM user";
            Statement st =  MyConnection.getInstance().getConnection().createStatement();
            
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            
                User c =new User();
                c.setId_user(rs.getInt(1));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setDate_naissance(rs.getString("date_naissance"));
                c.setPseudo(rs.getString("pseudo"));
                c.setTel(rs.getInt("tel"));
                c.setRole(rs.getString("role"));
                c.setMontant(rs.getFloat("montant"));
                
                data.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void Search(String name) {
        
        data = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM user WHERE nom like ?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, "%"+name+"%");
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            
                User c =new User();
                c.setId_user(rs.getInt(1));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setDate_naissance(rs.getString("date_naissance"));
                c.setPseudo(rs.getString("pseudo"));
                c.setTel(rs.getInt("tel"));
                c.setRole(rs.getString("role"));
                c.setMontant(rs.getFloat("montant"));
                
                data.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     
    public void show(){
    
     
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id_user"));
        pseudo.setCellValueFactory(new PropertyValueFactory<User,String>("pseudo"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        role.setCellValueFactory(new PropertyValueFactory<User,String>("role"));
        dateN.setCellValueFactory(new PropertyValueFactory<User,String>("date_naissance"));
        tel.setCellValueFactory(new PropertyValueFactory<User,Integer>("tel"));
       
        table_user.setItems(data); 
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        
        int selectedIndex = table_user.getSelectionModel().getSelectedIndex();
        User c = (User) table_user.getSelectionModel().getSelectedItem();
        int tempItemTag = c.getId_user();
        
        if (selectedIndex >=0 ) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getId_user()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.YES) {
            
                try {
                    String requete="DELETE FROM user WHERE id_user=?";
                    PreparedStatement st=MyConnection.getInstance().getConnection().prepareStatement(requete);
            
                    st.setInt(1, tempItemTag);
                    st.executeUpdate();
                    table_user.getItems().remove(selectedIndex);
                    } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    }
                Notifications notifications = Notifications.create();
                // notifications.graphic(new ImageView(notif));
                notifications.text("Deleted successfully!");
                notifications.title("Success message");
                notifications.hideAfter(Duration.seconds(4));
                notifications.position(Pos.BOTTOM_LEFT);
                //notifications.darkStyle();
                notifications.show();
            }
        }
    }

    @FXML
    private void updateRole() {
        int selectedIndex = table_user.getSelectionModel().getSelectedIndex();
        User c = (User) table_user.getSelectionModel().getSelectedItem();
        int tempItemTag = c.getId_user();

        String newRole="";
        String oldRole=c.getRole();;
        if (oldRole.equals("Client")) {
            newRole="Gerant";
        }else if (oldRole.equals("Gerant")) {
            newRole="Client";
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Can't delete Admin !");
            alert.showAndWait();
            return;
        }

        if (selectedIndex >=0 ) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update " + oldRole+ " into " + newRole + " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                CineproCRUD cineproCRUD = new CineproCRUD();
                cineproCRUD.changeRole(tempItemTag, newRole);

                initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());

                Notifications notifications = Notifications.create();
                // notifications.graphic(new ImageView(notif));
                notifications.text("Updated successfully!");
                notifications.title("Success message");
                notifications.hideAfter(Duration.seconds(4));
                notifications.position(Pos.BOTTOM_LEFT);
                //notifications.darkStyle();
                notifications.show();
            }
        }
    }

    @FXML
    private void searchUser(ActionEvent event) {
        String nomRecherche = searchNom.getText();
        
        if (nomRecherche.equals("")) {
        
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("You have to enter a name!");
            alert.showAndWait();
            
            InitUser();
            show();
            
        } else {
            Search(nomRecherche);
            show();
            
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
            Stage myWindow = (Stage) search.getScene().getWindow();
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
            search.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));

        try {
            Parent root = loader.load();
            search.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}




