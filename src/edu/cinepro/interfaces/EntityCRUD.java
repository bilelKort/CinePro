/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.cinepro.interfaces;

import java.util.List;

/**
 *
 * @author rayen
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public List <T> entitiesList();
    
    
}
