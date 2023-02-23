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
import javafx.util.StringConverter;

import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private DatePicker date;

    @FXML
    private TextField hour;

    @FXML
    private TextField min;

    @FXML
    private Button updateBtn;

    @FXML
    private Label error;

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
        checkint(hour);
        checkint(min);
        checkint(places);
        projection = new ProjectionService().getProjection(instance.id_projection);
        salle_id.setText(Integer.toString(projection.getId_salle()));
        Date release = new Date();
        try {
            release = new SimpleDateFormat("yyy-MM-dd").parse(projection.getDate_debut().substring(0, 10));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        date.getEditor().setText(dateFormat.format(release));
        hour.setText(projection.getDate_debut().substring(11, 13));
        min.setText(projection.getDate_debut().substring(14, 16));
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

    public boolean checkTime(TextField hour, TextField min) {
        int h = Integer.valueOf(hour.getText());
        int m = Integer.valueOf(min.getText());
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            return false;
        } else {
            return true;
        }
    }

    public void updateProjection(ActionEvent actionEvent) {
        error.setText("");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update Projection");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to update this projection ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == ButtonType.OK) {
            if (salle_id.getText().isEmpty() || date.getEditor().getText().isEmpty() || hour.getText().isEmpty() || min.getText().isEmpty() || dropFilm.getValue()==null || places.getText().isEmpty()) {
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
                    Projection p = new Projection(projection.getId_projection(), Integer.valueOf(salle_id.getText()), dropFilm.getValue().getId_film(), debut_date_time, dateTimeFormatter.format(fin_date_time), Integer.valueOf(places.getText()), diffuse.isSelected());
                    if (projectionService.checkDate(p)) {
                        projectionService.updateProjection(p);
                        listProjections(new ActionEvent());
                    }else {
                        error.setText("Already projection in salle");
                    }

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
}
