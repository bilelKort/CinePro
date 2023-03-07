package edu.connexion3A18.services;

import edu.cinepro.entities.cinema;
import edu.cinepro.entities.snack;
import edu.cinepro.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rayen
 */
public class SnackCRUD {

    public void addEntity(snack s) {
        String requete = "INSERT INTO snack ( nom,  prix,  quantite,  photo,  id_cinema )" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

            st.setString(1, s.getNom());
            st.setFloat(2, s.getPrix());
            st.setInt(3, s.getQuantite());
            st.setString(4, s.getPhoto());
            st.setInt(5, s.getId_cinema());

            st.executeUpdate();
            System.out.println("snack ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    
      public void addEntitycsv(snack s) {
          int count=0;
            try {
           String requete1 = "SELECT count(*) FROM snack where nom='"+s.getNom()+"'";
            Statement st1 = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st1.executeQuery(requete1);
            while (rs.next()) {
                count = count + rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

          
          
          if(count==0){
              String requete = "INSERT INTO snack ( nom,  prix,  quantite,  photo,  id_cinema )" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            

            st.setString(1, s.getNom());
            st.setFloat(2, s.getPrix());
            st.setInt(3, s.getQuantite());
            st.setString(4, s.getPhoto());
            st.setInt(5, s.getId_cinema());

            st.executeUpdate();
            System.out.println("snack ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          }
          else  {
          try {
              
              System.out.println(s.getQuantite());
            String requete="UPDATE snack SET  quantite=quantite+?   where nom=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
                        st.setInt(1,s.getQuantite());

            st.setString(2,s.getNom());
            
            st.executeUpdate();
            System.out.println("snack Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
          
          
          }
        

    }

    public List<snack> entitiesList() {
        ArrayList<snack> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM snack ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                snack s = new snack();
                s.setId_snack(rs.getInt(1));
                s.setNom(rs.getString("nom"));
                s.setPrix(rs.getFloat("prix"));

                s.setQuantite(rs.getInt("quantite"));
                s.setPhoto(rs.getString("photo"));
                s.setId_cinema(rs.getInt("id_cinema"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    
    
    public snack snackbyid(int id) {
                snack s = new snack();

        try {
            String requete = "SELECT * FROM snack where id_snack=" +id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
                s.setId_snack(rs.getInt("id_snack"));
                s.setNom(rs.getString("nom"));
                s.setPrix(rs.getFloat("prix"));

                s.setQuantite(rs.getInt("quantite"));
                s.setPhoto(rs.getString("photo"));


            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }
    
    
    
    
    
    
    
     public List<snack> entitiesList2(int id) {
        ArrayList<snack> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM snack where id_cinema="+id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                snack s = new snack();
                s.setId_snack(rs.getInt(1));
                s.setNom(rs.getString("nom"));
                s.setPrix(rs.getFloat("prix"));

                s.setQuantite(rs.getInt("quantite"));
                s.setPhoto(rs.getString("photo"));
                s.setId_cinema(rs.getInt("id_cinema"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
     
     
     
     
     public snack entitiesList3(int id) {
        ArrayList<snack> myList = new ArrayList();

        try {
            String requete = "SELECT * FROM snack where id_snack="+id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                snack s = new snack();
                s.setId_snack(rs.getInt(1));
                s.setNom(rs.getString("nom"));
                s.setPrix(rs.getFloat("prix"));

                s.setQuantite(rs.getInt("quantite"));
                s.setPhoto(rs.getString("photo"));
                s.setId_cinema(rs.getInt("id_cinema"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList.get(0);
    }
     
     
 public void updateEntity(int id_snack, snack c ) {




        try {
            String requete="UPDATE snack SET nom=?, quantite=? ,prix=?  where id_snack=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setString(1,c.getNom());
            st.setInt(2,c.getQuantite());
            st.setFloat(3,c.getPrix());
            st.setInt(4,id_snack);
            
            st.executeUpdate();
            System.out.println("snack Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEntity(int id) {
        try {
            String requete="DELETE FROM snack WHERE id_snack=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("snack deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
