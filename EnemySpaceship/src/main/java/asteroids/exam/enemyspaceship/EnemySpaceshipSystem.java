package asteroids.exam.enemyspaceship;

import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.commonbullet.BulletSPI;
import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

public class EnemySpaceshipSystem implements IEntityProcessingService {

    private final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(EnemySpaceship.class)) { // each enemy
            PositionPart pos = enemy.getPart(PositionPart.class);
            if (pos == null) continue;

            // occasionally change direction
            if (random.nextDouble() < 0.05) {
                pos.setRotation(random.nextInt(360));     // random rotation
            }

            // move forward
            double dx = Math.cos(Math.toRadians(pos.getRotation()));
            double dy = Math.sin(Math.toRadians(pos.getRotation()));
            pos.setX(pos.getX() + dx);
            pos.setY(pos.getY() + dy);

            // keep inside screen bounds
            if (pos.getX() < 0) pos.setX(1);
            if (pos.getX() > gameData.getDisplayWidth()) pos.setX(gameData.getDisplayWidth() - 1);
            if (pos.getY() < 0) pos.setY(1);
            if (pos.getY() > gameData.getDisplayHeight()) pos.setY(gameData.getDisplayHeight() - 1);

            // sometimes shoot a bullet
            if (random.nextDouble() < 0.03) {
                shootBullet(enemy, gameData, world);
            }
        }
    }

    private void shootBullet(Entity enemy, GameData gameData, World world) {
        for (BulletSPI spi : ServiceLoader.load(BulletSPI.class)) { // find all bullet SPIs
            Entity bullet = spi.createBullet(enemy, gameData);
            if (bullet != null) {
                world.addEntity(bullet);                  // add the bullet
            }
        }
    }
}
