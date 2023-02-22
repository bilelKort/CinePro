package cinepro.gui;

import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
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
import java.util.ResourceBundle;

public class AjoutProjectionController implements Initializable {

    @FXML
    private Label error;
    @FXML
    private Button ajoutMovies;
    @FXML
    private Button listMovies;

    @FXML
    private Button ajoutprojection;

    @FXML
    private TextField salle_id;

    @FXML
    private DatePicker date;

    @FXML
    private TextField hour;

    @FXML
    private ComboBox<Film> dropFilm;

    @FXML
    private TextField min;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkint(salle_id);
        checkint(hour);
        checkint(min);
        dropFilm.setConverter(new StringConverter<Film>() {
            @Override
            public String toString(Film object) {
                return object.getNom();
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

    public boolean checkTime(TextField hour, TextField min) {
        int h = Integer.valueOf(hour.getText());
        int m = Integer.valueOf(min.getText());
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            return false;
        } else {
            return true;
        }
    }

    public void ajoutProjection(ActionEvent actionEvent) {
        error.setText("");
        if (salle_id.getText().isEmpty() || date.getEditor().getText().isEmpty() || hour.getText().isEmpty() || min.getText().isEmpty() || dropFilm.getValue()==null) {
            error.setText("Empty field !");
        }else {
            if (!checkTime(hour, min)) {
                error.setText("Wrong Time !");
            } else {
                if (hour.getText().length()==1)
                    hour.setText("0"+hour.getText());
                if (min.getText().length()==1)
                    min.setText("0"+min.getText());
                String debut_date_time = date.getEditor().getText() + "-" + hour.getText() + ":" + min.getText();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
                LocalDateTime fin_date_time = LocalDateTime.parse(debut_date_time, dateTimeFormatter);
                fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree() / 60), ChronoUnit.HOURS);
                fin_date_time = fin_date_time.plus(Integer.valueOf(dropFilm.getValue().getDuree()) % 60, ChronoUnit.MINUTES);

                ProjectionService projectionService = new ProjectionService();
                Projection projection = new Projection(Integer.valueOf(salle_id.getText()), dropFilm.getValue().getId_film(), debut_date_time, dateTimeFormatter.format(fin_date_time), 0, false);
                if (projectionService.checkDate(projection)) {
                    projectionService.addProjection(projection);
                }else {
                    error.setText("Already projection in salle");
                }

            }
        }
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

}
