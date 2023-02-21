/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.interfaces;

import edu.cinepro.entities.Cinepro;
import java.util.List;
/**
 *
 * @author MOEµNESS
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public Cinepro login(String pseudo, String password);
    public List<T> entitiesList();
    public void updateEntity(int idU,String em,String passw,String n,String pr,String dateN,String psd,int num,String rl,float mt);
    public void deleteEntity(int id);
    public int getUserByPseudo(String pseudo);
    public Cinepro getUserById(int id);
    
}
