/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connexion3A18.services;

import edu.cinepro.entities.salle;
import edu.cinepro.entities.snack;
import edu.cinepro.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rayen
 */
public class SalleCRUD {

    public void addEntity(salle s) {
        String requete = "INSERT INTO salle ( nom,longueur,  largeur,  id_cinema ,acces)" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            st.setString(1, s.getNom());

            st.setInt(2, s.getLongueur());
            st.setInt(3, s.getLargeur());

            st.setInt(4, s.getId_cinema());
            st.setBoolean(5, false);

            st.executeUpdate();
            System.out.println("Salle ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public List<salle> entitiesList() {
        ArrayList<salle> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM salle ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                salle s = new salle();

                s.setId_salle(rs.getInt("id_salle"));

                s.setId_cinema(rs.getInt("id_cinema"));
                s.setNom(rs.getString("nom"));
                s.setLongueur(rs.getInt("longueur"));
                s.setLargeur(rs.getInt("Largeur"));
                s.setAcces(rs.getBoolean("acces"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    
    
     public List<salle> entitiesList2(int id) {
        ArrayList<salle> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM salle where id_cinema=" +id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                salle s = new salle();

                s.setId_salle(rs.getInt("id_salle"));
                s.setNom(rs.getString("nom"));

                s.setId_cinema(rs.getInt("id_cinema"));
                s.setLongueur(rs.getInt("longueur"));
                s.setLargeur(rs.getInt("Largeur"));
                s.setAcces(rs.getBoolean("acces"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
     
     
     
     public List<salle> entitiesList3(int id) {
        ArrayList<salle> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM salle where id_salle=" +id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                salle s = new salle();

                s.setId_salle(rs.getInt("id_salle"));
                s.setNom(rs.getString("nom"));

                s.setId_cinema(rs.getInt("id_cinema"));
                s.setLongueur(rs.getInt("longueur"));
                s.setLargeur(rs.getInt("Largeur"));
                s.setAcces(rs.getBoolean("acces"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
     


    public void updateEntity(int id, Boolean acces) {

        try {
            String requete = "UPDATE salle SET acces=?  where id_salle=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setBoolean(1, acces);

            st.setInt(2, id);

            st.executeUpdate();
            System.out.println("salle acces Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public void deleteEntity(int id) {
        try {
            String requete="DELETE FROM salle WHERE id_cinema=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("salle deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteEntity2(int id) {
        try {
            String requete="DELETE FROM salle WHERE id_salle=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("salle deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
