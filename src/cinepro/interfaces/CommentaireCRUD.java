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
public interface CommentaireCRUD<T>{
    public void addCommentaire(T t);
    public List<T> commentaireList();
    public void updateCommentaire(int id_feedback, String feedback,int id_user,int id_film,String date);
    public void deleteCommentaire(int id_feedback);
    
}
