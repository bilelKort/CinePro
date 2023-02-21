package cinepro.gui;

import cinepro.entities.Film;
import cinepro.services.FilmService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchMoviesController implements Initializable {

    @FXML
    private Button ajoutMovies;
    @FXML
    private BorderPane border;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField search;

    private FilmService filmService = new FilmService();
    private List<Film> list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list = filmService.filmList();
        diaplay(list);
    }

    public void test(KeyEvent keyEvent) {
        if (search.getText().equals("")) {
            list = filmService.filmList();
        } else {
            list = filmService.filmList(search.getText());
        }
        diaplay(list);
    }

    public void diaplay (List<Film> list) {
        vBox.getChildren().clear();
        int i=0;
        HBox hBox = new HBox();
        for (int j=0; j<list.size(); j++) {
            if (i==5) {
                i=0;
                vBox.setSpacing(20);
                vBox.getChildren().add(hBox);
                hBox = new HBox();
            }
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(list.get(j).getPoster()));
            imageView.setFitWidth(200);
            imageView.setFitHeight(298);

            Label label = new Label();
            label.setPrefWidth(200);
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(20));
            label.setText(list.get(j).getNom());

            VBox poster_name = new VBox();
            poster_name.setSpacing(10);
            poster_name.getChildren().addAll(imageView, label);

            hBox.setSpacing(20);
            hBox.getChildren().add(poster_name);
            i++;
        }
        vBox.getChildren().add(hBox);
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
}
