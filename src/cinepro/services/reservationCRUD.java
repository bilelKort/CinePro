/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.services;

import cinepro.entities.reservation;
import cinepro.interfaces.entityCRUD;
import cinepro.utils.cineproConnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kortb
 */
public class reservationCRUD implements entityCRUD<reservation>{

    public int checkUser(reservation r){
        int count=0;
        try {
            String requete = "SELECT COUNT(*) FROM user u WHERE u.id_user = ? ";
                                        
            PreparedStatement stmp = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            
            stmp.setInt(1, r.getId_user());
            
            ResultSet rs = stmp.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
           return count;

    }
    
public int checkFilm(reservation r){
   int count=0;
        try {
            String requete = "SELECT COUNT(*) FROM film f WHERE f.id_film = ?";
 
            PreparedStatement stmp = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            
            stmp.setInt(1, r.getId_film());
            
            ResultSet rs = stmp.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
           return count;  
}
    
    
    @Override
    public void addEntity(reservation r) {
        try {
            String requete = "INSERT INTO reservation (Prix_final,id_user,id_film,state,start_time,end_time)" + "VALUES (? ,?, ?, ?, ?, ?)";
            int count = checkUser(r);
            int count2=checkFilm(r);
            
            if (count == 0) {
                System.out.println("user doesn't exists");
            }
            
            if(count2==0){
                System.out.println("film doesn't exists"); 
            }
            
            else if(count>0 && count2>0){
               PreparedStatement st = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setFloat(1, 0f);
            st.setInt(2, r.getId_user());
            st.setInt(3, r.getId_film());
            st.setBoolean(4, r.isState());
            st.setTimestamp(5, r.getStart_time());
            st.setTimestamp(6, r.getEnd_time());

            st.executeUpdate();
            
            System.out.println("reservation ajoute"); 
            }        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<reservation> entitiesList() {
         ArrayList<reservation> myList = new ArrayList();
        
        try {
            String requete = "SELECT * FROM reservation";
            Statement st = cineproConnexion.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                reservation p =new reservation();
                
                p.setId_reservation(rs.getInt(1));
                p.setPrix_final(rs.getFloat(2));
                p.setId_user(rs.getInt(3));
                p.setId_film(rs.getInt(4));
                p.setState(rs.getBoolean(5));
                p.setStart_time(rs.getTimestamp(6));
                p.setEnd_time(rs.getTimestamp(7));
                                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        return myList;
    }
    
    public void deleteEntity(int id_reservation) {
        /*reservation_placeCRUD pcd = new reservation_placeCRUD();
        reservation_snackCRUD pcs = new reservation_snackCRUD();
        pcd.deleteEntity(id_res_place);
        pcs.deleteEntity(id_res_snack);
        */
        try {
            String requete="DELETE FROM reservation WHERE id_reservation=?";
            PreparedStatement st=cineproConnexion.getInstance()
                    .getCnx().prepareStatement(requete);
            
            st.setInt(1,id_reservation);
            //st.setInt(2,id_user);
            //st.setInt(3,id_film);

            st.executeUpdate();
            System.out.println("reservation deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
    
     public void updateEntity(int id_reservation,float prix_final,int id_user,int id_film,boolean state,Timestamp start_time,Timestamp end_time){
        
         try{
        String requete = "UPDATE reservation set prix_final = ?,id_user = ?,id_film = ?,state  = ?,start_time = ?,end_time = ? WHERE id_reservation = ?";
        PreparedStatement st=cineproConnexion.getInstance()
                .getCnx().prepareStatement(requete);
            
              st.setFloat(1, prix_final);
              st.setInt(2, id_user);
              st.setInt(3, id_film);
              st.setBoolean(4, state);
              st.setTimestamp(5, start_time);
              st.setTimestamp(6, end_time);

        st.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
}
