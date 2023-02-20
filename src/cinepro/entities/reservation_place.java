/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.entities;
import java.sql.Timestamp;

/**
 *
 * @author kortb
 */
public class reservation_place {
   private int id_res_place;
   private String coordonnee;
   private float prix;
   private int id_reservation;
   private Timestamp start_time;
   private Timestamp end_time;
   

    public reservation_place() {
    }

    public reservation_place(String coordonnee, float prix, int id_reservation,Timestamp start_time,Timestamp end_time) {
        this.coordonnee = coordonnee;
        this.prix = prix;
        this.id_reservation = id_reservation;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public reservation_place(int id_res_place, String coordonnee, float prix, int id_reservation,Timestamp start_time,Timestamp end_time) {
        this.id_res_place = id_res_place;
        this.coordonnee = coordonnee;
        this.prix = prix;
        this.id_reservation = id_reservation;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public reservation_place(String coordonnee, float prix,Timestamp start_time,Timestamp end_time) {
        this.coordonnee = coordonnee;
        this.prix = prix;
        
    }

    
    public int getId_res_place() {
        return id_res_place;
    }

    public void setId_res_place(int id_res_place) {
        this.id_res_place = id_res_place;
    }

    public String getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(String coordonnee) {
        this.coordonnee = coordonnee;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "reservation_place{" + "id_res_place=" + id_res_place + ", coordonnee=" + coordonnee + ", prix=" + prix + ", id_reservation=" + id_reservation + '}';
    }

   
}
