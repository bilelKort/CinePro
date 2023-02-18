/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.utils;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author kortb
 */
public class cineproConnexion {
    private String dbName = "cinepro";
    private String login="root";
    private String pwd="";
    private String url="jdbc:mysql://localhost/" + dbName + "?user=" + login + "&password=" + pwd + "&useUnicode=true&characterEncoding=UTF-8";
    private Connection cnx;
    private static cineproConnexion instance;
    
     public static cineproConnexion getInstance(){
        if(instance == null){
            instance = new cineproConnexion();
        }
        return instance;
    }
    
    public cineproConnexion(){
        try{
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("connexion etablie");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public Connection getCnx(){
        return cnx;
    }
      
}
