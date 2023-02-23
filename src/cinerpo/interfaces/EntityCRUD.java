/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinerpo.interfaces;

import java.util.List;

/**
 *
 * @author Home
 * @param <T>
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public List<T> entitiesList();
    public void updateEntity(T t, int id);
    public void deleteEntity(int id);
   
    
}
