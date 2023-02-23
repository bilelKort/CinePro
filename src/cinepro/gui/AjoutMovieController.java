package cinepro.gui;

import cinepro.entities.Crew;
import cinepro.entities.Film;
import cinepro.services.CrewService;
import cinepro.services.FilmService;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ECMAException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AjoutMovieController implements Initializable {

    @FXML
    private Button listProjections;
    @FXML
    private Button ajoutprojection;
    @FXML
    private Button listMovies;
    @FXML
    private Label actors;
    @FXML
    private Label directors;
    @FXML
    private Label error;
    @FXML
    private TextField search;
    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label runTime;
    @FXML
    private Label categories;
    @FXML
    private Label desc;
    @FXML
    private ImageView poster;
    @FXML
    private WebView trailer;
    private static HttpURLConnection connection;
    private String id_imdb;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void searchMovie(ActionEvent actionEvent) {
        error.setText("");
        try {
            id_imdb = search.getText().split("/")[4];
        } catch (Exception e) {
            error.setText("URL invalid");
            return;
        }

        JSONObject movie_response = get_movie(id_imdb);
        JSONObject trailer_response = get_trailer(id_imdb);
        JSONObject crew_response = get_crew(id_imdb);

        try {
            //////////////////////////////////////////////////////////////////////////////////details
            name.setText(movie_response.getString("title"));
            runTime.setText(movie_response.getInt("runtime")/60 + "h " + movie_response.getInt("runtime")%60 + "min");
            date.setText(movie_response.getString("release_date"));
            desc.setText(movie_response.getString("overview"));
            desc.setWrapText(true);
            String genres = "";
            for (int i = 0; i < movie_response.getJSONArray("genres").length(); i++) {
                genres = genres + movie_response.getJSONArray("genres").getJSONObject(i).getString("name") +", ";
            }
            categories.setText(genres.substring(0, genres.length()-2));
            String poster_url = "https://image.tmdb.org/t/p/original" + movie_response.getString("poster_path");
            poster.setImage(new Image(poster_url));

            ///////////////////////////////////////////////////////////////////////////////trailer
            JSONArray response_videos = trailer_response.getJSONArray("results");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.'000Z'");
            Date date_trailer = format.parse(response_videos.getJSONObject(0).getString("published_at"));
            String key_trailer = "";
            for (int i=0; i<response_videos.length(); i++){
                if (response_videos.getJSONObject(i).getString("type").equals("Trailer")){
                    Date release_trailer = format.parse(response_videos.getJSONObject(i).getString("published_at"));
                    if (release_trailer.compareTo(date_trailer) < 0) {
                        date_trailer = release_trailer;
                        key_trailer = response_videos.getJSONObject(i).getString("key");
                    }
                }
            }
            String youtubeTrailer = "https://www.youtube.com/embed/" + key_trailer;
            trailer.getEngine().load(youtubeTrailer);


            /////////////////////////////////////////////////////////////////////////////crew
            JSONArray actorsArray = crew_response.getJSONArray("cast");
            String actor = "";
            int count =0;
            for (int i=0; i<actorsArray.length(); i++){
                if (count >= 10) {
                    break;
                }
                try {
                    String name = actorsArray.getJSONObject(i).getString("name");
                    String portrait = "https://image.tmdb.org/t/p/original" + actorsArray.getJSONObject(i).getString("profile_path");
                    actor = actor + name + ", ";
                    count ++;
                } catch (Exception e) {
                }
            }
            actors.setText(actor.substring(0, actor.length()-2));

            JSONArray directorsArray = crew_response.getJSONArray("crew");
            String director = "";
            for (int i=0; i<directorsArray.length(); i++){
                if ((directorsArray.getJSONObject(i).getString("department").equals("Directing")) && (directorsArray.getJSONObject(i).getString("job").equals("Director"))) {
                    String name = directorsArray.getJSONObject(i).getString("name");
                    String portrait = "https://image.tmdb.org/t/p/original" + directorsArray.getJSONObject(i).getString("profile_path");
                    director = director + name + ", ";
                }
            }
            directors.setText(director.substring(0, director.length()-2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public JSONObject get_movie(String id_imdb) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONObject response = null;
        try {
            URL url = new URL("https://api.themoviedb.org/3/movie/" + id_imdb + "?api_key=bc707c1f4e36344270536a932b5f6a58");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                error.setText("URL invalid");
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            response = new JSONObject(responseContent.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage() +"test");
        } finally {
            connection.disconnect();
            return response;
        }
    }

    public JSONObject get_trailer(String id_imdb) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONObject response = null;
        try {
            URL url = new URL("http://api.themoviedb.org/3/movie/" + id_imdb + "/videos?api_key=bc707c1f4e36344270536a932b5f6a58");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                error.setText("URL invalid");
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            response = new JSONObject(responseContent.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.disconnect();
            return response;
        }
    }

    public JSONObject get_crew(String id_imdb) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        JSONObject response = null;
        try {
            URL url = new URL("http://api.themoviedb.org/3/movie/" + id_imdb + "/credits?api_key=bc707c1f4e36344270536a932b5f6a58");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                error.setText("URL invalid");
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            response = new JSONObject(responseContent.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            connection.disconnect();
            return response;
        }
    }

    public void ajouterFilm(ActionEvent actionEvent) {
        error.setText("");

        String poster_id = poster.getImage().impl_getUrl().split("/")[6];
        String localURL = "src/cinepro/images/poster/"+poster_id;
        FilmService filmService = new FilmService();
        Film film = new Film(name.getText(), date.getText(), localURL, categories.getText(), desc.getText(), Integer.parseInt(runTime.getText()), trailer.getEngine().getLocation(), id_imdb);

        try {
            filmService.addFilm(film);
        } catch (SQLException e) {
            error.setText("URL invalid");
            return;
        }
        File file = new File(localURL);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(new Image("https://image.tmdb.org/t/p/original/"+poster_id),null), "jpg", file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        CrewService crewService = new CrewService();
        JSONObject crew = get_crew(id_imdb);
        try {
            JSONArray actors = crew.getJSONArray("cast");
            int count =0;
            for (int i=0; i<actors.length(); i++){
                if (count >= 10) {
                    break;
                }
                try {
                    String name = actors.getJSONObject(i).getString("name");
                    String portrait_id = actors.getJSONObject(i).getString("profile_path");
                    String portrait = "src/cinepro/images/crewPhoto" + portrait_id;
                    Crew actor = new Crew(name, portrait, "Actor", filmService.getFilm(id_imdb).getId_film());
                    crewService.addCrew(actor);

                    File filePortrait = new File(portrait);
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(new Image("https://image.tmdb.org/t/p/original"+portrait_id),null), "jpg", filePortrait);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    count ++;
                } catch (Exception e) {
                }
            }
            JSONArray directors = crew.getJSONArray("crew");
            for (int i=0; i<directors.length(); i++){
                if ((directors.getJSONObject(i).getString("department").equals("Directing")) && (directors.getJSONObject(i).getString("job").equals("Director"))) {
                    String name = directors.getJSONObject(i).getString("name");
                    String portrait_id = directors.getJSONObject(i).getString("profile_path");
                    String portrait = "src/cinepro/images/crewPhoto" + portrait_id;
                    Crew director = new Crew(name, portrait, "Director", filmService.getFilm(id_imdb).getId_film());
                    crewService.addCrew(director);

                    File filePortrait = new File(portrait);
                    try {
                        ImageIO.write(SwingFXUtils.fromFXImage(new Image("https://image.tmdb.org/t/p/original"+portrait_id),null), "jpg", filePortrait);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            listMovies(new ActionEvent());
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
}
