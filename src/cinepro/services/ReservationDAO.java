package cinepro.services;


import cinepro.utils.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDAO {

    private Connection connection;

    /*public ReservationDAO(Connection connection) {
        this.connection = connection;
    }*/

    public ResultSet getUser(int reservationId) throws SQLException {
        String requete = "SELECT user.id_user, user.nom, user.email "
                + "FROM user JOIN reservation ON user.id_user = reservation.id_user "
                + "WHERE reservation.id_reservation = ?";
        
         PreparedStatement stmp = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
       
        stmp.setInt(1, reservationId);
        return stmp.executeQuery();
    }
}
