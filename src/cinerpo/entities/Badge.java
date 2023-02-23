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
public class Badge {
    private int id_badge;
    private Type_Badge type;
    private int nbr_reservation;
    private int id_user;

    public Badge() {
    }

    public Badge(int id_badge, Type_Badge type, int nbr_reservation, int id_user) {
        this.id_badge = id_badge;
        this.type = type;
        this.nbr_reservation = nbr_reservation;
        this.id_user = id_user;
    }

    public Badge(Type_Badge type, int nbr_reservation, int id_user) {
        this.type = type;
        this.nbr_reservation = nbr_reservation;
        this.id_user = id_user;
    }

    public Badge(Type_Badge type, int nbr_reservation) {
        this.type = type;
        this.nbr_reservation = nbr_reservation;
    }
    
    

    public int getId_badge() {
        return id_badge;
    }

    public void setId_badge(int id_badge) {
        this.id_badge = id_badge;
    }

    public Type_Badge getType() {
        return type;
    }

    public void setType(Type_Badge type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Badge{" + "id_badge=" + id_badge + ", type=" + type + ", nbr_reservation=" + nbr_reservation + ", id_user=" + id_user + '}';
    }
    

    public int getNbr_reservation() {
        return nbr_reservation;
    }

    public void setNbr_reservation(int nbr_reservation) {
        this.nbr_reservation = nbr_reservation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    

  
    
    
}
