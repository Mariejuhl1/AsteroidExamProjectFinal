package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public class PositionPart implements EntityPart {

    private double x, y, rotation;                        // pos & facing

    public PositionPart(double x, double y, double rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;                        // init position
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getRotation() { return rotation; }
    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setRotation(double rotation) { this.rotation = rotation; }

    public void setPosition(double newX, double newY) {
        this.x = newX; this.y = newY;                    // move to coords
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        // no per-frame logic
    }
}
