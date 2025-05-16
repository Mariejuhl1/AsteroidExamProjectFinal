package asteroids.exam.common.data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private float radius;

    private final Map<Class<?>, Object> parts;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    public <T> void add(Class<T> partClass, T part) {
        parts.put(partClass, part);
    }

    public void remove(Class<?> partClass) {
        parts.remove(partClass);
    }

    public <T> T getPart(Class<T> partClass) {
        return partClass.cast(parts.get(partClass));
    }

    public String getID() {
        return ID.toString();
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setPolygonCoordinates(double... coordinates) {
        this.polygonCoordinates = coordinates;
    }


    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }
}
