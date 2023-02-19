package cinepro.entities;

import java.util.Arrays;
import java.util.List;

public class Film {

    private int id_film;
    private String nom;
    private String poster;
    private String releaseDate;
    private String categorie;
    private String description;
    private int duree;
    private String trailer;
    private String id_imdb;

    public Film() {
    }

    public Film(int id_film, String nom, String releaseDate, String poster, String categorie, String description, int duree, String trailer, String id_imdb) {
        this.id_film = id_film;
        this.nom = nom;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.categorie = categorie;
        this.description = description;
        this.duree = duree;
        this.trailer = trailer;
        this.id_imdb = id_imdb;
    }

    public Film(String nom, String releaseDate, String poster, String categorie, String description, int duree, String trailer, String id_imdb) {
        this.nom = nom;
        this.releaseDate = releaseDate;
        this.poster = poster;
        this.categorie = categorie;
        this.description = description;
        this.duree = duree;
        this.trailer = trailer;
        this.id_imdb = id_imdb;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id_film=" + id_film +
                ", nom='" + nom + '\'' +
                ", poster='" + poster + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", categorie='" + categorie + '\'' +
                ", description='" + description + '\'' +
                ", duree=" + duree +
                ", trailer='" + trailer + '\'' +
                ", id_imdb='" + id_imdb + '\'' +
                '}';
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getId_imdb() {
        return id_imdb;
    }

    public void setId_imdb(String id_imdb) {
        this.id_imdb = id_imdb;
    }
}
