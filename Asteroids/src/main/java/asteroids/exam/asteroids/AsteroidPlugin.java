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
        for (int i = 0; i < 5; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Random rnd = new Random();

        double x = rnd.nextDouble() * gameData.getDisplayWidth();
        double y = rnd.nextDouble() * gameData.getDisplayHeight();
        double rotation = rnd.nextInt(360);
        int size = rnd.nextInt(16) + 10;

        Entity asteroid = new Asteroid();
        asteroid.add(PositionPart.class, new PositionPart(x, y, rotation));
        asteroid.add(TypePart.class, new TypePart(TypePart.EntityType.ASTEROID));
        asteroid.setRadius(size);
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);

        return asteroid;
    }
}
