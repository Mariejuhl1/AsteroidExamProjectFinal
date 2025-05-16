package asteroids.exam.enemyspaceship;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.LifePart;
import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.entityparts.TypePart;
import asteroids.exam.common.services.IGamePluginService;

import java.util.Random;

public class EnemySpaceshipPlugin implements IGamePluginService {

    private final Random random = new Random();
    private Entity spaceship;

    public EnemySpaceshipPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        spaceship = generateEnemy(gameData);
        world.addEntity(spaceship);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(spaceship);
    }

    private Entity generateEnemy(GameData gameData) {
        Entity enemy = new EnemySpaceship();

        float posX = random.nextFloat(gameData.getDisplayWidth());
        float posY = random.nextFloat(gameData.getDisplayHeight());

        enemy.setRadius(8);
        enemy.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);

        enemy.add(PositionPart.class, new PositionPart(posX, posY, 0));
        enemy.add(LifePart.class, new LifePart(10));
        enemy.add(TypePart.class, new TypePart(TypePart.EntityType.ENEMY));

        return enemy;
    }
}
