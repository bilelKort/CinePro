/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.entities;

/**
 *
 * @author kortb
 */
public class reservation {
   private int id_reservation;
   private float prix_final;
   private int id_user;
   private int id_film;
   private boolean state;

  
    public reservation() {
    }

    public reservation(int id_reservation, float prix_final, int id_user, int id_film,boolean state) {
        this.id_reservation = id_reservation;
        this.prix_final = prix_final;
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
    }

    public reservation(float prix_final, int id_user, int id_film,boolean state) {
        this.prix_final = prix_final;
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
    }

    public reservation(int id_user, int id_film, boolean state) {
        this.id_user = id_user;
        this.id_film = id_film;
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    
    public int getId_reservation(int aInt) {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public float getPrix_final() {
        return prix_final;
    }

    public void setPrix_final(float prix_final) {
        this.prix_final = prix_final;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", prix_final=" + prix_final + ", id_user=" + id_user + ", id_film=" + id_film + ", state=" + state + '}';
    }
       
}
