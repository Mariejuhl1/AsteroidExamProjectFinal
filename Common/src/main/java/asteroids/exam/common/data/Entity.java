package asteroids.exam.common.data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();           // unique entity ID

    private double[] polygonCoordinates;                  // outline points
    private float radius;                                 // collision radius

    private final Map<Class<?>, Object> parts;            // entity parts map

    public Entity() {
        parts = new ConcurrentHashMap<>();                // thread-safe parts storage
    }

    public <T> void add(Class<T> partClass, T part) {
        parts.put(partClass, part);                       // attach a part
    }

    public void remove(Class<?> partClass) {
        parts.remove(partClass);                          // detach a part
    }

    public <T> T getPart(Class<T> partClass) {
        return partClass.cast(parts.get(partClass));      // retrieve a part
    }

    public String getID() {
        return ID.toString();                             // return ID as string
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;                        // return shape
    }

    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;            // set shape
    }

    public void setRadius(float radius) {
        this.radius = radius;                             // set radius
    }

    public float getRadius() {
        return radius;                                    // get radius
    }
}
