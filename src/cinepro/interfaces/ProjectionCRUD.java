package cinepro.interfaces;

import cinepro.entities.Projection;

import java.util.List;

public interface ProjectionCRUD<T> {
    public void addProjection(T t);
    public List projectionList();
    public List projectionListByFilm(int id_film);
    public Projection getProjection(int id_projection);
    public void updateProjection(T t);
    public void deleteProjection(T t);
    public boolean checkDate(T t);
    public List projectionListBySalle(int id_salle);
    public Integer countFilms(int id_film);
    public void updateDiffuse();
}
