/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.services;

import cinepro.entities.Reclamation;
import cinepro.interfaces.EntityCRUD;
import cinepro.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class ReclamationCRUD implements EntityCRUD<Reclamation> {

    @Override
    public void addEntity(Reclamation t) {
        try {
            String requete = "INSERT INTO reclamation (description,id_user,id_film,date,etat)" + "VALUES (?,?,?,str_to_date(?, '%d/%m/%Y-%H:%i'),?)";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setString(1, t.getDescription());
            st.setInt(2, t.getId_user());
            st.setInt(3, t.getId_film());
            st.setString(4, t.getDate());
            st.setBoolean(5, t.isEtat());

            st.executeUpdate();
            System.out.println("Reclamation ajoutée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> EntityList() {
        ArrayList<Reclamation> myList = new ArrayList();
        String requete = "SELECT * FROM reclamation";

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation f = new Reclamation();
                f.setId_reclamation(rs.getInt("id_reclamation"));
                f.setDescription(rs.getString("description"));
                f.setId_user(rs.getInt("Id_user"));
                f.setId_film(rs.getInt("Id_film"));
                f.setDate(rs.getString("date"));
                f.setEtat(rs.getBoolean("etat"));

                myList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("test" + myList);
        return myList;
    }

    @Override
    public void updateEntity(int id_reclamation, String description, int id_user, int id_film, String date, boolean etat) {

        try {
            String requete = "UPDATE RECLAMATION SET description=?, id_user=?,id_film=?,date=str_to_date(?, '%d/%m/%Y-%H:%i'),etat=? where id_reclamation=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);

            st.setString(1, description);
            st.setInt(2, id_user);
            st.setInt(3, id_film);
            st.setString(4, date);
            st.setInt(6, id_reclamation);
            st.setBoolean(5, etat);

            st.executeUpdate();
            System.out.println("Reclamation Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void deleteEntity(int id_reclamation) {
        try {
            String requete = "DELETE FROM RECLAMATION WHERE id_reclamation=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            st.setInt(1, id_reclamation);
            st.executeUpdate();
            System.out.println("Reclamation deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   /* public void colorerEtat() {
        try {
            String sql = "SELECT description , etat FROM reclamation";
        
        // Création de la déclaration
        Statement stmt;
       
        stmt = MyConnection.getInstance().getCnx().createStatement();
        
        
         //Exécution de la requête SQL et récupération du résultat
        ResultSet rs;
        
        rs = stmt.executeQuery(sql);
        
        // Parcours des résultats et coloration en fonction de l'état
        while (rs.next()) {
        String reclamation = rs.getString("description");
        boolean etat = rs.getBoolean("etat");
        
        if (etat) {
        System.out.println("<font color='green'>" + reclamation + "</font>"); // couleur verte
        } else {
        System.out.println("<font color='red'>" + reclamation + "</font>"); // couleur rouge
        }
        }
        }
        }
        catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
        }

        }
*/
}
    