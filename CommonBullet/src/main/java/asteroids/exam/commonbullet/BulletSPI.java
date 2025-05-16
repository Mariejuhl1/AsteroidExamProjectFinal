package asteroids.exam.commonbullet;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public interface BulletSPI {
    Entity createBullet(Entity e, GameData gameData);
}
