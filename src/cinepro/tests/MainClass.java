/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.tests;

import cinepro.entities.*;
import cinepro.services.*;
import cinepro.utils.cineproConnexion;

/**
 *
 * @author kortb
 */
public class MainClass {
    public static void main(String[] args) { 
        cineproConnexion mc = new cineproConnexion();
        reservationCRUD pcd = new reservationCRUD();
        reservation res = new reservation(1,1,true);
       pcd.addEntity(res);
        //pcd.del eteEntity(6,2,1);
        //System.out.println(pcd.entitiesList()); 
        
       //reservation_place r1=new reservation_place("(15,50)", 40.5f,12);
       //reservation_placeCRUD pcd = new reservation_placeCRUD();
       // pcd.deleteEntity(1);
       //pcd.addEntity(r1);
        //System.out.println(pcd.entitiesList()); 
        //pcd.updateEntity(5, "(10,10)", 60.9f, 7);
      //reservation_snack rr = new reservation_snack(30,50.9f,11);
      //reservation_snackCRUD pcs = new reservation_snackCRUD();
      //pcs.addEntity(rr);
    }
}
