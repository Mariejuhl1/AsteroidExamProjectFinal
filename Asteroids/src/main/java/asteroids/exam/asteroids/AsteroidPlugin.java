package asteroids.exam.asteroids;

import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.entityparts.TypePart;
import asteroids.exam.commonasteroids.Asteroid;
import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.services.IGamePluginService;
import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {

    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {                    // create 5 asteroids
            Entity asteroid = createAsteroid(gameData);  // build new asteroid
            world.addEntity(asteroid);                   // add it to the world
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) { // find all asteroids
            world.removeEntity(asteroid);               // remove each asteroid
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Random rnd = new Random();                       // random generator

        double x = rnd.nextDouble() * gameData.getDisplayWidth();   // random x on screen
        double y = rnd.nextDouble() * gameData.getDisplayHeight();  // random y on screen
        double rotation = rnd.nextInt(360);              // random facing angle
        int size = rnd.nextInt(16) + 10;                 // random size between 10–25

        Entity asteroid = new Asteroid();                // new asteroid entity
        asteroid.add(PositionPart.class, new PositionPart(x, y, rotation)); // set pos
        asteroid.add(TypePart.class, new TypePart(TypePart.EntityType.ASTEROID)); // set type
        asteroid.setRadius(size);                        // set collision radius
        asteroid.setPolygonCoordinates(                   // set shape vertices
                size, -size, -size, -size, -size, size, size, size
        );

        return asteroid;                                 // return built asteroid
    }
}
