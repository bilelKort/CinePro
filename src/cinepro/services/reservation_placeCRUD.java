/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.services;

import cinepro.entities.reservation;
import cinepro.entities.reservation_place;
import cinepro.interfaces.entityCRUD;
import cinepro.utils.cineproConnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kortb
 */
public class reservation_placeCRUD implements entityCRUD<reservation_place>{

    @Override
    public void addEntity(reservation_place r) {
        ResultSet rs = null;
        try {
             String query = "SELECT COUNT(*) FROM reservation_place " +
                       "WHERE coordonnee = ? AND id_reservation = ?";
              PreparedStatement stmp = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(query);
              
        stmp.setString(1, r.getCoordonnee());
        stmp.setInt(2, r.getId_reservation());
        //st.setTimestamp(4, Timestamp.valueOf(startTime.plusMinutes(120))); // End time of new reservation
        //st.setTimestamp(5, Timestamp.valueOf(startTime)); // Start time of new reservation
        rs = stmp.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            System.out.println("You have already reserved a seat for another film at the same time.");
        }
            
        else{
           String requete = "INSERT INTO reservation_place (coordonnee,prix,id_reservation)" + "VALUES (?, ?, ?)";
            
            PreparedStatement st = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setString(1, r.getCoordonnee());
            st.setFloat(2, r.getPrix());
            st.setInt(3, r.getId_reservation());

            st.executeUpdate();
             
             String requete2 = "UPDATE reservation SET prix_final = (prix_final + ?) WHERE (id_reservation=?)";
             PreparedStatement st2 = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete2);

             st2.setFloat(1, r.getPrix());             
             st2.setInt(2, r.getId_reservation());
             
             st2.executeUpdate();
             System.out.println("reservation ajoute");
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<reservation_place> entitiesList() {
         ArrayList<reservation_place> myList = new ArrayList();
        
        try {
            String requete = "SELECT * FROM reservation_place";
            Statement st = cineproConnexion.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                reservation_place p =new reservation_place();

                p.setCoordonnee(rs.getString(2));
                p.setPrix(rs.getInt(3));
                p.setId_reservation(rs.getInt(4));
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        return myList;
    }
    
     public void deleteEntity(int id_reservation) {
        try {
            String requete="DELETE FROM reservation_place WHERE id_res_place=?";
            PreparedStatement st=cineproConnexion.getInstance()
                    .getCnx().prepareStatement(requete);
            
            st.setInt(1,id_reservation);
            st.executeUpdate();
            System.out.println("reservation_place deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }

     public void updateEntity(int id_res_place,String coordonnee,float prix,int id_reservation){
        try{
        String requete = "UPDATE reservation_place set id_res_place=?,coordonnee=?,prix=?,id_reservation=?";
        PreparedStatement st=cineproConnexion.getInstance()
                .getCnx().prepareStatement(requete);
            
        st.setInt(1, id_res_place);
        st.setString(2, coordonnee);
        st.setFloat(3, prix);
        st.setInt(4, id_reservation);
        
        st.executeUpdate();
        
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
}
