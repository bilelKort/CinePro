/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.services;

import cinepro.entities.*;
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
public class reservation_snackCRUD implements entityCRUD<reservation_snack>{

     @Override
    public void addEntity(reservation_snack r) {
        try {
            String requete = "INSERT INTO reservation_snack (quantite,prix,id_reservation,id_snack)" + "VALUES (?, ?, ?, ?)";
            PreparedStatement st = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setInt(1, r.getQuantite());
            st.setFloat(2, r.getPrix());
            st.setInt(3, r.getId_reservation());
            st.setInt(4, r.getId_snack());
            
            st.executeUpdate();
            
             String requete2 = "UPDATE reservation SET prix_final = (prix_final + ?) WHERE (id_reservation=?)";
             PreparedStatement st2 = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete2);

             st2.setFloat(1, r.getPrix());             
             st2.setInt(2, r.getId_reservation());
             
             st2.executeUpdate();
            
            System.out.println("reservation_snack ajoute");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<reservation_snack> entitiesList() {
         ArrayList<reservation_snack> myList = new ArrayList();
        
        try {
            String requete = "SELECT * FROM reservation_snack";
            Statement st = cineproConnexion.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            
            while(rs.next()){
                reservation_snack p =new reservation_snack();

                p.setId_res_snack(rs.getInt(1));
                p.setQuantite(rs.getInt(2));
                p.setPrix(rs.getInt(3));
                p.setId_reservation(rs.getInt(4));
                p.setId_snack(rs.getInt(5));
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        return myList;
    }
    
    public void deleteEntity(int id_reservation) {
        try {
            String requete="DELETE FROM reservation_snack WHERE id_res_snack=?";
            PreparedStatement st=cineproConnexion.getInstance()
                    .getCnx().prepareStatement(requete);
            
            st.setInt(1,id_reservation);
            st.executeUpdate();
            System.out.println("reservation_snack deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }

    public void updateEntity(int quantite,float prix,int id_reservation,int id_snack,int id_res_snack){
        try{
        String requete = "UPDATE reservation_snack set quantite=?,prix=?,id_reservation=?,id_snack=? WHERE id_res_snack=?";
        PreparedStatement st=cineproConnexion.getInstance()
                .getCnx().prepareStatement(requete);
            
        st.setInt(1, quantite);
        st.setFloat(2, prix);
        st.setInt(3, id_reservation);
        st.setInt(4, id_snack);
        st.setInt(5, id_res_snack);
        
        st.executeUpdate();
        
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
}
