/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.services;

import cinerpo.utils.CineproConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Home
 */
public class AbonnementDAO {
    
    private Connection connection;

    /*public ReservationDAO(Connection connection) {
        this.connection = connection;
    }*/

    public ResultSet getUser(int id_abonnement) throws SQLException {
        String requete = "SELECT user.id_user, user.nom, user.email "
                + "FROM user JOIN abonnement ON user.id_user = abonnement.id_user "
                + "WHERE abonnement.id_abonnement = ?";
        
         PreparedStatement stmp = CineproConnection.getInstance().getCnx()
                    .prepareStatement(requete);
       
        stmp.setInt(1,id_abonnement);
        return stmp.executeQuery();
    }
    
}
