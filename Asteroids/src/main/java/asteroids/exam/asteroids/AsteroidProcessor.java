package asteroids.exam.asteroids;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.services.IEntityProcessingService;
import asteroids.exam.commonasteroids.Asteroid;
import asteroids.exam.commonasteroids.IAsteroidSplitter;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidSplitter splitter = new AsteroidSplitter();

    @Override
    public void process(GameData gameData, World world) {

        for (Entity e : world.getEntities(Asteroid.class)) {
            PositionPart pos = e.getPart(PositionPart.class);
            if (pos == null) continue;

            double angle = Math.toRadians(pos.getRotation());
            double deltaX = Math.cos(angle) * 0.5;
            double deltaY = Math.sin(angle) * 0.5;

            double x = pos.getX() + deltaX;
            double y = pos.getY() + deltaY;

            int screenW = gameData.getDisplayWidth();
            int screenH = gameData.getDisplayHeight();

            if (x < 0) x += screenW;
            if (x > screenW) x %= screenW;
            if (y < 0) y += screenH;
            if (y > screenH) y %= screenH;

            pos.setPosition(x, y);
        }
    }


    public void setSplitter(IAsteroidSplitter splitter) {
        this.splitter = splitter;
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.splitter = null;
    }


}
