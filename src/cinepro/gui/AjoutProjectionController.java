package cinepro.gui;

import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import edu.cinepro.entities.salle;
import edu.connexion3A18.services.SalleCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AjoutProjectionController implements Initializable {

    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTimePicker time;
    @FXML
    private Button listProjections;
    @FXML
    private Button ajoutMovies;
    @FXML
    private Button listMovies;

    @FXML
    private Button ajoutprojection;

    @FXML
    private ComboBox<Film> dropFilm;
    private static final AjoutProjectionController instance = new AjoutProjectionController();
    private int id_salle;
    public static AjoutProjectionController getInstance() {
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
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
        List<Film> listFilm = filmService.filmList();
        for (int i=0; i<listFilm.size(); i++) {
            dropFilm.getItems().add(listFilm.get(i));
        }
    }

    public void ajoutProjection(ActionEvent actionEvent) {
        if (date.getValue() == null || time.getValue() == null || dropFilm.getValue()==null) {
            alerting("Invalid", "Champ vide !");
        }else {
            String debut_date_time = date.getValue() + " " + time.getValue();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fin_date_time = LocalDateTime.parse(debut_date_time, dateTimeFormatter);
            fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree() / 60), ChronoUnit.HOURS);
            fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree()) % 60, ChronoUnit.MINUTES);

            ProjectionService projectionService = new ProjectionService();
            Projection projection = new Projection(instance.id_salle, dropFilm.getValue().getId_film(), debut_date_time, dateTimeFormatter.format(fin_date_time), 0, false);
            if (projectionService.checkDate(projection)) {
                projectionService.addProjection(projection);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ajout Projection");
                alert.setHeaderText(null);
                alert.setContentText("Projection ajoutée !");
                Optional<ButtonType> option = alert.showAndWait();
                listProjections(new ActionEvent());
            }else {
                alerting("Invalid", "Projection est en cours pour cette salle !");
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

    public void listProjections(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListProjections.fxml"));
            Parent root =loader.load();
            listProjections.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }
}
