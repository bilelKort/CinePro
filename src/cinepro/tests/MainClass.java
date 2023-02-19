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

        Projection projection = new Projection(1, 19, "20/10/2022-22:30", 50, true, "3D");
        ProjectionService projectionService = new ProjectionService();
        projectionService.addProjection(projection);

    }
}