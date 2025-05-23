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

    @Override
    public void start(GameData gameData, World world) {
        spaceship = generateEnemy(gameData);            // create a new enemy
        world.addEntity(spaceship);                     // add to the world
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(spaceship);                  // remove the enemy
    }

    private Entity generateEnemy(GameData gameData) {
        Entity enemy = new EnemySpaceship();            // new enemy entity

        // random starting position
        float posX = random.nextFloat() * gameData.getDisplayWidth();
        float posY = random.nextFloat() * gameData.getDisplayHeight();

        enemy.setRadius(8);                             // set collision radius
        enemy.setPolygonCoordinates(-5, -5, 10, 0, -5, 5); // set shape

        // add initial position and rotation
        enemy.add(PositionPart.class, new PositionPart(posX, posY, 0));
        enemy.add(LifePart.class, new LifePart(10));    // set life count
        enemy.add(TypePart.class, new TypePart(TypePart.EntityType.ENEMY)); // set type

        return enemy;                                   // return the configured enemy
    }
}
