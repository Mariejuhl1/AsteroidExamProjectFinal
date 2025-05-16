package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public class LifePart implements EntityPart {

    private int life;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void process(GameData gameData, Entity entity) {

    }
}