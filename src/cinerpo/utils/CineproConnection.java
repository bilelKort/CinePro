/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Home
 */
public class CineproConnection {
    private String dbName = "cinepro";
    private String login="root";
    private String pwd="";
    private String url="jdbc:mysql://localhost/" + dbName + "?user=" + login + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8";
    private Connection cnx;
    private static CineproConnection instance;
    
    private CineproConnection(){
        try{
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("connexion etablie");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
     public static CineproConnection getInstance(){
        if(instance == null){
            instance = new CineproConnection();
        }
        return instance;
    }
    
    
    public Connection getCnx(){
        return cnx;
    }
     
}
