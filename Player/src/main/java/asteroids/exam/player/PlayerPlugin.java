package asteroids.exam.player;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.LifePart;
import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.entityparts.TypePart;
import asteroids.exam.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    @Override
    public void start(GameData gameData, World world) {
        player = createPlayerShip(gameData);              // spawn player in center
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {
        Entity playerShip = new Player();

        double x = gameData.getDisplayWidth() / 2.0;
        double y = gameData.getDisplayHeight() / 2.0;
        double rotation = 90;

        playerShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5); // ship shape
        playerShip.setRadius(8);                                // collision radius
        playerShip.add(PositionPart.class, new PositionPart(x, y, rotation)); // start pos
        playerShip.add(LifePart.class, new LifePart(10));       // initial lives
        playerShip.add(TypePart.class, new TypePart(TypePart.EntityType.PLAYER)); // type

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);                           // remove on stop
    }
}
