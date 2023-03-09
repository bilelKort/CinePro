package cinepro.gui;

import cinepro.entities.Projection;
import cinepro.entities.TableProjection;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import edu.cinepro.entities.UserSession;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListProjectionsController implements Initializable {

    @FXML
    private AnchorPane anchor;
    @FXML
    private TableView<TableProjection> tableProjection;
    @FXML
    private Button ajoutprojection;
    @FXML
    private Button ajoutMovies;
    @FXML
    private Button listMovies;
    @FXML
    private TableColumn<TableProjection, Integer> id_projection;

    @FXML
    private TableColumn<TableProjection, Integer> id_salle;

    @FXML
    private TableColumn<TableProjection, String> id_film;

    @FXML
    private TableColumn<TableProjection, Integer> nbr_places;

    @FXML
    private TableColumn<TableProjection, String> debut;

    @FXML
    private TableColumn<TableProjection, String> fin;

    @FXML
    private TableColumn<TableProjection, String> diffuse;

    @FXML
    private TableColumn<TableProjection, Void> update;

    @FXML
    private TableColumn<TableProjection, Void> delete;
    private ProjectionService projectionService = new ProjectionService();
    private List<Projection> list;
    public ObservableList<TableProjection> observableList = FXCollections.observableArrayList();
    @FXML
    private Button Logout;
    @FXML
    private Button listProjections;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (UserSession.getInstace().getId()==0) {
            Logout.setVisible(false);
        }
        tableProjection.getItems().clear();
        projectionService.updateDiffuse();
        list = projectionService.projectionList();
        for (Projection projection : list) {
            observableList.add(new TableProjection(projection));
        }
        id_projection.setCellValueFactory(new PropertyValueFactory<TableProjection, Integer>("id_projection"));
        id_salle.setCellValueFactory(new PropertyValueFactory<TableProjection, Integer>("id_salle"));
        id_film.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("film"));
        nbr_places.setCellValueFactory(new PropertyValueFactory<TableProjection, Integer>("nbr_places"));
        debut.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("date_debut"));
        fin.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("date_fin"));
        diffuse.setCellValueFactory(new PropertyValueFactory<TableProjection, String>("diffuse"));

        tableProjection.setItems(observableList);

        Callback<TableColumn<TableProjection, Void>, TableCell<TableProjection, Void>> delete_btn = new Callback<TableColumn<TableProjection, Void>, TableCell<TableProjection, Void>>() {
            @Override
            public TableCell<TableProjection, Void> call(final TableColumn<TableProjection, Void> param) {
                final TableCell<TableProjection, Void> cell = new TableCell<TableProjection, Void>() {
                    private final Button btn = new Button("Supprimer");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int rowIndex = getTableRow().getIndex();
                            deleteProjection(id_projection.getCellObservableValue(rowIndex).getValue());
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
        delete.setCellFactory(delete_btn);

        Callback<TableColumn<TableProjection, Void>, TableCell<TableProjection, Void>> update_btn = new Callback<TableColumn<TableProjection, Void>, TableCell<TableProjection, Void>>() {
            @Override
            public TableCell<TableProjection, Void> call(final TableColumn<TableProjection, Void> param) {
                final TableCell<TableProjection, Void> cell = new TableCell<TableProjection, Void>() {
                    private final Button btn = new Button("Update");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            int rowIndex = getTableRow().getIndex();
                            UpdateProjectionController.getInstance().setId_projection(id_projection.getCellObservableValue(rowIndex).getValue());
                            updateProjection();
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
        update.setCellFactory(update_btn);
    }

    public void deleteProjection(int id) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Film");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this projection ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.isPresent() && option.get() == ButtonType.OK) {
            ProjectionService projectionService1 = new ProjectionService();
            projectionService1.deleteProjection(projectionService.getProjection(id));
        }
        initialize(new FXMLLoader().getLocation(), new FXMLLoader().getResources());
    }


    @FXML
    public void ajoutMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMovie.fxml"));
            Parent root =loader.load();
            ajoutMovies.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void listMovies(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchMovies.fxml"));
            Parent root =loader.load();
            listMovies.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void ajoutProjection(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProjection.fxml"));
            Parent root =loader.load();
            ajoutprojection.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProjection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProjection.fxml"));
            Parent root =loader.load();
            ajoutprojection.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/SignIn.fxml"));

        try {
            Parent root = loader.load();
            Stage myWindow = (Stage) Logout.getScene().getWindow();
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
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void reclamation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinepro/gui/AjouterReclamation.fxml"));

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

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
            }else {
                loader = new FXMLLoader(getClass().getResource("/edu/cinepro/gui/GerantIndex.fxml"));
            }
        }

        try {
            Parent root = loader.load();
            Logout.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}