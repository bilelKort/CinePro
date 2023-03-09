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
    
    private static UserSession instance = new UserSession();

    private int id=0;
    private String role;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String pseudo;
    private int tel;
    private float montant;

    public UserSession() {
    }

    public UserSession(int id) {
        instance.id = id;
        CineproCRUD ccd = new CineproCRUD();
        User c = new User();
        c = ccd.getUserById(id);
        instance.nom = c.getNom();
        instance.prenom = c.getPrenom();
        instance.email = c.getEmail();
        instance.date_naissance = c.getDate_naissance();
        instance.pseudo = c.getPseudo();
        instance.tel = c.getTel();
        instance.role = c.getRole();
        instance.password = c.getPassword();
        instance.montant = c.getMontant();
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
        new UserSession(id);
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
