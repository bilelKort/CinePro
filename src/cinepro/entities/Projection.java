package cinepro.entities;

public class Projection {

    private int id_projection;
    private int id_salle;
    private int id_film;
    private String date;
    private int nbr_places;
    private boolean diffuse;
    private String type;

    public Projection() {
    }

    public Projection(int id_projection, int id_salle, int id_film, String date, int nbr_places, boolean diffuse, String type) {
        this.id_projection = id_projection;
        this.id_salle = id_salle;
        this.id_film = id_film;
        this.date = date;
        this.nbr_places = nbr_places;
        this.diffuse = diffuse;
        this.type = type;
    }

    public Projection(int id_salle, int id_film, String date, int nbr_places, boolean diffuse, String type) {
        this.id_salle = id_salle;
        this.id_film = id_film;
        this.date = date;
        this.nbr_places = nbr_places;
        this.diffuse = diffuse;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Projection{" +
                "id_projection=" + id_projection +
                ", id_salle=" + id_salle +
                ", id_film=" + id_film +
                ", date='" + date + '\'' +
                ", nbr_places=" + nbr_places +
                ", diffuse=" + diffuse +
                ", type='" + type + '\'' +
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDiffuse() {
        return diffuse;
    }

    public void setDiffuse(boolean diffuse) {
        this.diffuse = diffuse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbr_places() {
        return nbr_places;
    }

    public void setNbr_places(int nbr_places) {
        this.nbr_places = nbr_places;
    }
}
