package asteroids.exam.commonbullet;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;

public interface BulletSPI {
    // create a new bullet based on the shooter’s state
    Entity createBullet(Entity shooter, GameData gameData);
}
