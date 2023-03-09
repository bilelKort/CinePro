package cinepro.gui;

import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import edu.cinepro.entities.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UpdateProjectionController implements Initializable {

    @FXML
    private TextField salle_id;

    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTimePicker time;
    @FXML
    private Button updateBtn;

    @FXML
    private ComboBox<Film> dropFilm;
    @FXML
    private TextField places;
    @FXML
    private CheckBox diffuse;
    @FXML
    private Button listProjections;
    @FXML
    private Button ajoutprojection;
    @FXML
    private Button ajoutMovies;
    @FXML
    private Button listMovies;

    private static final UpdateProjectionController instance = new UpdateProjectionController();
    private Projection projection;
    private int id_projection;

    public static UpdateProjectionController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkint(salle_id);
        checkint(places);
        projection = new ProjectionService().getProjection(instance.id_projection);
        salle_id.setText(Integer.toString(projection.getId_salle()));
        date.setValue(LocalDate.parse(projection.getDate_debut().substring(0, 10)));
        time.setValue(LocalTime.parse(projection.getDate_debut().substring(11, 16)));
        places.setText(Integer.toString(projection.getNbr_places()));

        dropFilm.setConverter(new StringConverter<Film>() {
            @Override
            public String toString(Film object) {
                return object.getId_film() + " - " + object.getNom();
            }

            @Override
            public Film fromString(String string) {
                return null;
            }
        });
        FilmService filmService = new FilmService();
        List<Film> list = filmService.filmList();
        for (int i=0; i<list.size(); i++) {
            dropFilm.getItems().add(list.get(i));
        }
        dropFilm.setValue(filmService.getFilmById(projection.getId_film()));
        diffuse.setSelected(projection.isDiffuse());


    }

    public void checkint(TextField input) {
        input.setTextFormatter(new TextFormatter<Object>(change -> {
            if (!change.getText().chars().allMatch(Character::isDigit)) {
                change.setText("");
            }
            return change;
        }));
    }


    public void updateProjection(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Projection");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update this projection ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == ButtonType.OK) {
            if (salle_id.getText().isEmpty() || date.getValue() == null || time.getValue() == null || dropFilm.getValue()==null || places.getText().isEmpty()) {
                alerting("Invalid", "Champ vide !");
            }else {
                String debut_date_time = date.getValue() + " " + time.getValue();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime fin_date_time = LocalDateTime.parse(debut_date_time, dateTimeFormatter);
                fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree() / 60), ChronoUnit.HOURS);
                fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree()) % 60, ChronoUnit.MINUTES);

                ProjectionService projectionService = new ProjectionService();
                Projection p = new Projection(projection.getId_projection(), Integer.valueOf(salle_id.getText()), dropFilm.getValue().getId_film(), debut_date_time, dateTimeFormatter.format(fin_date_time), Integer.valueOf(places.getText()), diffuse.isSelected());
                if (projectionService.checkDate(p)) {
                    projectionService.updateProjection(p);
                    listProjections(new ActionEvent());
                }else {
                    alerting("Invalid", "Projection est en cours pour cette salle !");
                }
            }
        }
    }

    public void alerting(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Optional<ButtonType> option = alert.showAndWait();
    }

    public void ajoutMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMovie.fxml"));
            Parent root =loader.load();
            ajoutMovies.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchMovies.fxml"));
            Parent root =loader.load();
            listMovies.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ajoutProjection(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProjection.fxml"));
            Parent root =loader.load();
            ajoutprojection.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listProjections(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProjections.fxml"));
            Parent root =loader.load();
            listProjections.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setId_projection(int id_projection) {
        this.id_projection = id_projection;
    }

    public void diffuse(ActionEvent actionEvent) {
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
            Stage myWindow = (Stage) date.getScene().getWindow();
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
            date.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AfficherReclamation.fxml"));

        try {
            Parent root = loader.load();
            date.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void cinema(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/CinemaAffiche.fxml"));

        try {
            Parent root = loader.load();
            date.getScene().setRoot(root);

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
            date.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
