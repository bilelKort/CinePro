/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.entities;

import edu.cinepro.services.CineproCRUD;

/**
 *
 * @author MOEµNESS
 */
public final class UserSession {
    
    private static UserSession instance;

    private int id;
    private String role;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String pseudo;
    private int tel;
    private float montant;
    

    public UserSession(int id) {
        this.id = id;
        CineproCRUD ccd = new CineproCRUD();
        Cinepro c = new Cinepro();
        c = ccd.getUserById(id);
        
        this.nom = c.getNom();
        this.prenom = c.getPrenom();
        this.email = c.getEmail();
        this.date_naissance = c.getDate_naissance();
        this.pseudo = c.getPseudo();
        this.tel = c.getTel();
        this.role = c.getRole();
        this.password = c.getPassword();
        this.montant = c.getMontant();
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getTel() {
        return tel;
    }

    public float getMontant() {
        return montant;
    }

    public static UserSession getInstace() {
        return instance;
    }

    public static UserSession getInstace(int id) {
        if(instance == null) {
            instance = new UserSession(id);
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getNom() {
        return nom;
    }

    

    public void cleanUserSession() {
        id = 0;// or null
        role = "";// or null
        nom = "";
    }

    @Override
    public String toString() {
        return "UserSession{" + "id=" + id + ", role=" + role + '}';
    }

}
