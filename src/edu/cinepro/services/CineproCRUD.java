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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;


/**
 *
 * @author MOEµNESS
 */

public class CineproCRUD implements EntityCRUD<Cinepro> {
    
    // Verifier unicité du Pseudo
   /* public boolean testPseudo(Cinepro c)
            {
            int a;
            String requete = "select id from user WHERE email=?";
            
            
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            preparedStatement.setString(1, pseudo);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
             a = rs.getString("id");
        return false;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
            } */
    
    
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
          //  System.out.println("User ajoutée!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public Cinepro login(String pseudo, String password){
    
        Cinepro c = new Cinepro();
        try {
            String requete = "SELECT * FROM user where (pseudo=? AND password=?) ";
            
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            preparedStatement.setString(1, pseudo);
            preparedStatement.setString(2, password);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            
                c.setId_user(rs.getInt(1));
                c.setEmail(rs.getString(2));
                c.setPassword(rs.getString(3));
                c.setNom(rs.getString(4));
                c.setPrenom(rs.getString(5));
                c.setDate_naissance(rs.getString(6));
                c.setPseudo(rs.getString(7));
                c.setTel(rs.getInt(8));
                c.setRole(rs.getString(9));
                c.setMontant(rs.getFloat(10));
            }
            return c;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
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
    @Override
    public void updateEntity(int idU,String em,String passw,String n,String pr,String dateN,String psd,int num,String rl,float mt) {
       
        try {
            String requete="UPDATE user SET email=?, password=?, nom=?, prenom=?, date_naissance=STR_TO_DATE(?,'%Y-%m-%d'), pseudo=?, tel=?, role=?, montant=? where id_user=?";
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
    
    @Override
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
    

        int id=0;
        try {
            String requete = "SELECT id_user FROM user WHERE pseudo=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            preparedStatement.setString(1, pseudo);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
            
               id = rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
        
    } 
    
    @Override
    public String getEmailByPseudo(String pseudo){
    
        String email = "";
        try {
            String requete = "SELECT email FROM user WHERE pseudo=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            preparedStatement.setString(1, pseudo);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
            
               email = rs.getString(1);
            
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return email;
        
        
    }
    
    @Override
    public Cinepro getUserById(int id){
    
        Cinepro c = new Cinepro();
        try {
            String requete = "SELECT * FROM user WHERE id_user=?";
            PreparedStatement preparedStatement = MyConnection.getInstance().getCnx().prepareStatement(requete);
            preparedStatement.setInt(1, id);
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
            
               
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
                
            }
            return c;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;   
    }
    
    @Override
    public void changeRole(int idU, String rl){
    
        try {
            String requete="UPDATE user SET role=? where id_user=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setString(1, rl);
            st.setInt(2, idU);
            
            st.executeUpdate();
            System.out.println("Role Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void changePass(String pseudo, String pass){
    
        try {
            String requete="UPDATE user SET password=? where pseudo=?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setString(1, pass);
            st.setString(2, pseudo);
            
            st.executeUpdate();
            System.out.println("Password Changed!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public boolean testUniqueness(String pseudo){
    
        int count=0;
        
        try {
            String requete="SELECT COUNT(*) FROM user WHERE pseudo = ?";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            
            st.setString(1, pseudo);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
            
                count = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (count > 0){
        
            return false;
        } else{
        
            return true;
        }
        
    }
    
    
    
}


