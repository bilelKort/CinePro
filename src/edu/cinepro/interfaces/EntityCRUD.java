
package edu.cinepro.interfaces;

import edu.cinepro.entities.cinema;
import java.util.List;

/**
 *
 * @author rayen
 */
public interface EntityCRUD<T> {

    void addEntity(cinema c);

    List<cinema> entitiesList();
}
