/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.cinepro.tests;

import edu.cinepro.entities.cinema;
import edu.cinepro.entities.salle;
import edu.cinepro.entities.snack;
import edu.cinepro.utils.MyConnection;
import edu.connexion3A18.services.CinemaCRUD;
import edu.connexion3A18.services.SalleCRUD;
import edu.connexion3A18.services.SnackCRUD;
import java.util.List;

/**
 *
 * @author rayen
 */
public class MainClass {
    public static void main(String[] args) {
       // MyConnection mc = new MyConnection();
       
   /*      cinema C1 = new cinema (1, "rayen", "rades", "test", "url");
        
       pcd.addEntity(C1);
       System.out.println(pcd.entitiesList());
      */
             /* CinemaCRUD pcd= new CinemaCRUD();
       pcd.updateEntity(2, "bolbol","rasjbal" , "khayba","photo");*/

   
   
   
   /*
     // snack s = new snack( "9tania", 2 ,  15 , "test", 15, 2);
      SnackCRUD pcd= new SnackCRUD();
     // pcd.addEntity(s); 
      
      //System.out.println(pcd.entitiesList());
      */
           // SnackCRUD pcd= new SnackCRUD();
//pcd.updateEntity(2, "jus", 3, 0, 0, 2);
   
   //salle salle= new salle(5, 3, 2,false);
        
       CinemaCRUD cd= new CinemaCRUD();

      List<cinema>   k= cd.entitiesList();
        System.out.println(k);
        
                           cinema c=cd.cinemabyid(2);
                            System.out.println(c);
         
      //          System.out.println(pcd.entitiesList());

        
 //  pcd.updateEntity(2, true);
                      //  System.out.println(pcd.entitiesList());
                        
                        
   }
    
}
