/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.services;

import cinerpo.entities.Abonnement;
import cinerpo.entities.TypeAbonnement;
import cinerpo.interfaces.EntityCRUD;
import cinerpo.utils.CineproConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Home
 */
public class AbonnementCRUD implements EntityCRUD<Abonnement> {

    @Override
    public void addEntity(Abonnement a) {
        
        try {
            String requete="INSERT INTO abonnement(id_user,type,dateDebut,dateExpiration)VALUES(?,?,?,?)";
            PreparedStatement st=CineproConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,a.getId_user());
            st.setInt(2,a.getType().getNb_mois());
            
            Timestamp td = new Timestamp(System.currentTimeMillis());
        Timestamp te=new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(te);
        switch(a.getType().getNb_mois()){
                case 1 :
                    cal.add(Calendar.MONTH,1);
                    break;
                case 3 :
                    cal.add(Calendar.MONTH,3);
                    break;
                case 6 :
                    cal.add(Calendar.MONTH,6);
                    break;
                case 12 :
                    cal.add(Calendar.YEAR,1);
                    break;
        }
        te.setTime(cal.getTimeInMillis());
            st.setTimestamp(3,td);
            st.setTimestamp(4,te);
            st.executeUpdate();
            System.out.println("Abonnement ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Abonnement> entitiesList() {
         ArrayList<Abonnement> myList= new ArrayList();
        String requete="SELECT * FROM abonnement";
        try {
            Statement st =CineproConnection.getInstance().getCnx().createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Abonnement a=new Abonnement();
                TypeAbonnement ta=new TypeAbonnement();
                a.setId_abonnement(rs.getInt(1));
                a.setId_user(rs.getInt(2));
                a.setType(ta);
                ta.setNb_mois(rs.getInt(3));
                a.setDateDebut(rs.getTimestamp(4));
                a.setDateExpiration(rs.getTimestamp(5));
               
                
                myList.add(a);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void updateEntity(Abonnement a, int id) {
        try {
            String requete="UPDATE abonnement SET type=?, dateDebut=?,dateExpiration=? where id_abonnement=?";
                    //"UPDATE abonnement SET type=? , dateDebut=? , dateExpiration=? , etat=? where id_abonnement=?";
            PreparedStatement st=CineproConnection.getInstance().getCnx().prepareStatement(requete);
            
            Timestamp td= new Timestamp(System.currentTimeMillis());
            st.setInt(1,a.getType().getNb_mois());
            st.setTimestamp(2,td);
            Timestamp te=new Timestamp(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(te);
            switch(a.getType().getNb_mois()){
                case 1 :
                    cal.add(Calendar.MONTH,1);
                    break;
                case 3 :
                    cal.add(Calendar.MONTH,3);
                    break;
                case 6 :
                    cal.add(Calendar.MONTH,6);
                    break;
                case 12 :
                    cal.add(Calendar.YEAR,1);
                    break;
        }
         
        te.setTime(cal.getTimeInMillis());
        
        st.setTimestamp(3,te);
         
        st.setInt(4,id);
        st.executeUpdate();
            System.out.println("Abonnement Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }

    @Override
    public void deleteEntity(int id) {
        try {
            String requete="DELETE FROM abonnement WHERE id_abonnement=?";
            PreparedStatement st=CineproConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("Abonnement deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    
    

  
    
}
