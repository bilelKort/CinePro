/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.services;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.interfaces.EntityCRUD;
import edu.cinepro.utils.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author MOEµNESS
 */

public class CineproCRUD implements EntityCRUD<Cinepro> {
    
    // Insertion
    @Override
    public void addEntity(Cinepro c) {
        try {
            String requete = "INSERT INTO user (id_user, email, password, nom, prenom, date_naissance, pseudo, tel, role, montant)"
                    + "VALUES (?,?,?,?,?,STR_TO_DATE(?,'%Y-%m-%d'),?,?,?,?)";
            
            
            PreparedStatement st =  MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setInt(1, c.getId_user());
            st.setString(2, c.getEmail());
            st.setString(3, c.getPassword());
            st.setString(4, c.getNom());
            st.setString(5, c.getPrenom());
            st.setString(6,c.getDate_naissance());
            st.setString(7,c.getPseudo());
            st.setInt(8,c.getTel());
            st.setString(9, c.getRole());
            st.setFloat(10,c.getMontant());
            st.executeUpdate();
            System.out.println("User ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public Cinepro login(String pseudo, String password){
    
        try {
            String requete = "SELECT * FROM user where pseudo=? AND password=? ";
            
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            
                Cinepro c = new Cinepro();
                c.setPseudo(rs.getString("pseudo"));
                c.setPassword(rs.getString("password"));
            }
          /*  ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            
                Personne p =new Personne();
                p.setId(rs.getInt(1));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                
                myList.add(p);
            } */
            
            
            /*ResultSet rs = st.executeQuery()
            st.setString(1, pseudo);
            st.setString(2, password);*/
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CineproCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Affichage
    @Override
    public List<Cinepro> entitiesList() {
        ArrayList<Cinepro> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM user";
            Statement st =  MyConnection.getInstance().getCnx().createStatement();
            
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            
                Cinepro c =new Cinepro();
                c.setId_user(rs.getInt(1));
                c.setEmail(rs.getString("email"));
                c.setPassword(rs.getString("password"));
                c.setNom(rs.getString("nom"));
                c.setPrenom(rs.getString("prenom"));
                c.setDate_naissance(rs.getString("date_naissance"));
                c.setPseudo(rs.getString("pseudo"));
                c.setTel(rs.getInt("tel"));
                c.setRole(rs.getString("role"));
                c.setMontant(rs.getFloat("montant"));
                
                myList.add(c);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    
    //Modification
    
    public void updateEntity(int idU,String em,String passw,String n,String pr,String dateN,String psd,int num,String rl,float mt) {
       
        try {
            String requete="UPDATE user SET email=?, password=?, nom=?, prenom=?, date_naissance=STR_TO_DATE(?,'%m/%d/%Y'), pseudo=?, tel=?, role=?, montant=? where id_user=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            
            st.setString(1, em);
            st.setString(2, passw);
            st.setString(3, n);
            st.setString(4, pr);
            st.setString(5, dateN);
            st.setString(6, psd);
            st.setInt(7, num);
            st.setString(8, rl);
            st.setFloat(9, mt);
            st.setInt(10, idU);
            
            st.executeUpdate();
            System.out.println("User Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void deleteEntity(int id) {
        try {
            String requete="DELETE FROM user WHERE id_user=?";
            PreparedStatement st=MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("User deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}

    @Override
    public int getUserByPseudo(String pseudo) {
    
        try {
            String requete = "Select id_user from user where pseudo=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setString(1, pseudo);
            st.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    }
        
    
}
