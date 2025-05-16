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
        for (Entity enemySpaceship : world.getEntities(EnemySpaceship.class)) {

            PositionPart position = enemySpaceship.getPart(PositionPart.class);

            if (random.nextDouble() < 0.05) {
                position.setRotation(random.nextInt(360));
            }

            double changeX = Math.cos(Math.toRadians(position.getRotation()));
            double changeY = Math.sin(Math.toRadians(position.getRotation()));
            position.setX(position.getX() + changeX);
            position.setY(position.getY() + changeY);

            if (position.getX() < 0) {
                position.setX(1);
            }
            if (position.getX() > gameData.getDisplayWidth()) {
                position.setX(gameData.getDisplayWidth() - 1);
            }
            if (position.getY() < 0) {
                position.setY(1);
            }
            if (position.getY() > gameData.getDisplayHeight()) {
                position.setY(gameData.getDisplayHeight() - 1);
            }
            if (random.nextDouble() < 0.03) {
                shootBullet(enemySpaceship, gameData, world);
            }
        }
    }
    private void shootBullet(Entity enemySpaceship, GameData gameData, World world) {
        for (BulletSPI bulletSPI : getBulletSPIs()) {
            Entity bullet = bulletSPI.createBullet(enemySpaceship, gameData);
            if (bullet != null) {
                world.addEntity(bullet);
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
