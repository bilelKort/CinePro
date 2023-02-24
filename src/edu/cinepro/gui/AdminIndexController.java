/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.gui;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.entities.UserSession;
import edu.cinepro.services.CineproCRUD;
import edu.cinepro.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author MOEµNESS
 */
public class AdminIndexController implements Initializable {

    @FXML
    private TextField affichageNom;
    @FXML
    private TableView<Cinepro> table_user;
    @FXML
    private TableColumn<Cinepro, Integer> id;
    @FXML
    private TableColumn<Cinepro, String> pseudo;
    @FXML
    private TableColumn<Cinepro, String> email;
    @FXML
    private TableColumn<Cinepro, String> role;
    @FXML
    private TableColumn<Cinepro, String> dateN;
    @FXML
    private TableColumn<Cinepro, Integer> tel;
    
    private ObservableList<Cinepro> data;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InitUser();
        show();
        
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) {
        UserSession.getInstace().cleanUserSession();
        //System.out.println(UserSession.getInstace().getId());
       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Account");
            alert.setHeaderText(null);
            alert.setContentText("You have logged out successfully!");
            alert.showAndWait();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        
        try {
            Parent root = loader.load();
            affichageNom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void InitUser() {
        
        data = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM user";
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            
                Cinepro c =new Cinepro();
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
    
     
        
        id.setCellValueFactory(new PropertyValueFactory<Cinepro,Integer>("id_user"));
        pseudo.setCellValueFactory(new PropertyValueFactory<Cinepro,String>("pseudo"));
        email.setCellValueFactory(new PropertyValueFactory<Cinepro,String>("email"));
        role.setCellValueFactory(new PropertyValueFactory<Cinepro,String>("role"));
        dateN.setCellValueFactory(new PropertyValueFactory<Cinepro,String>("date_naissance"));
        tel.setCellValueFactory(new PropertyValueFactory<Cinepro,Integer>("tel"));
       
        table_user.setItems(data); 
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        
        int selectedIndex = table_user.getSelectionModel().getSelectedIndex();
        Cinepro c = (Cinepro) table_user.getSelectionModel().getSelectedItem();
        int tempItemTag = c.getId_user();
        
        if (selectedIndex >=0 ) {
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getId_user()+ " ?", ButtonType.YES, ButtonType.CANCEL);
            alert.showAndWait();
            
            if (alert.getResult() == ButtonType.YES) {
            
                try {
                    String requete="DELETE FROM user WHERE id_user=?";
                    PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            
                    st.setInt(1, tempItemTag);
                    st.executeUpdate();
                    table_user.getItems().remove(selectedIndex);
                    } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    }
            }
        }
    }
    
}




