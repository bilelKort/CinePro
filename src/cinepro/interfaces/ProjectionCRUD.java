package cinepro.interfaces;

import cinepro.entities.Projection;

import java.util.List;

public interface ProjectionCRUD<T> {
    public void addProjection(T t);
    public List ProjectionList();
    public Projection getProjection(int id_projection);
    public void updateProjection(T t);
    public void deleteProjection(T t);
}
