package cinepro.gui;

import cinepro.entities.Film;
import cinepro.services.FilmService;
import edu.cinepro.entities.UserSession;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;

public class SearchMoviesController implements Initializable {

    @FXML
    private AnchorPane test;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button listProjections;
    @FXML
    private Button ajoutprojection;
    @FXML
    private Button ajoutMovies;
    @FXML
    private VBox vBox;
    @FXML
    private DatePicker date;
    @FXML
    private TextField search;
    @FXML
    private ComboBox<String> genre;

    private FilmService filmService = new FilmService();
    private List<Film> list;
    @FXML
    private Button Logout;
    @FXML
    private Button listMovies;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }
        list = filmService.filmList();
        genre.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String genres) {
                return genres;
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });
        FilmService filmService = new FilmService();
        HashSet<String> categories = filmService.genresList();
        for (String categorie: categories) {
            genre.getItems().add(categorie);
        }
        display(list);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            search(new ActionEvent());
        });
        date.valueProperty().addListener((observable, oldValue, newValue) -> {
            search(new ActionEvent());
        });
        genre.valueProperty().addListener((observable, oldValue, newValue) -> {
            search(new ActionEvent());
        });
    }

    @FXML
    public void search(ActionEvent actionEvent) {
        list = filmService.filmList(search.getText(), date.getEditor().getText(), genre.getValue());
        display(list);
    }


    @FXML
    public void searchDelete(ActionEvent actionEvent) {
        search.setText("");
        date.getEditor().setText("");
        genre.setValue("Choose Genre");
        list = filmService.filmList();
        display(list);
    }

    public void display (List<Film> list) {
        vBox.getChildren().clear();
        vBox.setSpacing(20);
        anchor.setPrefHeight(310);
        int i=0;
        HBox hBox = new HBox();
        for (int j=0; j<list.size(); j++) {
            if ((i % 5 == 0) && (i != 0)) {
                i=0;
                vBox.getChildren().add(hBox);
                hBox = new HBox();
                anchor.setPrefHeight(anchor.getPrefHeight()+310);
            }
            ImageView imageView = new ImageView();
            File file = new File(list.get(j).getPoster());
            String localURl = "";
            try {
                localURl = file.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            Image image = new Image(localURl);

            String nom = list.get(j).getNom();

            imageView.setImage(image);
            imageView.setFitWidth(200);
            imageView.setFitHeight(298);

            Label label = new Label();
            label.setPrefWidth(200);
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(20));
            label.setText(nom);

            VBox poster_name = new VBox();
            poster_name.setSpacing(10);
            poster_name.getChildren().addAll(imageView, label);

            hBox.setSpacing(40);
            hBox.getChildren().add(poster_name);
            i++;

            int id = list.get(j).getId_film();
            poster_name.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    movieDetail(new ActionEvent(), id);
                }
            });
        }
        vBox.getChildren().add(hBox);
        anchor.setPrefHeight(anchor.getPrefHeight()+310);
    }

    public void movieDetail(ActionEvent actionEvent, int id) {
        try {
            MovieDetailsController.getInstance().setId_film(id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieDetails.fxml"));
            Parent root =loader.load();
            vBox.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void ajoutMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMovie.fxml"));
            Parent root =loader.load();
            vBox.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void ajoutProjection(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProjection.fxml"));
            Parent root =loader.load();
            vBox.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void listProjections(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProjections.fxml"));
            Parent root =loader.load();
            vBox.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
