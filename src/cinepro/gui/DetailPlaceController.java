/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.reservation;
import cinepro.entities.reservation_place;
import cinepro.services.reservationCRUD;
import cinepro.services.reservation_placeCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class DetailPlaceController implements Initializable {
  @FXML
    private TableColumn<reservation_place, Integer> col1;
    @FXML
    private TableColumn<reservation_place, String> col2;
    @FXML
    private TableColumn<reservation_place, Float> col3;
    @FXML
    private TableColumn<reservation_place, Integer> col4;  
    @FXML
    private TableView<reservation_place> tableview;
    public   ObservableList<reservation_place> data = FXCollections.observableArrayList();
    @FXML
    private Button btnres;
    @FXML
    private Button btnressnack;
    @FXML
    private TableColumn<reservation_place, Void> delete;
    @FXML
    private Button Menu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         reservation_placeCRUD rs = new reservation_placeCRUD();
       List<reservation_place> ls = new ArrayList<reservation_place>();
       ls=rs.entitiesList();
    for(reservation_place i:ls){
        data.add(i);
    }
    
    col1.setCellValueFactory(new PropertyValueFactory<reservation_place, Integer>("id_res_place")); 
    col2.setCellValueFactory(new PropertyValueFactory<reservation_place, String>("coordonnee"));
    col3.setCellValueFactory(new PropertyValueFactory<reservation_place, Float>("prix"));
    col4.setCellValueFactory(new PropertyValueFactory<reservation_place, Integer>("id_reservation"));

    tableview.setItems(data);
    
    
    Callback<TableColumn<reservation_place, Void>, TableCell<reservation_place, Void>> cellFactory = new Callback<TableColumn<reservation_place, Void>, TableCell<reservation_place, Void>>() {
            @Override
            public TableCell<reservation_place, Void> call(final TableColumn<reservation_place, Void> param) {
                final TableCell<reservation_place, Void> cell = new TableCell<reservation_place, Void>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int rowIndex = getTableRow().getIndex();
                            reservation_placeCRUD pcd = new reservation_placeCRUD();
                            pcd.deleteEntity(col1.getCellObservableValue(rowIndex).getValue());

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
    }    
    
    @FXML
    public void showresTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Display.fxml"));
       try{
       Parent root = loader.load(); 

        btnres.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    @FXML
    public void showSnackTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailSnack.fxml"));
       try{
       Parent root = loader.load(); 

        btnressnack.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }

         public void showMenu(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
       try{
       Parent root = loader.load(); 

        Menu.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
}
