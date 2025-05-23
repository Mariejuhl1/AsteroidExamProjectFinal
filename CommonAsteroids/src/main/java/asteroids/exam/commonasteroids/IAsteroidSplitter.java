package asteroids.exam.commonasteroids;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.World;

public interface IAsteroidSplitter {
    // split the given asteroid into smaller ones
    void createSplitAsteroid(Entity e, World w);
}
