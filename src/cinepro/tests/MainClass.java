/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cinepro.tests;

import cinepro.entities.Feedback;
import cinepro.services.FeedbackCRUD;

/** 
 *
 * @author acer
 */
public class MainClass {
    public static void main(String[]args){
       // MyConnection mc =new MyConnection();
        Feedback f1=new Feedback("amazing",3,2,"02/04/2001- 14:20");
        FeedbackCRUD pcd=new FeedbackCRUD();
        //pcd.addCommentaire(f1);
       System.out.println(pcd.commentaireList());
       //pcd.deleteCommentaire(21);
       pcd.updateCommentaire(22, "nour", 3, 2, "05/05/2022-15:00");
        
        
    }
    
}
