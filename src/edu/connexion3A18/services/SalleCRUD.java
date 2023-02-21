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
        String requete = "INSERT INTO salle ( longueur,  largeur,  id_cinema ,acces)" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

            st.setInt(1, s.getLongueur());
            st.setInt(2, s.getLargeur());

            st.setInt(3, s.getId_cinema());
            st.setBoolean(4, s.isAcces());

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
            String requete = "UPDATE salle SET acces=?  where id_cinema=?";
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
}
