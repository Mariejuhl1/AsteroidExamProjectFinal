package asteroids.exam.asteroids;

import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.entityparts.TypePart;
import asteroids.exam.commonasteroids.Asteroid;
import asteroids.exam.commonasteroids.IAsteroidSplitter;
import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.World;

import java.util.Random;

public class AsteroidSplitter implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        float originalRadius = e.getRadius();
        if (originalRadius <= 6) return; // for små til split

        PositionPart pos = e.getPart(PositionPart.class);
        if (pos == null) return;

        float newRadius = originalRadius / 2;
        Random rnd = new Random();

        for (int i = 0; i < 2; i++) {
            Entity newAsteroid = new Asteroid();

            int shapeSize = (int) newRadius;
            newAsteroid.setPolygonCoordinates(
                    shapeSize, -shapeSize,
                    -shapeSize, -shapeSize,
                    -shapeSize, shapeSize,
                    shapeSize, shapeSize
            );
            newAsteroid.setRadius(newRadius);

            double rotation = rnd.nextInt(360);
            PositionPart newPos = new PositionPart(pos.getX(), pos.getY(), rotation);
            newAsteroid.add(PositionPart.class, newPos);
            newAsteroid.add(TypePart.class, new TypePart(TypePart.EntityType.ASTEROID));


            world.addEntity(newAsteroid);
        }
    }
}
