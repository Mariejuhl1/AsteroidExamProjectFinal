package asteroids.exam.common.entityparts;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public interface EntityPart {
    void process(GameData gameData, Entity entity);
}
