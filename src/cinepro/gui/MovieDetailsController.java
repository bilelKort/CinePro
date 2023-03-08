package cinepro.gui;

import cinepro.entities.*;
import cinepro.services.*;
import edu.cinepro.gui.PlaceController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MovieDetailsController implements Initializable {


    @FXML
    private TableView<Feedback> tableview;
    @FXML
    private TableColumn<Feedback, String> feedbacktable;

    @FXML
    private TableColumn<Feedback, Integer> usertable;

    @FXML
    private TableColumn<Feedback, String> datetable;

    @FXML
    private TableColumn<Feedback, Integer> id_feedbacltable;
    @FXML
    private TextArea feedback;
    @FXML
    private TableView table;
    @FXML
    private Label nom;

    @FXML
    private Label categorie;

    @FXML
    private Label duree;

    @FXML
    private Label date;

    @FXML
    private Label description;

    @FXML
    private ImageView poster;

    @FXML
    private WebView trailer;

    @FXML
    private VBox vBocActors;

    @FXML
    private VBox vBoxDirector;

    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn<TableProjection, Integer> tableProjection;

    @FXML
    private TableColumn<TableProjection, Integer> tableSalle;

    @FXML
    private TableColumn<TableProjection, String> tableFilm;

    @FXML
    private TableColumn<TableProjection, String> tableDebut;

    @FXML
    private TableColumn<TableProjection, String> tableFin;
    @FXML
    private TableColumn<TableProjection, Void> tableRes;
    @FXML
    private Button listProjections;
    @FXML
    private Button ajoutprojection;
    @FXML
    private Button ajoutMovies;
    @FXML
    private Button listMovies;
    private static final MovieDetailsController instance = new MovieDetailsController();
    private Film film;
    public static MovieDetailsController getInstance() {
        return instance;
    }
    private int id_film;
    private List<Projection> list;
    public ObservableList<TableProjection> observableList = FXCollections.observableArrayList();
    public ObservableList<Feedback> k = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id_feedbacltable.setVisible(false);
        film = new FilmService().getFilmById(instance.id_film);
        nom.setText(film.getNom());
        categorie.setText(film.getCategorie());
        duree.setText(Integer.valueOf(film.getDuree())/60+"h "+Integer.valueOf(film.getDuree())%60+"min");
        date.setText(film.getReleaseDate());
        description.setWrapText(true);
        description.setFont(Font.font("Regular", 18));
        description.setText(film.getDescription());
        File file = new File(film.getPoster());
        String localURl = "";
        try {
            localURl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(localURl);
        poster.setImage(image);
        trailer.getEngine().load(film.getTrailer());

        displayCrew(vBoxDirector, "Director");
        displayCrew(vBocActors, "Actor");

        ProjectionService projectionService = new ProjectionService();
        list = projectionService.projectionListByFilm(instance.id_film);
        for (Projection projection : list) {
            observableList.add(new TableProjection(projection));
        }
        tableProjection.setCellValueFactory(new PropertyValueFactory<TableProjection, Integer>("id_projection"));
        tableSalle.setCellValueFactory(new PropertyValueFactory<TableProjection, Integer>("id_salle"));
        tableFilm.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("film"));
        tableDebut.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("date_debut"));
        tableFin.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("date_fin"));
        table.setItems(observableList);

        Callback<TableColumn<TableProjection, Void>, TableCell<TableProjection, Void>> update_btn = new Callback<TableColumn<TableProjection, Void>, TableCell<TableProjection, Void>>() {
            @Override
            public TableCell<TableProjection, Void> call(final TableColumn<TableProjection, Void> param) {
                final TableCell<TableProjection, Void> cell = new TableCell<TableProjection, Void>() {
                    private final Button btn = new Button("Réserver");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int rowIndex = getTableRow().getIndex();
                            try {
                                PlaceController.getInstance().setId_salle(tableSalle.getCellObservableValue(rowIndex).getValue());
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/place.fxml"));
                                Parent root =loader.load();
                                listProjections.getScene().setRoot(root);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        tableRes.setCellFactory(update_btn);


        FeedbackCRUD cd = new FeedbackCRUD();
        List<Feedback> liste = new ArrayList<Feedback>();
        liste = cd.commentaireList(instance.id_film);

        for (Feedback f : liste) {
            k.add(f);
        }

        usertable.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("id_user"));
        feedbacktable.setCellValueFactory(new PropertyValueFactory<Feedback, String>("feedback"));
        datetable.setCellValueFactory(new PropertyValueFactory<Feedback, String>("date"));
        id_feedbacltable.setCellValueFactory(new PropertyValueFactory<Feedback, Integer>("id_feedback"));

        tableview.setItems(k);
    }

    public void displayCrew(VBox vBox, String job) {
        CrewService crewService = new CrewService();
        List<Crew> list = crewService.crewListByFilm(instance.id_film);
        HBox hBoxCrew = new HBox();
        int count = 0;
        for (int i=0; i<list.size(); i++) {
            if (list.get(i).getJob().equals(job)) {
                if (count == 5) {
                    count = 0;
                    vBox.setSpacing(20);
                    vBox.getChildren().add(hBoxCrew);
                    hBoxCrew = new HBox();
                }
                ImageView imageView = new ImageView();
                Image photo_crew = new Image(list.get(i).getPhoto());
                imageView.setImage(photo_crew);
                imageView.setFitWidth(150);
                imageView.setFitHeight(225);
                Rectangle rectangle = new Rectangle(150, 225);
                rectangle.setArcHeight(250);
                rectangle.setArcWidth(250);
                imageView.setClip(rectangle);

                String nom = list.get(i).getNom();
                Label label = new Label();
                label.setPrefWidth(150);
                label.setAlignment(Pos.CENTER);
                label.setFont(Font.font(20));
                label.setText(nom);

                VBox portrait_nom = new VBox();
                portrait_nom.setSpacing(10);
                portrait_nom.getChildren().addAll(imageView, label);

                hBoxCrew.setSpacing(60);
                hBoxCrew.getChildren().add(portrait_nom);
                count++;
            }
        }
        vBox.getChildren().add(hBoxCrew);
    }

    @FXML
    public void deleteFilm(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Film");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this movie "+film.getNom()+" ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == ButtonType.OK) {
            try {
                File file = new File(film.getPoster());
                file.delete();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            FilmService filmService = new FilmService();
            filmService.deleteFilm(film);
            listMovies(new ActionEvent());
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
    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public void ajouterfeedback(ActionEvent actionEvent) {
        FeedbackCRUD c = new FeedbackCRUD();

        if (c.FeedbackCounter() >= 2) {
            System.out.println("Vous n'avez pas respecté les reglements");

            Mail nvmail = new Mail();
            nvmail.envoyerFeedback();
        } else {

            int resId_user = 1;
            int resId_film = Integer.valueOf(instance.id_film);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            FeedbackCRUD pcd = new FeedbackCRUD();
            Feedback f = new Feedback(feedback.getText(), resId_user, resId_film, date);

            pcd.addCommentaire(f);
            System.out.println("Done!!");
        }
    }
}
