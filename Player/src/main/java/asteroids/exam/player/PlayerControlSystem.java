package asteroids.exam.player;

import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.commonbullet.BulletSPI;
import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.GameKeys;
import asteroids.exam.common.data.World;
import asteroids.exam.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity player : world.getEntities(Player.class)) {

            PositionPart position = player.getPart(PositionPart.class);

            if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                position.setRotation(position.getRotation() - 5);
            }
            if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                position.setRotation(position.getRotation() + 5);
            }
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                double changeX = Math.cos(Math.toRadians(position.getRotation()));
                double changeY = Math.sin(Math.toRadians(position.getRotation()));
                position.setX(position.getX() + changeX);
                position.setY(position.getY() + changeY);
            }
            if(gameData.getKeys().isDown(GameKeys.SPACE)) {                
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> {world.addEntity(spi.createBullet(player, gameData));}
                );
            }
            
        if (position.getX() < 0) {
            position.setX(1);
        }

        if (position.getX() > gameData.getDisplayWidth()) {
            position.setX(gameData.getDisplayWidth()-1);
        }

        if (position.getY() < 0) {
            position.setY(1);
        }

        if (position.getY() > gameData.getDisplayHeight()) {
            position.setY(gameData.getDisplayHeight()-1);
        }

                                        
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
