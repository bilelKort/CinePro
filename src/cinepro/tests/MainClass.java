package cinepro.tests;

import cinepro.entities.Crew;
import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.services.CrewService;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import cinepro.utils.MyConnection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {

        FilmService filmService = new FilmService();
        System.out.println(filmService.filmList("a"));

    }
}
