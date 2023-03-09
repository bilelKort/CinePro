package edu.cinepro.interfaces;

import edu.cinepro.entities.User;

import java.util.List;

public interface UserCRUD<T> {
    public void addEntity(T t);
    public Boolean login(String pseudo, String password);

    //Affichage
    List<User> entitiesList();

    //public List<T> entitiesList(String name);
    public void updateEntity(int idU,String em,String passw,String n,String pr,String dateN,String psd,int num,String rl,float mt);
    public void deleteEntity(int id);
    public int getUserByPseudo(String pseudo);
    public String getEmailByPseudo(String pseudo);
    public User getUserById(int id);
    public void changeRole(int idU, String rl);
    public void changePass(String pseudo, String pass);

}
