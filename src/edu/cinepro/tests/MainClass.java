/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.tests;

import edu.cinepro.entities.Cinepro;
import edu.cinepro.services.CineproCRUD;
import edu.cinepro.utils.MyConnection;
/**
 *
 * @author MOEµNESS
 */
public class MainClass {
    public static void main(String[] args) {
    //    MyConnection mc = new MyConnection();
    
        //Ajout d'un nouveau utilisateur
        //Cinepro c2 = new Cinepro("gannouni.moeness@esprit.tn","azer123ty45","Bouhamed","Amin","08/13/2000","Bouha",12345678,"client",0);
        CineproCRUD ccd = new CineproCRUD();
       // ccd.addEntity(c2);
        
        //Modification d'un user
       // ccd.updateEntity(3,"moeness.gannouni@esprit.tn","12345azerty","Ben Salah","Fedi","08/13/2000",87654321,12345678,"client",0);
       
        //Affichage du User
       // System.out.println(c2.toString());
        //Affichage du liste
       // System.out.println(ccd.entitiesList());
        
        //Suppresion d'un user
        ccd.deleteEntity(3);
        
    }
}
