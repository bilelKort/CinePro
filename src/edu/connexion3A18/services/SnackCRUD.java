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
        String requete = "INSERT INTO snack ( nom,  prix,  quantite,  photo,  stock,  id_cinema )" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement st = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);

            st.setString(1, s.getNom());
            st.setFloat(2, s.getPrix());
            st.setInt(3, s.getQuantite());
            st.setString(4, s.getPhoto());
            st.setInt(5, s.getStock());
            st.setInt(6, s.getId_cinema());

            st.executeUpdate();
            System.out.println("snack ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
                s.setStock(rs.getInt("stock"));
                s.setId_cinema(rs.getInt("id_cinema"));

                myList.add(s);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
 public void updateEntity(int id_snack, String nom,float prix , int quantite,int stock, int id_cinema ) {




        try {
            String requete="UPDATE snack SET nom=?, quantite=?,stock=? ,prix=?  where id_snack=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setString(1,nom);
            st.setInt(2,quantite);
            st.setInt(3,stock);
            st.setFloat(4,prix);
            st.setInt(5,id_snack);
            
            st.executeUpdate();
            System.out.println("snack Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteEntity(int id) {
        try {
            String requete="DELETE FROM snack WHERE id_cinema=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1,id);
            st.executeUpdate();
            System.out.println("snack deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
