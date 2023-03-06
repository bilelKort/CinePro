package cinepro.interfaces;

import cinepro.entities.Film;

import java.sql.SQLException;
import java.util.List;

public interface FilmCRUD<T> {

    public void addFilm(T t) throws SQLException;
    public List filmList();
    public List filmList(String name, String date);
    public Film getFilmById(int id_film);
    public Film getFilm(String id_imdb);
    public void updateFilm(T t);
    public void deleteFilm(T t);
}
