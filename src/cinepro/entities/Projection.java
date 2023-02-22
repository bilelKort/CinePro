package cinepro.entities;

public class Projection {

    private int id_projection;
    private int id_salle;
    private int id_film;
    private String date_debut;
    private String date_fin;
    private int nbr_places;
    private boolean diffuse;
    public Projection() {
    }

    public Projection(int id_projection, int id_salle, int id_film, String date_debut, String date_fin, int nbr_places, boolean diffuse) {
        this.id_projection = id_projection;
        this.id_salle = id_salle;
        this.id_film = id_film;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_places = nbr_places;
        this.diffuse = diffuse;
    }

    public Projection(int id_salle, int id_film, String date_debut, String date_fin, int nbr_places, boolean diffuse) {
        this.id_salle = id_salle;
        this.id_film = id_film;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.nbr_places = nbr_places;
        this.diffuse = diffuse;
    }

    @Override
    public String toString() {
        return "Projection{" +
                "id_projection=" + id_projection +
                ", id_salle=" + id_salle +
                ", id_film=" + id_film +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", nbr_places=" + nbr_places +
                ", diffuse=" + diffuse +
                '}';
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

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public boolean isDiffuse() {
        return diffuse;
    }

    public void setDiffuse(boolean diffuse) {
        this.diffuse = diffuse;
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
}
