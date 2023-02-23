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
    private String feedback;
    private int id_user;
    private int id_film;
    private String date ;
   

    public Feedback() {
    }

    public Feedback(String feedback, int id_user) {
        this.feedback = feedback;
        this.id_user = id_user;
    }

    public Feedback(String feedback) {
        this.feedback = feedback;
    }

    public Feedback(String feedback, int id_user,int id_film, String date) {
        this.feedback = feedback;
        this.id_user = id_user;
        this.id_film = id_film;
        this.date = date;
    }

    
    public Feedback(int id_feedback, String feedback, int id_user,int id_film, String date) {
        this.id_feedback = id_feedback;
        this.feedback= feedback;
        this.id_user = id_user;
        this.id_film = id_film;
        this.date = date;
        
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public String getFeedback() {
        return feedback;
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

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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
        return "Feedback{" + "id_feedback=" + id_feedback + ", feedback=" + feedback + ", id_user=" + id_user + ", id_film=" + id_film + ", date=" + date + '}';
    }

  

   

  
    
    
    
    
}
