package cinepro.gui;

import cinepro.entities.Projection;
import cinepro.entities.TableProjection;
import cinepro.services.FilmService;
import cinepro.services.ProjectionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.File;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public void updateProjection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateProjection.fxml"));
            Parent root =loader.load();
            ajoutprojection.getScene().setRoot(root);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
