/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cinepro.entities;

/**
 *
 * @author rayen
 */
public class salle {

    private int id_salle;
    private int longueur;
    private int largeur;
    private int id_cinema;
        private boolean  acces;

    public boolean isAcces() {
        return acces;
    }

    public void setAcces(boolean acces) {
        this.acces = acces;
    }
        


    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(int id_cinema) {
        this.id_cinema = id_cinema;
    }

    public salle(int id_salle, int longueur, int largeur, int id_cinema, boolean acces) {
        this.id_salle = id_salle;
        this.longueur = longueur;
        this.largeur = largeur;
        this.id_cinema = id_cinema;
        this.acces = acces;
    }

    public salle(int longueur, int largeur, int id_cinema, boolean acces) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.id_cinema = id_cinema;
        this.acces = acces;
    }

    public salle() {
    }

    @Override
    public String toString() {
        return "salle{" + "id_salle=" + id_salle + ", longueur=" + longueur + ", largeur=" + largeur + ", id_cinema=" + id_cinema + ", acces=" + acces + '}';
    }

   
    
    
    

}
