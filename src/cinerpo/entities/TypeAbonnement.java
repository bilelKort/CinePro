/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.entities;

/**
 *
 * @author Home
 */
public class TypeAbonnement {
    private int nb_mois;
    private int prix;

    public TypeAbonnement() {
    }

    public TypeAbonnement(int prix) {
        this.prix = prix;
    }

    public TypeAbonnement(int nb_mois, int prix) {
        this.nb_mois = nb_mois;
        this.prix = prix;
    }
    

    public int getNb_mois() {
        return nb_mois;
    }

    public void setNb_mois(int nb_mois) {
        this.nb_mois = nb_mois;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return String.valueOf(nb_mois);
    }
    
}
