package cinepro.entities;

import cinepro.services.FilmService;

public class TableProjection {

    private int id_projection;
    private int id_salle;
    private String film;
    private int nbr_places;
    private String date_debut;
    private String date_fin;
    private Boolean diffuse;

    public TableProjection() {
    }

    public TableProjection(Projection projection) {
        this.id_projection = projection.getId_projection();
        this.id_salle = projection.getId_salle();
        this.nbr_places = projection.getNbr_places();
        this.date_debut = projection.getDate_debut();
        this.date_fin = projection.getDate_fin();
        this.diffuse = projection.isDiffuse();

        FilmService filmService = new FilmService();
        this.film = filmService.getFilmById(projection.getId_film()).getNom();
    }

    public int getId_projection() {
        return id_projection;
    }

    public void setId_projection(int id_projection) {
        this.id_projection = id_projection;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public Boolean getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Boolean diffuse) {
        this.diffuse = diffuse;
    }
}
