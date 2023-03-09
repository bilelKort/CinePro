/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.services;

import cinepro.entities.Feedback;
import cinepro.interfaces.CommentaireCRUD;
import cinepro.utils.MyConnection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acer
 */
public class FeedbackCRUD implements CommentaireCRUD<Feedback> {

    @Override
    public void addCommentaire(Feedback t) {
        try {
            String requete = "INSERT INTO feedback (feedback,id_user,id_film,date)" + "VALUES (?,?,?,str_to_date(?, '%d/%m/%Y-%H:%i'))";
            PreparedStatement st = MyConnection.getInstance().getConnection().prepareStatement(requete);
            st.setString(1, t.getFeedback());
            st.setInt(2, t.getId_user());
            st.setInt(3, t.getId_film());
            st.setString(4, t.getDate());
            
          
    

            st.executeUpdate();
            System.out.println("Feedback ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public List<Feedback> commentaireList() {
        ArrayList<Feedback> myList = new ArrayList();
        String requete = "SELECT * FROM feedback";

        try {
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId_feedback(rs.getInt("id_feedback"));
                f.setFeedback(rs.getString("feedback"));
                f.setId_user(rs.getInt("Id_user"));
                f.setId_film(rs.getInt("Id_film"));
                f.setDate(rs.getString("date"));


                myList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("test"+myList);
        return myList;
    }
    @Override
    public List<Feedback> commentaireList(int id_film) {
        ArrayList<Feedback> myList = new ArrayList();
        String requete = "SELECT * FROM feedback where id_film='" + id_film + "'";

        try {
            Statement st = MyConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId_feedback(rs.getInt("id_feedback"));
                f.setFeedback(rs.getString("feedback"));
                f.setId_user(rs.getInt("Id_user"));
                 f.setId_film(rs.getInt("Id_film"));
                f.setDate(rs.getString("date"));
               

                myList.add(f);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("test"+myList);
        return myList;
    }

    @Override
    public void updateCommentaire(int id_feedback, String feedback, int id_user,int id_film, String date) {

        try {
            String requete = "UPDATE FEEDBACK SET feedback=?, id_user=?,id_film=?,date=str_to_date(?, '%d/%m/%Y-%H:%i') where id_feedback=?";
            PreparedStatement st = MyConnection.getInstance().getConnection().prepareStatement(requete);

            st.setString(1,feedback );
            st.setInt(2, id_user);
            st.setInt(3, id_film);
            st.setString(4, date);
            st.setInt(5, id_feedback);

            st.executeUpdate();
            System.out.println("Feedback Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    @Override
    public void deleteCommentaire(int id_feedback) {
        try {
            String requete="DELETE FROM FEEDBACK WHERE id_feedback=?";
            PreparedStatement st=MyConnection.getInstance().getConnection().prepareStatement(requete);
            st.setInt(1,id_feedback);
            st.executeUpdate();
            System.out.println("feedback deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
   public int FeedbackCounter(){
     
   
    // Liste des mots interdits
   String[] badWords = {"racisme", "projection", "descrimination"};

    
        // Compteur de feedbacks contenant un mot interdit
        int badCount =0;

        try {
            // Récupération des feedbacks
            Statement stmt = MyConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id_user, feedback FROM feedback");

            // Parcours des feedbacks
            while (rs.next()) {
                String feedback = rs.getString("feedback");
                // Vérification si le feedback contient un mot interdit
                for (String badWord : badWords) {
                    if (feedback.toLowerCase().contains(badWord)) {
                        badCount++;
                        break;
                    }
                }
                // Vérification si le nombre de feedbacks contenant un mot interdit a atteint 3
                if (badCount >= 2) {
                    System.out.println("Vous avez atteint le nombre maximum de feedbacks contenant des mots interdits.");
                    
                    
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            
        }
        return badCount;
        
    }

}
    
    
