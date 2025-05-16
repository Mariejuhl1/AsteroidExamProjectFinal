package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.Entity;


public class PositionPart implements EntityPart {

    private double x;
    private double y;
    private double rotation;

    public PositionPart(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setPosition(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
    }
}