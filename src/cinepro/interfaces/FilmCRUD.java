package cinepro.interfaces;

import cinepro.entities.Film;

import java.util.List;

public interface FilmCRUD<T> {

    public void addFilm(T t);
    public List filmList();
    public Film getFilm(int id_film);
    public Film getFilm(String id_imdb);
    public void updateFilm(T t);
    public void deleteFilm(T t);
}
