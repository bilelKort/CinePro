/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.entities;

import java.sql.Timestamp;



/**
 *
 * @author Home
 */
public class Abonnement {
    private int id_abonnement;
    private int id_user;
    private TypeAbonnement type;
    private Timestamp dateDebut;
    private Timestamp dateExpiration;  

    public Abonnement() {
    }

    public Abonnement(TypeAbonnement type) {
        this.type = type;
    }

    public Abonnement(int id_user, TypeAbonnement type, Timestamp dateDebut, Timestamp dateExpiration) {
        this.id_user = id_user;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateExpiration = dateExpiration;
    }

    public Abonnement(TypeAbonnement type, Timestamp dateDebut, Timestamp dateExpiration) {
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateExpiration = dateExpiration;

    }
    

    public Abonnement(int id_abonnement, int id_user, TypeAbonnement type, Timestamp dateDebut, Timestamp dateExpiration) {
        this.id_abonnement = id_abonnement;
        this.id_user = id_user;
        this.type = type;
        this.dateDebut = dateDebut;
        this.dateExpiration =dateExpiration;
    }

    public Abonnement(int id_user, TypeAbonnement type) {
        this.id_user=id_user;
        this.type=type;
    }

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public TypeAbonnement getType() {
        return type;
    }

    public void setType(TypeAbonnement type) {
        this.type = type;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
        
    }
    public void setDateExpiration(Timestamp dateExpiration) {
        this.dateExpiration = dateExpiration;
        
    }

    public Timestamp getDateExpiration() {
        return dateExpiration;
    }

    

    @Override
    public String toString() {
        return "Abonnement{" + "id_abonnement=" + id_abonnement + ", id_user=" + id_user + ", type=" + type.getNb_mois() + ", dateDebut=" + dateDebut + ", dateExpiration=" + dateExpiration +  '}';
    }
    
}
