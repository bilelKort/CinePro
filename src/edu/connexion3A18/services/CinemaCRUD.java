/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.connexion3A18.services;

import edu.cinepro.entities.cinema;
import edu.cinepro.entities.salle;
import edu.cinepro.interfaces.EntityCRUD;
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
public class CinemaCRUD implements EntityCRUD<cinema> {

    @Override
    public void addEntity(cinema c) {
        String requete = "INSERT INTO cinema (id_user	, nom ,	localisation ,	description ,	photo) " + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            st.setInt(1, c.getId_user());

            st.setString(2, c.getNom());
            st.setString(3, c.getLocalisation());
            st.setString(4, c.getDescription());
            st.setString(5, c.getPhoto());

            st.executeUpdate();
            System.out.println("cinema ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<cinema> entitiesList() {
        ArrayList<cinema> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM cinema ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                cinema c = new cinema();
                c.setId_cinema(rs.getInt(1));
                c.setId_user(rs.getInt("id_cinema"));
                c.setNom(rs.getString("nom"));

                c.setLocalisation(rs.getString("localisation"));
                c.setDescription(rs.getString("description"));
                c.setPhoto(rs.getString("photo"));

                myList.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public cinema cinemabyid(int id) {
        cinema c =new cinema();

        try {
            String requete = "SELECT * FROM cinema where id_cinema=" + id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                
            
            c.setId_cinema(rs.getInt(1));
            c.setId_user(rs.getInt("id_cinema"));
            c.setNom(rs.getString("nom"));

            c.setLocalisation(rs.getString("localisation"));
            c.setDescription(rs.getString("description"));
            c.setPhoto(rs.getString("photo"));
}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    /* 
    public List<cinema> entitiesListavecsalle() {
        ArrayList<cinema> myList= new ArrayList();
        
        try {
            
            
            String requete = "SELECT * FROM cinema INNER JOIN salle WHERE cinema.id_cinema = salle.id_cinema; ";
            Statement st =  MyConnection.getInstance().getCnx()
                    .createStatement();
            
            ResultSet rs= st.executeQuery(requete);
            while(rs.next()){
              ArrayList<salle> salle = new ArrayList<salle>();  
            cinema c = new cinema();
            salle s = new salle();
            c.setId_cinema(rs.getInt(1));
           c.setId_user(rs.getInt("id_cinema"));
           c.setNom(rs.getString("nom"));
                      c.setLocalisation(rs.getString("localisation"));
                      c.setDescription(rs.getString("description"));
                      c.setPhoto(rs.getString("photo"));
                      s.setId_salle(rs.getInt("id_salle"));

                s.setId_cinema(rs.getInt("id_cinema"));
                s.setLongueur(rs.getInt("longueur"));
                s.setLargeur(rs.getInt("Largeur"));
                s.setAcces(rs.getBoolean("acces"));
                salle s2= (salle) s;

                salle.add(s2);

                c.setListsalle(salle);
                      

           myList.add(c);
           
            }
            salle s4=(salle)myList.get(0).getListsalle().get(0);
            System.out.println(s4.getId_salle());
                       // System.out.println(myList);

           //  SalleCRUD salle = new SalleCRUD();
          //   List<salle> i= salle.entitiesList();
          //  System.out.println(i);
            
           

            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
return myList ;
        }
    
     */
    public void updateEntity(int id, String nom, String localisation, String description, String photo) {

        try {
            String requete = "UPDATE cinema SET nom=?, localisation=?,description=? ,photo=?  where id_cinema=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);

            st.setString(1, nom);
            st.setString(2, localisation);
            st.setString(3, description);
            st.setString(4, photo);
            st.setInt(5, id);

            st.executeUpdate();
            System.out.println("cinema Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEntity(int id) throws SQLException {
        
        PreparedStatement st0 = MyConnection.getInstance().getCnx().prepareStatement("DELETE FROM salle WHERE id_cinema=?");
            st0.setInt(1, id);
            st0.executeUpdate(); 
        
        try {
            
            
            String requete = "DELETE FROM cinema WHERE id_cinema=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("cinema deleted!");
        } catch (SQLException ex) {
 
        }
    }
}
