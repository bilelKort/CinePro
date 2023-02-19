package cinepro.interfaces;

import java.util.List;

public interface CrewCRUD<T> {

    public void addCrew(T t);
    public List crewList();
    public void updateCrew(T t);
    public void deleteCrew(T t);
}
