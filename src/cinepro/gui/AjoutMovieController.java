package cinepro.gui;

import cinepro.entities.Crew;
import cinepro.entities.Film;
import cinepro.services.CrewService;
import cinepro.services.FilmService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AjoutMovieController extends Application {

    @FXML
    public TextField actors;
    @FXML
    public TextField directors;
    @FXML
    private Label error;
    @FXML
    private TextField search;
    @FXML
    private TextField name;
    @FXML
    private TextField date;
    @FXML
    private TextField runTime;
    @FXML
    private TextField categories;
    @FXML
    private TextArea desc;
    @FXML
    private ImageView poster;
    @FXML
    private WebView trailer;
    private static HttpURLConnection connection;
    private String id_imdb;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

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
            runTime.setText(movie_response.getInt("runtime")+"");
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
            for (int i=0; i<10; i++){
                String name = actorsArray.getJSONObject(i).getString("name");
                String portrait = "https://image.tmdb.org/t/p/original" + actorsArray.getJSONObject(i).getString("profile_path");
                actor = actor + name + ", ";
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
            System.out.println(e.getMessage());
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
        FilmService filmService = new FilmService();
        Film film = new Film(name.getText(), date.getText(), poster.getImage().impl_getUrl(), categories.getText(), desc.getText(), Integer.parseInt(runTime.getText()), trailer.getEngine().getLocation(), id_imdb);
        filmService.addFilm(film);

        CrewService crewService = new CrewService();
        JSONObject crew = get_crew(id_imdb);
        try {
            JSONArray actors = crew.getJSONArray("cast");
            for (int i=0; i<10; i++){
                String name = actors.getJSONObject(i).getString("name");
                String portrait = "https://image.tmdb.org/t/p/original" + actors.getJSONObject(i).getString("profile_path");
                Crew actor = new Crew(name, portrait, "Actor", filmService.getFilm(id_imdb).getId_film());
                crewService.addCrew(actor);
            }
            JSONArray directors = crew.getJSONArray("crew");
            for (int i=0; i<directors.length(); i++){
                if ((directors.getJSONObject(i).getString("department").equals("Directing")) && (directors.getJSONObject(i).getString("job").equals("Director"))) {
                    String name = directors.getJSONObject(i).getString("name");
                    String portrait = "https://image.tmdb.org/t/p/original" + directors.getJSONObject(i).getString("profile_path");
                    Crew director = new Crew(name, portrait, "Director", filmService.getFilm(id_imdb).getId_film());
                    crewService.addCrew(director);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
