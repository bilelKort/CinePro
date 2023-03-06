/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.entities;

/**
 *
 * @author acer
 */
public class Reclamation {
    private int id_reclamation;
    private String description;
    private int id_user;
    private int id_film;
    private String date ;
    private boolean etat;

    public Reclamation() {
    }
   
    public Reclamation(String description, int id_user) {
        this.description = description;
        this.id_user = id_user;
      
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public Reclamation(String description, int id_user, int id_film, String date,boolean etat) {
        this.description = description;
        this.id_user = id_user;
        this.id_film = id_film;
        this.date = date;
        this.etat=etat;
       
    }
    

    public Reclamation(int id_reclamation, String description, int id_user, int id_film, String date, boolean etat) {
        this.id_reclamation = id_reclamation;
        this.description = description;
        this.id_user = id_user;
        this.id_film = id_film;
        this.date = date;
        this.etat=etat;
        
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    
    public int getId_reclamation() {
        return id_reclamation;
    }

    public String getDescription() {
        return description;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_film() {
        return id_film;
    }

    public String getDate() {
        return date;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id_reclamation=" + id_reclamation + ", description=" + description + ", id_user=" + id_user + ", id_film=" + id_film + ", date=" + date + ", etat=" + etat + '}';
    }

   
   
    
    
}
