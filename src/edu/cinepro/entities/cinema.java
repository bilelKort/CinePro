/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cinepro.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rayen
 */
public class cinema {

    private int id_cinema;
    private int id_user;
    private String nom;
    private String localisation;
    private String description;
    private String photo;
    private ArrayList<salle> listsalle = new ArrayList<salle>();

    public List getListsalle() {
        return listsalle;
    }

    public void setListsalle(ArrayList<salle> listsalle) {
        this.listsalle = listsalle;
    }

   
      
    
    

    public int getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(int id_cinema) {
        this.id_cinema = id_cinema;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

  

    public cinema() {
    }

    public cinema(int id_user, String nom, String localisation, String description, String photo) {
        this.id_user = id_user;
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.photo = photo;
    }

    public cinema(int id_cinema, int id_user, String nom, String localisation, String description, String photo) {
        this.id_cinema = id_cinema;
        this.id_user = id_user;
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.photo = photo;
    }
     public cinema(String nom, String localisation, String description, String photo, ArrayList<salle> listsalle) {
        this.nom = nom;
        this.localisation = localisation;
        this.description = description;
        this.photo = photo;
        this.listsalle = listsalle;
    }
    
    
    @Override

    /*public String toString() {
        return "cinema{" + "id_cinema=" + id_cinema + ", id_user=" + id_user + ", nom=" + nom + ", localisation=" + localisation + ", description=" + description + ", photo=" + photo + '}';
    }*/

    public String toString() {
        return "cinema{" + "id_cinema=" + id_cinema + ", id_user=" + id_user + ", nom=" + nom + ", localisation=" + localisation + ", description=" + description + ", photo=" + photo + ", listsalle=" + listsalle + '}';
    }
    
    
    
    

}
