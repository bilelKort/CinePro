/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.services;

import cinepro.entities.reservation;
import cinepro.interfaces.entityCRUD;
import cinepro.utils.MyConnection;
import edu.cinepro.entities.UserSession;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kortb
 */
public class reservationCRUD implements entityCRUD<reservation>{

    public int checkUser(reservation r){
        int count=0;
        try {
            String requete = "SELECT COUNT(*) FROM user u WHERE u.id_user = ? ";
                                        
            PreparedStatement stmp = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            
            stmp.setInt(1, r.getId_user());
            
            ResultSet rs = stmp.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
           return count;

    }
    
public int checkFilm(reservation r){
   int count=0;
        try {
            String requete = "SELECT COUNT(*) FROM film f WHERE f.id_film = ?";

            PreparedStatement stmp = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            
            stmp.setInt(1, r.getId_film());
            
            ResultSet rs = stmp.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(reservationCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
           return count;  
}
    
    
    @Override
    public void addEntity(reservation r) {
        try {
    int userId = r.getId_user();
    int filmId = r.getId_film();
    boolean state = r.isState();
    float prixFinal = 0f;

    // Check if the user exists
    if (checkUser(r) == 0) {
          System.out.println("user doesn't exists");
    System.out.println("r.getId_user() = " + r.getId_user());
    System.out.println("count = " + checkUser(r));
    }

    // Check if the film exists
    if (checkFilm(r) == 0) {
        System.out.println("Film does not exist");
        return;
    }

    // Retrieve the start and end times from the projection table
    String selectProjectionQuery = "SELECT date_debut, date_fin FROM projection WHERE id_projection = ?";
    PreparedStatement selectProjectionStmt = MyConnection.getInstance().getConnection().prepareStatement(selectProjectionQuery);
    selectProjectionStmt.setInt(1, r.getId_projection());
    ResultSet projectionResult = selectProjectionStmt.executeQuery();
    projectionResult.next();    // Insert the reservation into the database
    String insertReservationQuery = "INSERT INTO reservation (Prix_final, id_user, id_film, state, start_time, end_time, id_projection) VALUES (?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement insertReservationStmt = MyConnection.getInstance().getConnection().prepareStatement(insertReservationQuery);
    insertReservationStmt.setFloat(1, prixFinal);
    insertReservationStmt.setInt(2, userId);
    insertReservationStmt.setInt(3, filmId);
    insertReservationStmt.setBoolean(4, state);
    insertReservationStmt.setInt(7, r.getId_projection());
    Timestamp startTime = projectionResult.getTimestamp("date_debut");
    Timestamp endTime = projectionResult.getTimestamp("date_fin");

    insertReservationStmt.setTimestamp(5, startTime);
    insertReservationStmt.setTimestamp(6, endTime);

    int rowsAffected = insertReservationStmt.executeUpdate();

    if (rowsAffected > 0) {
        System.out.println("Reservation added successfully");
    } else {
        System.out.println("Failed to add reservation");
    }
} catch (SQLException ex) {
    System.out.println(ex.getMessage());
}
    }

    @Override
    public List<reservation> entitiesList() {
        ArrayList<reservation> myList = new ArrayList();

        try {
            String requete="";
            if (UserSession.getInstace().getRole().equals("Gerant")) {
                requete = "SELECT * FROM reservation r left join projection p on r.id_projection=p.id_projection left join salle s on s.id_salle=p.id_salle left join cinema c on c.id_cinema=s.id_cinema where c.id_user='" + UserSession.getInstace().getId() + "'";
            }else if (UserSession.getInstace().getRole().equals("Client")) {
                requete = "SELECT * FROM reservation where id_user='" + UserSession.getInstace().getId() + "'";
            }
            Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while(rs.next()){
                reservation p =new reservation();

                p.setId_reservation(rs.getInt(1));
                p.setPrix_final(rs.getFloat(2));
                p.setId_user(rs.getInt(3));
                p.setId_film(rs.getInt(4));
                p.setState(rs.getBoolean(5));
                p.setStart_time(rs.getTimestamp(6));
                p.setEnd_time(rs.getTimestamp(7));
                p.setId_projection(rs.getInt(8));

                myList.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    
    public void deleteEntity(int id_reservation) {
        /*reservation_placeCRUD pcd = new reservation_placeCRUD();
        reservation_snackCRUD pcs = new reservation_snackCRUD();
        pcd.deleteEntity(id_res_place);
        pcs.deleteEntity(id_res_snack);
        */
        try {
            String requete="DELETE FROM reservation WHERE id_reservation=?";
            PreparedStatement st = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            
            st.setInt(1,id_reservation);
            //st.setInt(2,id_user);
            //st.setInt(3,id_film);

            st.executeUpdate();
            System.out.println("reservation deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
    
     public void updateEntity(int id_reservation,float prix_final,int id_user,int id_film,boolean state, int id_projection){
        
         try{
        String requete = "UPDATE reservation set prix_final = ?,id_user = ?,id_film = ?,state  = ?,id_projection=? WHERE id_reservation = ?";
        PreparedStatement st=MyConnection.getInstance()
                .getConnection().prepareStatement(requete);
            
              st.setFloat(1, prix_final);
              st.setInt(2, id_user);
              st.setInt(3, id_film);
              st.setInt(7, id_projection);
              st.setBoolean(5, state);
              
        st.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }
     
     public void updateEntityPrix(int id_reservation,float prix_final,int id_user){
        
         try{
        String requete = "UPDATE reservation set prix_final = ?,id_user = ? WHERE id_reservation = ?";
        PreparedStatement st=MyConnection.getInstance()
                .getConnection().prepareStatement(requete);
            
              st.setFloat(1, prix_final);
              st.setInt(2, id_user);
              st.setInt(3, id_reservation);

              
        st.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

    public static Map<String, Integer> countFilmReservations() {
        try {
            String requete = "SELECT f.nom, COUNT(*) AS reservations_count " +
                    "FROM reservation r " +
                    "INNER JOIN film f ON r.id_film = f.id_film " +
                    "GROUP BY f.nom";
            
        PreparedStatement st=MyConnection.getInstance()
                    .getConnection().prepareStatement(requete);
            ResultSet rs = st.executeQuery(requete);
            
            Map<String, Integer> filmReservationCounts = new HashMap<>();

            while (rs.next()) {
                String filmName = rs.getString("nom");
                int reservationsCount = rs.getInt("reservations_count");
                filmReservationCounts.put(filmName, reservationsCount);
            }

            return filmReservationCounts;
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
         return null;
        }


        public int getlast() {
            try {
                String requete="select id_reservation from reservation order by id_reservation desc";
                Statement st=MyConnection.getInstance()
                        .getConnection().createStatement();


                ResultSet rs = st.executeQuery(requete);
                while (rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return 0;
        }
    
     
     
}
