package cinepro.interfaces;

import java.util.List;

public interface ReservationCRUD<T> {
    public void addEntity(T t);
    public List<T> EntityList();
    public void updateEntity(int id_reclamation, String description,int id_user,int id_film,String date,boolean etat);
    public void deleteEntity(int id_reclamation);

}
