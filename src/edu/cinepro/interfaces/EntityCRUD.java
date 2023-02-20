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
    public List<T> entitiesList();
    public int getUserByPseudo(String pseudo);
    
}
