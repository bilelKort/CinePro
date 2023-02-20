/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.entities;



/**
 *
 * @author MOEµNESS
 */
public class Cinepro {
    private int id_user;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String pseudo;
    private int tel;
    private String role;
    private float montant;
    
    public Cinepro(){}

    //Constructeur
    public Cinepro(int id_user, String email, String password, String nom, String prenom, String date_naissance, String pseudo, int tel, String role, float montant) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.pseudo = pseudo;
        this.tel = tel;
        this.role = role;
        this.montant = montant;
    }

    //COnstructeur sans ID
    public Cinepro(String email, String password, String nom, String prenom, String date_naissance, String pseudo, int tel, String role, float montant) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.pseudo = pseudo;
        this.tel = tel;
        this.role = role;
        this.montant = montant;
    }
    
    
    // Getters + Setters

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Infos sur l'utilisateur "+ id_user + "{ email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", pseudo=" + pseudo + ", tel=" + tel + ", role=" + role + ", montant=" + montant + '}';
    }
    
    
    
    
}
