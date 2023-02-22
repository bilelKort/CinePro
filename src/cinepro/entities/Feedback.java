/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.entities;

import java.util.Objects;

/**
 *
 * @author acer
 */
public class Feedback {
    private int id_feedback;
    private String description;
    private int id_user;
    private int id_film;
    private String date ;
   

    public Feedback() {
    }

    public Feedback(String description, int id_user) {
        this.description = description;
        this.id_user = id_user;
    }

    public Feedback(String description) {
        this.description = description;
    }

    public Feedback(String description, int id_user,int id_film, String date) {
        this.description = description;
        this.id_user = id_user;
        this.id_film = id_film;
        this.date = date;
    }

    
    public Feedback(int id_feedback, String description, int id_user,int id_film, String date) {
        this.id_feedback = id_feedback;
        this.description = description;
        this.id_user = id_user;
        this.id_film = id_film;
        this.date = date;
        
    }

    public int getId_feedback() {
        return id_feedback;
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

  
    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
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
        return "Feedback{" + "id_feedback=" + id_feedback + ", description=" + description + ", id_user=" + id_user + ", id_film=" + id_film + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Feedback other = (Feedback) obj;
        if (this.id_feedback != other.id_feedback) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_film != other.id_film) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }

   

  
    
    
    
    
}
