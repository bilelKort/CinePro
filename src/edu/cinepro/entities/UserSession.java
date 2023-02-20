/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cinepro.entities;

/**
 *
 * @author MOEµNESS
 */
public final class UserSession {
    
    private static UserSession instance;

    private int id;
    private String role;

    public UserSession(int id, String role) {
        this.id = id;
        this.role = role;
    }

    

    public static UserSession getInstace(int id, String role) {
        if(instance == null) {
            instance = new UserSession(id, role);
        }
        return instance;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    

    public void cleanUserSession() {
        id = 0;// or null
        role = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" + "id=" + id + ", role=" + role + '}';
    }

}
