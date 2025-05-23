package asteroids.exam.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>(); // entities

    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);            // add to world
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);                       // remove by ID
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());                 // remove by instance
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();                        // all entities
    }

    @SafeVarargs
    public final <E extends Entity> List<Entity> getEntities(Class<E>... types) {
        List<Entity> result = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> t : types) {
                if (e.getClass().equals(t)) {
                    result.add(e);                        // filter by type
                }
            }
        }
        return result;
    }

    public Entity getEntity(String ID) {
        return entityMap.get(ID);                        // lookup by ID
    }
}
