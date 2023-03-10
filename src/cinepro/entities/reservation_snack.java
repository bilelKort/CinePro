/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.entities;

/**
 *
 * @author kortb
 */
public class reservation_snack {
   private int id_res_snack;
   private int quantite;
   private float prix;
   private int 	id_reservation;
   

    public reservation_snack() {
    }

    public reservation_snack(int quantite, float prix, int id_reservation) {
        this.quantite = quantite;
        this.prix = prix;
        this.id_reservation = id_reservation;
    }

    public reservation_snack(int id_res_snack, int quantite, float prix, int id_reservation) {
        this.id_res_snack = id_res_snack;
        this.quantite = quantite;
        this.prix = prix;
        this.id_reservation = id_reservation;
    }

    

    public int getId_res_snack() {
        return id_res_snack;
    }

    public void setId_res_snack(int id_res_snack) {
        this.id_res_snack = id_res_snack;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "reservation_snack{" + "id_res_snack=" + id_res_snack + ", quantite=" + quantite + ", id_reservation=" + id_reservation + ", prix=" + prix + '}';
    }
   
    
    
}
