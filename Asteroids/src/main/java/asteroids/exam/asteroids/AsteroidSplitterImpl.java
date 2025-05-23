package asteroids.exam.asteroids;

import asteroids.exam.common.entityparts.PositionPart;  // position & rotation part
import asteroids.exam.common.entityparts.TypePart;      // holds entity type
import asteroids.exam.commonasteroids.Asteroid;         // concrete Asteroid entity
import asteroids.exam.commonasteroids.IAsteroidSplitter; // splitter interface
import asteroids.exam.common.data.Entity;               // game entity base class
import asteroids.exam.common.data.World;                // game world
import java.util.Random;                                // for random numbers

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World world) {
        float originalRadius = e.getRadius();              // get radius of asteroid
        if (originalRadius <= 6) return;                   // too small to split

        PositionPart pos = e.getPart(PositionPart.class);  // get its position
        if (pos == null) return;                           // skip if no position

        float newRadius = originalRadius / 2;              // radius of child asteroids
        Random rnd = new Random();                         // random generator

        for (int i = 0; i < 2; i++) {                      // create 2 smaller ones
            Entity newAsteroid = new Asteroid();           // new child asteroid

            int shapeSize = (int) newRadius;               // shape half-size
            newAsteroid.setPolygonCoordinates(             // define its shape
                    shapeSize, -shapeSize,
                    -shapeSize, -shapeSize,
                    -shapeSize, shapeSize,
                    shapeSize, shapeSize
            );
            newAsteroid.setRadius(newRadius);              // set its radius

            double rotation = rnd.nextInt(360);            // random rotation
            PositionPart newPos = new PositionPart(pos.getX(), pos.getY(), rotation);
            newAsteroid.add(PositionPart.class, newPos);  // add position
            newAsteroid.add(TypePart.class, new TypePart(TypePart.EntityType.ASTEROID)); // add type

            world.addEntity(newAsteroid);                  // put into world
        }
    }
}
