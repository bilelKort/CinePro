package cinepro.gui;

import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
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
    private TextField salle_id;

    @FXML
    private ComboBox<Film> dropFilm;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkint(salle_id);
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
        List<Film> list = filmService.filmList();
        for (int i=0; i<list.size(); i++) {
            dropFilm.getItems().add(list.get(i));
        }
    }

    public void checkint(TextField input) {
        input.setTextFormatter(new TextFormatter<Object>(change -> {
            if (!change.getText().chars().allMatch(Character::isDigit)) {
                change.setText("");
            }
            return change;
        }));
    }

    public void ajoutProjection(ActionEvent actionEvent) {
        if (salle_id.getText().isEmpty() || date.getValue() == null || time.getValue() == null || dropFilm.getValue()==null) {
            alerting("Invalid", "Champ vide !");
        }else {
            String debut_date_time = date.getValue() + " " + time.getValue();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime fin_date_time = LocalDateTime.parse(debut_date_time, dateTimeFormatter);
            fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree() / 60), ChronoUnit.HOURS);
            fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree()) % 60, ChronoUnit.MINUTES);

            ProjectionService projectionService = new ProjectionService();
            Projection projection = new Projection(Integer.valueOf(salle_id.getText()), dropFilm.getValue().getId_film(), debut_date_time, dateTimeFormatter.format(fin_date_time), 0, false);
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
}
