/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cinepro.interfaces;

import java.util.List;

/**
 *
 * @author kortb
 */
public interface entityCRUD<T> {
    public void addEntity(T t);
    public List<T> entitiesList();
}
