/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cinepro.GUI;
import cinepro.entities.Feedback;
import cinepro.services.FeedbackCRUD;
import java.awt.Insets;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class AfficherController implements Initializable {

    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, ?> id_userC;
    @FXML
    private TableColumn<?, ?> id_filmC;
    @FXML
    private TableColumn<?, ?> feedbackC;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnajouter;
    @FXML
    private Label id_user;
    @FXML
    private Label id_film;
    @FXML
    private Label feedback;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ajouter(ActionEvent event) {
         
        try
        {
             /*erreur.setText("");
        if (feedback.getText().isEmpty()) {
            erreur.setText("entrez feedback!");
        } else {*/
            

            try {

                String resFeedback = feedback.getText();

                int resId_user = Integer.valueOf(id_user.getText());
                int resId_film = Integer.valueOf(id_film.getText());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
                LocalDateTime now = LocalDateTime.now();
                String date = dtf.format(now);
                FeedbackCRUD pcd = new FeedbackCRUD();
                Feedback f = new Feedback(resFeedback, resId_user, resId_film, ("02/02/2022-14:00"));
                pcd.addCommentaire(f);
                System.out.println("Done!!");
          
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Feedback");
 
alert.setHeaderText("Feedback");
alert.setContentText(" Addedddd!");
 
alert.showAndWait();

            table();
            
            id_user.setText("");
            id_film.setText("");
           feedback.setText("");
             date.setText("");
            feedback.requestFocus();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AfficherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
      
    public void table(){
        MyConnection();
        
          ObservableList<Feedback> Feedback = FXCollections.observableArrayList();
       try
       {
           pst = con.prepareStatement("select * from feedback");  
           ResultSet rs = pst.executeQuery();
      {
        while (rs.next())
        {
            Feedback st = new Student();
            st.setId(rs.getString("id"));
            st.setName(rs.getString("name"));
            st.setMobile(rs.getString("mobile"));
            st.setCourse(rs.getString("course"));
            students.add(st);
       }
    }
    
    
    
    
    
    @FXML
    private void supprimer(ActionEvent event) {
        
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

    private void MyConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
