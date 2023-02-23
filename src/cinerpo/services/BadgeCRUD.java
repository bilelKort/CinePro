/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.services;

import cinerpo.entities.Badge;
import cinerpo.entities.TypeAbonnement;
import cinerpo.entities.Type_Badge;
import cinerpo.interfaces.EntityCRUD;
import cinerpo.utils.CineproConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Home
 */
public class BadgeCRUD implements EntityCRUD<Badge> {

    @Override
    public void addEntity(Badge t) {
        try {
            String requete="INSERT INTO badge(type,nbr_reservation,id_user)VALUES(?,?,?)";
            PreparedStatement st=CineproConnection.getInstance().getCnx().prepareStatement(requete);
            switch(t.getType()){
                case gold:
                    st.setString(1,"gold");
                    st.setInt(2,100);
                    break;
                case silver:
                    st.setString(1,"silver");
                    st.setInt(2,50);
                    break;
                case bronze:
                    st.setString(1,"bronze");
                    st.setInt(2,10);
            }
           // st.setInt(2,t.getNbr_reservation());
            st.setInt(3,t.getId_user());
            st.executeUpdate();
            System.out.println("Badge ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List entitiesList() {
          ArrayList<Badge> myList= new ArrayList();
        String requete="SELECT * FROM Badge";
        try {
            Statement st =CineproConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs=st.executeQuery(requete);
            while(rs.next()){
                Badge b=new Badge();
                b.setId_badge(rs.getInt(1));
                switch(rs.getString(2)){
                  case "gold":
                     b.setType(Type_Badge.gold);
                     break;
                  case "silver":
                      b.setType(Type_Badge.silver);
                      break;
                  case "bronze":
                      b.setType(Type_Badge.bronze);
                      break;
                }
                b.setNbr_reservation(rs.getInt(3));
                b.setId_user(rs.getInt(4));
                myList.add(b);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    @Override
    public void updateEntity(Badge b , int id) {
       
        try {
            String requete="UPDATE BADGE SET type=?, nbr_reservation=? where id_user=?";
            PreparedStatement st=CineproConnection.getInstance().getCnx().prepareStatement(requete);
            switch(b.getType()){
                case gold:
                    st.setString(1,"gold");
                    break;
                case silver:
                    st.setString(1,"silver");
                    break;
                case bronze:
                    st.setString(1,"bronze");
            }
            st.setInt(2,b.getNbr_reservation());
            st.setInt(3,id);
            st.executeUpdate();
            System.out.println("Badge Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       
            
        
        
    }

    @Override
    public void deleteEntity(int id) {
        try {
            String requete="DELETE FROM BADGE WHERE id_user=?";
            PreparedStatement st=CineproConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("Badge deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    
    
    
   

    
    
    }

    

