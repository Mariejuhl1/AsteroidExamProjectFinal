package asteroids.exam.asteroids;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.services.IEntityProcessingService;
import asteroids.exam.commonasteroids.Asteroid;
import asteroids.exam.commonasteroids.IAsteroidSplitter;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidSplitter splitter = new AsteroidSplitterImpl();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e : world.getEntities(Asteroid.class)) { // loop all asteroids
            PositionPart pos = e.getPart(PositionPart.class);
            if (pos == null) continue;

            double angle = Math.toRadians(pos.getRotation()); // convert to radians
            double deltaX = Math.cos(angle) * 0.5;            // movement in X
            double deltaY = Math.sin(angle) * 0.5;            // movement in Y

            double x = pos.getX() + deltaX;                   // new X
            double y = pos.getY() + deltaY;                   // new Y

            int screenW = gameData.getDisplayWidth();         // screen width
            int screenH = gameData.getDisplayHeight();        // screen height

            if (x < 0) x += screenW;                          // wrap left
            if (x > screenW) x %= screenW;                    // wrap right
            if (y < 0) y += screenH;                          // wrap top
            if (y > screenH) y %= screenH;                    // wrap bottom

            pos.setPosition(x, y);                            // apply position
        }
    }

    public void setSplitter(IAsteroidSplitter splitter) {
        this.splitter = splitter;                            // switch splitter
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.splitter = null;                                // disable splitting
    }
}
