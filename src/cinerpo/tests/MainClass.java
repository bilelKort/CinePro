/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.tests;

import cinerpo.entities.Abonnement;
import cinerpo.entities.Badge;
import cinerpo.entities.TypeAbonnement;
import cinerpo.entities.Type_Badge;
import cinerpo.services.AbonnementCRUD;
import cinerpo.services.BadgeCRUD;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Home
 */
public class MainClass {
    	public static void main(String[] args) throws SQLException {
		//MyConnection mc =new MyConnection();
               Badge b1=new Badge(1);
                //Badge b2=new Badge(Type_Badge.silver,5,2);
                BadgeCRUD bcd=new BadgeCRUD();
                //bcd.addEntity(b1);
                //bcd.addEntity(b2);
               // System.out.println(bcd.entitiesList());
                //bcd.updateEntity("gold",1234578, 1);
               // bcd.deleteEntity(1);
                //TypeAbonnement ta=new TypeAbonnement("3mois",80);
               // TypeAbonnement tb=new TypeAbonnement(12,500);
               // TypeAbonnement tbb=new TypeAbonnement(6,300);
               // TypeAbonnement ta=new TypeAbonnement(3,150);
                //Abonnement a1=new Abonnement(1,ta,"02/03/2001","02/03/2001",false);
               // Abonnement a2=new Abonnement(1,tb);
                //Abonnement a1=new Abonnement(1,ta);
               // AbonnementCRUD acd=new AbonnementCRUD();
                //acd.addEntity(a2);
               // acd.addEntity(a1);
               // System.out.println(bcd.entitiesList());
                //acd.updateEntity(a1,4);
               
                
                //System.out.println(bcd.test1(4));
              bcd.update2(4);
                
	}
    
}
