package cinepro.tests;

import cinepro.entities.Crew;
import cinepro.entities.Film;
import cinepro.entities.Projection;
import cinepro.services.CrewService;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import cinepro.utils.MyConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/cinepro/gui/ListProjections.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("CinePro");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
