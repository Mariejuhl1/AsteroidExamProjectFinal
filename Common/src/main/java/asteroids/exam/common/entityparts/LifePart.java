package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public class LifePart implements EntityPart {

    private int life;                                     // remaining lives

    public LifePart(int life) {
        this.life = life;                                 // init lives
    }

    public int getLife() {
        return life;                                      // check lives
    }

    public void setLife(int life) {
        this.life = life;                                 // change lives
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        // no per-frame logic
    }
}
