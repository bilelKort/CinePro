package cinepro.entities;

public class Crew {

    private int id_crew;
    private String nom;
    private String photo;
    private String job;
    private int id_film;

    public Crew() {
    }

    public Crew(int id_crew, String nom, String photo, String job, int id_film) {
        this.id_crew = id_crew;
        this.nom = nom;
        this.photo = photo;
        this.job = job;
        this.id_film = id_film;
    }

    public Crew(String nom, String photo, String job, int id_film) {
        this.nom = nom;
        this.photo = photo;
        this.job = job;
        this.id_film = id_film;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "id_crew=" + id_crew +
                ", nom='" + nom + '\'' +
                ", photo='" + photo + '\'' +
                ", job='" + job + '\'' +
                ", id_film=" + id_film +
                '}';
    }

    public int getId_crew() {
        return id_crew;
    }

    public void setId_crew(int id_crew) {
        this.id_crew = id_crew;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }
}
