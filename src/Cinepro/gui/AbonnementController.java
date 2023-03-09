/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinepro.gui;

import cinerpo.entities.Abonnement;

import cinerpo.entities.TypeAbonnement;
import cinerpo.services.AbonnementCRUD;
import cinerpo.services.AbonnementDAO;
import cinerpo.services.StripeAPI;
import com.stripe.exception.StripeException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.cinepro.entities.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author Home
 */


public class AbonnementController implements Initializable {

    public Button Logout;
    @FXML
    private TextField idNom;
    @FXML
    private TextField idPrenom;
    @FXML
    private Button valider;
    @FXML
    private VBox vBox;
    @FXML
    private ChoiceBox<Type> choiceBox;
    @FXML
    private Button cancel;
    @FXML
    private TextField ncb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("test");
        idNom.setText(UserSession.getInstace().getNom());
        idPrenom.setText(UserSession.getInstace().getPrenom());
        // Create a new ChoiceBox
        // Add the enum values to the ChoiceBox
        choiceBox.getItems().addAll(Type.values());

        // Set a default value for the ChoiceBox
        choiceBox.setValue(Type.Un_mois);

        // Create a layout for the ChoiceBox
        vBox.getChildren().addAll(choiceBox);
        cancel.setOnAction(e -> {
    Stage stage = (Stage) cancel.getScene().getWindow();
    stage.close();
});

        // TODO
    }

    @FXML
    private void saveAbonnement(ActionEvent event) throws MessagingException, IOException, StripeException, SQLException {
        //SAUVEGARDE

        Type type = choiceBox.getValue();
        AbonnementCRUD acd = new AbonnementCRUD();
        TypeAbonnement ta = new TypeAbonnement();
        switch (type) {
            case Un_mois:
                ta.setNb_mois(1);
                ta.setPrix(50);
                break;
            case Trois_mois:
                ta.setNb_mois(3);
                ta.setPrix(150);
                break;
            case Six_mois:
                ta.setNb_mois(6);
                ta.setPrix(300);
                break;
            case Un_an:
                ta.setNb_mois(12);
                ta.setPrix(500);
                break;
        }
        Abonnement a = new Abonnement(UserSession.getInstace().getId(), ta);
        int ida = acd.ajoutEntity(a);
        String email = acd.getEmail();
        System.out.println(a.getType().getPrix());
        sendMail(email,a);
        pay(ida,a);
       
    }

    public static void sendMail(String recepient, Abonnement a) throws MessagingException, IOException {
        System.out.println("Prepared to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String myEmailAccount = "cinepro2023@gmail.com";
        String password = "utkwolmihagifeow";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailAccount, password);

            }
        });

        Message message = prepareMessage(session, myEmailAccount, recepient, a);
        Transport.send(message);
        System.out.println("Message sent succesfully");
    }

    private static Message prepareMessage(Session session, String myEmailAccount, String recepient, Abonnement a) throws IOException {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmailAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Abonnement CinePro");
            //String template = new String(Files.readAllBytes(Paths.get("C:\\Users\\Home\\Downloads\\New message 4.html")));
            MimeMultipart multipart = new MimeMultipart();
            MimeBodyPart htmlPart = new MimeBodyPart();
            //htmlPart.setContent(template, "text/html");
            multipart.addBodyPart(htmlPart);
            message.setText("Bonjour cher(e) abonné(e)\n votre abonnement a été effectué avec succès ! \n Bienvenu(e) parmi nous :) "+"\n Vous avez payé "+a.getType().getPrix()+"DT pour un abonnement de "+a.getType()+"mois.");
            return message;
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void pay(int x,Abonnement a) {
        try {
            AbonnementDAO aDAO = new AbonnementDAO();
            ResultSet user = aDAO.getUser(x);
            if (user.next()) {
                int userId = user.getInt("id_user");
                String name = user.getString("nom");
                String email = user.getString("email");
                // retrieve other user properties here
                System.out.println("User ID: " + userId);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);

                // Create a new card and associate it with the customer
                String customerId = StripeAPI.createCustomerWithCard(name,email,ncb.getText(), 12, 2023, "123");
                System.out.println("cs created");

                // Charge the card
                StripeAPI.createCharge(customerId, (int) a.getType().getPrix(), "usd", email);
                System.out.println("card charged");
                AbonnementCRUD acrud = new AbonnementCRUD();
                // rescrud.updateEntityPrix(res.getId_reservation(), 0, userId);
                // print other user properties here
            } else {
                System.out.println("No user found for Abonnement ID " + a.getId_abonnement());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (StripeException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setIdNom(String idNom) {
        this.idNom.setText(idNom);
    }

    public void setIdPrenom(String idPrenom) {
        this.idPrenom.setText(idPrenom);
    }

    public TextField getIdNom() {
        return idNom;
    }

    public TextField getIdPrenom() {
        return idPrenom;
    }

    public ChoiceBox<Type> getChoiceBox() {
        return choiceBox;
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
            Stage myWindow = (Stage) idNom.getScene().getWindow();
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
            idNom.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));

        try {
            Parent root = loader.load();
            idNom.getScene().setRoot(root);

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
            idNom.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
