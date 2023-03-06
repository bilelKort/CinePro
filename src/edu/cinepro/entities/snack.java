/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cinepro.entities;

/**
 *
 * @author rayen
 */
public class snack {

    private int id_snack;
    private String nom;
    private float prix;
    private int quantite;
    private String photo;
    private int id_cinema;

    
    public snack() {
    }

    public snack(String nom, float prix, int quantite, String photo, int id_cinema) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.id_cinema = id_cinema;
    }
    
      public snack(String nom, float prix, int quantite, String photo) {
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
    }

    public int getId_snack() {
        return id_snack;
    }

    public void setId_snack(int id_snack) {
        this.id_snack = id_snack;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }



    public int getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(int id_cinema) {
        this.id_cinema = id_cinema;
    }

    public snack(int id_snack, String nom, float prix, int quantite, String photo, int id_cinema) {
        this.id_snack = id_snack;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.photo = photo;
        this.id_cinema = id_cinema;
    }

    @Override
    public String toString() {
        return  " nom=" + nom + ", prix=" + prix  ;
    }
    
    

}
