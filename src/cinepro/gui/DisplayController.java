/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinepro.entities.reservation;
import cinepro.services.QRCodeGenerator;
import cinepro.services.ReservationDAO;
import cinepro.services.StripeAPI;
import cinepro.services.WeatherAPI;
import cinepro.services.reservationCRUD;
import cinepro.utils.cineproConnexion;
import com.google.zxing.WriterException;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cinepro.entities.UserSession;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author kortb
 */
public class DisplayController implements Initializable {
  
    @FXML
    private TableColumn<reservation, Integer> col1;
    @FXML
    private TableColumn<reservation, Float> col2;
    @FXML
    private TableColumn<reservation, Integer> col3;
    @FXML
    private TableColumn<reservation, Integer> col4;
    @FXML
    private TableColumn<reservation, Boolean> col5;
    @FXML
    private TableColumn<reservation, Timestamp> col6;
    @FXML
    private TableColumn<reservation, Timestamp> col7;
    @FXML
    private TableColumn<reservation, Integer> col8;
    @FXML
    private TableView<reservation> tableview;
    public   ObservableList<reservation> data = FXCollections.observableArrayList();
    @FXML
    private Button btnresplace;
    @FXML
    private Button btnressnack;
    @FXML
    private Button Menu;
    @FXML
    private TableColumn<reservation, Void> delete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       reservationCRUD rs = new reservationCRUD();
       List<reservation> ls = rs.entitiesList();

    for(reservation i:ls){
        data.add(i);
    }
    
    col1.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_reservation"));
    col2.setCellValueFactory(new PropertyValueFactory<reservation, Float>("prix_final"));
    col3.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_user"));
    col4.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_film"));
    col5.setCellValueFactory(new PropertyValueFactory<reservation, Boolean>("state"));
    col6.setCellValueFactory(new PropertyValueFactory<reservation, Timestamp>("start_time"));
    col7.setCellValueFactory(new PropertyValueFactory<reservation, Timestamp>("end_time"));
    col8.setCellValueFactory(new PropertyValueFactory<reservation, Integer>("id_projection"));
        
    tableview.setItems(data);
    
    

    // Create new TableColumn for QR code
TableColumn<reservation, Void> qrCodeCol = new TableColumn<>("QR Code");
qrCodeCol.setPrefWidth(100);
qrCodeCol.setSortable(false);

// Set cellFactory for QR code column to display QR code for each reservation
qrCodeCol.setCellFactory(new Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>>() {
    @Override
    public TableCell<reservation, Void> call(TableColumn<reservation, Void> param) {
        final TableCell<reservation, Void> cell = new TableCell<reservation, Void>() {
              private final Button btnqr = new Button("QR Code");
            {
                btnqr.setOnAction((ActionEvent event) -> {
                    // Retrieve reservation information from the database
                    reservation res = getTableView().getItems().get(getIndex());

                    String reservationInfo = " id reservation: " + res.getId_reservation() + "\n id user: " + res.getId_user() + "\n id_film: " + 
                + res.getId_film() + "\n id projection: " + res.getId_projection() + "\n start time: " + res.getStart_time() + "\n end time: " + res.getEnd_time() + "\n price: " + res.getPrix_final();

                    // Generate QR code
                    QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
                    ImageView qrCodeImageView = null;
                    qrCodeImageView = qrCodeGenerator.generateQRCode(reservationInfo);

                    // Create new stage
                    Stage qrCodeStage = new Stage();
                    qrCodeStage.setTitle("Reservation QR Code");

                    // Check if qrCodeImageView is null
                    if (qrCodeImageView != null) {
                        // Add ImageView object to the scene
                        Scene scene = new Scene(new Group(qrCodeImageView));
                        qrCodeStage.setScene(scene);

                        // Show the new stage
                        qrCodeStage.show();
                    }
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnqr);
                }
            }
        };
        return cell;
    }
});

// Add QR code column to the table
tableview.getColumns().add(qrCodeCol);
////////////////////////////////////////////////////////////////////////////////////**
TableColumn<reservation, String> weatherIconCol = new TableColumn<>("Weather Icon");
weatherIconCol.setCellValueFactory(cellData -> {
    reservation reservation = cellData.getValue();
    try {
        LocalDate date = reservation.getStart_time().toLocalDateTime().toLocalDate();
        String weatherData = WeatherAPI.getWeatherData("Tunisia", date);
        JSONObject obj = new JSONObject(weatherData);
        JSONArray weatherArray = obj.getJSONArray("weather");
        JSONObject weather = weatherArray.getJSONObject(0);
        String iconCode = weather.getString("icon");
        URL iconUrl = new URL("https://openweathermap.org/img/w/" + iconCode + ".png");
        return new SimpleStringProperty(iconUrl.toString());
    } catch (Exception e) {
        e.printStackTrace();
        return new SimpleStringProperty("Error retrieving weather information");
    }
});

weatherIconCol.setCellFactory(column -> new TableCell<reservation, String>() {
    private final ImageView imageView = new ImageView();

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setGraphic(null);
        } else {
            Image icon = new Image(item);
            imageView.setImage(icon);
            setGraphic(imageView);
        }
    }
});


tableview.getColumns().add(weatherIconCol);

///////////////////////////////////////////////////////

TableColumn<reservation, String> weatherCol = new TableColumn<>("Weather");

weatherCol.setCellValueFactory(cellData -> {
    reservation reservation = cellData.getValue();
    try {
        Timestamp timestamp = reservation.getStart_time();
        LocalDate date = reservation.getStart_time().toLocalDateTime().toLocalDate();
        String weatherInfo = WeatherAPI.displayWeather(date, "Tunisia");
        return new SimpleStringProperty(weatherInfo);
    } catch (Exception e) {
        e.printStackTrace();
        return new SimpleStringProperty("Error retrieving weather information");
    }
});


// Add weather column to the table
tableview.getColumns().add(weatherCol);

//////////////////////////////////////////////////////////////////////////////////////**

     Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>> cellFactory = new Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>>() {
            @Override
            public TableCell<reservation, Void> call(final TableColumn<reservation, Void> param) {
                final TableCell<reservation, Void> cell = new TableCell<reservation, Void>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int rowIndex = getTableRow().getIndex();
                            reservationCRUD pcd = new reservationCRUD();
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
        
        
        ////////////////////////////////////STRIPE///////////////////////////////////////////////////
TableColumn<reservation, Void> paymentCol = new TableColumn<>("payment");

Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>> payment = new Callback<TableColumn<reservation, Void>, TableCell<reservation, Void>>() {
    @Override
    public TableCell<reservation, Void> call(TableColumn<reservation, Void> param) {
        final TableCell<reservation, Void> cell = new TableCell<reservation, Void>() {
            // Define the button that generates the QR code
            private final Button btnstripe = new Button("Payer");
            {
                // Set action for the button
       btnstripe.setOnAction((ActionEvent event) -> {
    // Retrieve reservation information from the table view
    reservation res = getTableView().getItems().get(getIndex());

    // Create a new dialog box
    Dialog<ButtonType> dialog = new Dialog<>();
    dialog.setTitle("Create Customer with Card");

    // Set the button types
    ButtonType createButtonType = new ButtonType("Create", ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(createButtonType, ButtonType.CANCEL);

    // Create the form
    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    TextField nameField = new TextField();
    nameField.setPromptText("Name");
    TextField emailField = new TextField();
    emailField.setPromptText("Email");
    TextField cardNumberField = new TextField();
    cardNumberField.setPromptText("Card Number");
    TextField expMonthField = new TextField();
    expMonthField.setPromptText("Expiration Month");
    TextField expYearField = new TextField();
    expYearField.setPromptText("Expiration Year");
    TextField cvcField = new TextField();
    cvcField.setPromptText("CVC");

    grid.add(new Label("Name:"), 0, 0);
    grid.add(nameField, 1, 0);
    grid.add(new Label("Email:"), 0, 1);
    grid.add(emailField, 1, 1);
    grid.add(new Label("Card Number:"), 0, 2);
    grid.add(cardNumberField, 1, 2);
    grid.add(new Label("Expiration Month:"), 0, 3);
    grid.add(expMonthField, 1, 3);
    grid.add(new Label("Expiration Year:"), 0, 4);
    grid.add(expYearField, 1, 4);
    grid.add(new Label("CVC:"), 0, 5);
    grid.add(cvcField, 1, 5);

    dialog.getDialogPane().setContent(grid);

    // Convert the result to a Customer object when the button is clicked
    dialog.setResultConverter(dialogButton -> {
        if (dialogButton == createButtonType) {
            String name = nameField.getText();
            String email = emailField.getText();
            String cardNumber = cardNumberField.getText();
            int expMonth = Integer.parseInt(expMonthField.getText());
            int expYear = Integer.parseInt(expYearField.getText());
            String cvc = cvcField.getText();
            try {
                String customerId = StripeAPI.createCustomerWithCard(email, name, cardNumber, expMonth, expYear, cvc);
                StripeAPI.createCharge(customerId, (int) res.getPrix_final(), "usd", email);
                reservationCRUD rescrud = new reservationCRUD();
                rescrud.updateEntityPrix(res.getId_reservation(), 0, res.getId_user());
            } catch (StripeException ex) {
                Logger.getLogger(DisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    });

    dialog.showAndWait();
});

            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnstripe);
                }
            }
        };
        return cell;
    }
};

paymentCol.setCellFactory(payment);
tableview.getColumns().add(paymentCol);

        
     ///////end init //////   
    }    
      // Define the TableView and TableColumn objects from the FXML file
    @FXML
   public void showPlaceTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailPlace.fxml"));
       try{
       Parent root = loader.load(); 

        btnresplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    @FXML
    public void showSnackTable(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("detailSnack.fxml"));
       try{
       Parent root = loader.load(); 

        btnresplace.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }
    // Define a method to load the data and populate the TableView
    public void loadData() throws SQLException {
        // Connect to your database
         
    }
    
    @FXML
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

