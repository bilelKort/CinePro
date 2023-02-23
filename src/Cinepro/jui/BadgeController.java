/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cinepro.jui;

import cinerpo.entities.Badge;
import cinerpo.entities.Type_Badge;
import cinerpo.services.BadgeCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Home
 */
enum Type {
        Gold,Silver,Bronze
    }

public class BadgeController implements Initializable {

    @FXML
    private Label type;
    @FXML
    private Label idBadge;
    @FXML
    private VBox vbox;
    @FXML
    private ChoiceBox<Type> cbox;
    @FXML
    private TextField idUser;
    @FXML
    private Button addBadge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add the enum values to the ChoiceBox
        cbox.getItems().addAll(Type.values());

        // Set a default value for the ChoiceBox
        cbox.setValue(Type.Gold);

        // Create a layout for the ChoiceBox
        vbox.getChildren().addAll(cbox);
        // TODO
    }    

    @FXML
    private void addBadge(ActionEvent event) {
        Type type= cbox.getValue();
        BadgeCRUD acd= new BadgeCRUD();
        Badge b=new Badge();
        
        switch(type){
                case Gold :
                    b.setType(Type_Badge.gold);
                    b.setNbr_reservation(100);
                    break;
                case Silver:
                    b.setType(Type_Badge.silver);
                    b.setNbr_reservation(50);
                    break;
                case Bronze:
                    b.setType(Type_Badge.bronze);
                    b.setNbr_reservation(10);
                    break;
 
        }
        String idu= idUser.getText();
        b.setId_user(Integer.parseInt(idu)); 
        acd.addEntity(b);
}
    
    
}
