package asteroids.exam.bullet;

import asteroids.exam.commonbullet.Bullet;
import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

    private Entity bullet;

    @Override
    public void start(GameData gameData, World world) {

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities()) {
            if (e.getClass() == Bullet.class) {
                world.removeEntity(e);
            }
        }
    }

}
