/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cinepro.interfaces;

import java.util.List;

/**
 *
 * @author acer
 */
public interface EntityCRUD<T> {
     public void addEntity(T t);
    public List<T> EntityList();
    public void updateEntity(int id_reclamation, String description,int id_user,int id_film,String date,boolean etat);
    public void deleteEntity(int id_reclamation);
   // public void colorerEtat();
}
