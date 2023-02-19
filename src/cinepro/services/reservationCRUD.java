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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kortb
 */
public class reservationCRUD implements entityCRUD<reservation>{

    @Override
    public void addEntity(reservation r) {
        try {
            String requete = "INSERT INTO reservation (Prix_final,id_user,id_film,state)" + "VALUES (? ,?, ?, ?)";
            
            //+ "SELECT SUM(rp.prix + rs.prix) as prix_final FROM reservation_place rp JOIN reservation_snack rs ON rp.id_reservation = rs.id_reservation" 
            PreparedStatement st = cineproConnexion.getInstance().getCnx()
                    .prepareStatement(requete);
            
            st.setFloat(1, 0f);
            st.setInt(2, r.getId_user());
            st.setInt(3, r.getId_film());
            st.setBoolean(4, r.isState());

            st.executeUpdate();
            
            System.out.println("reservation ajoute");
            
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
                p.setPrix_final(rs.getInt(2));
                p.setId_user(rs.getInt(3));
                p.setId_film(rs.getInt(4));
                p.setState(rs.getBoolean(5));
                
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }       
        return myList;
    }
    
    public void deleteEntity(int id_reservation,int id_res_place,int id_res_snack) {
        reservation_placeCRUD pcd = new reservation_placeCRUD();
        reservation_snackCRUD pcs = new reservation_snackCRUD();
        pcd.deleteEntity(id_res_place);
        pcs.deleteEntity(id_res_snack);
        
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
}
