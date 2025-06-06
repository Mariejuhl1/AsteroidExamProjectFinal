package asteroids.exam.bullet;

import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.entityparts.TypePart;
import asteroids.exam.commonbullet.Bullet;
import asteroids.exam.commonbullet.BulletSPI;
import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity bullet : world.getEntities(Bullet.class)) { // loop all bullets
            PositionPart position = bullet.getPart(PositionPart.class);
            if (position == null) continue;

            double changeX = Math.cos(Math.toRadians(position.getRotation())); // x vector
            double changeY = Math.sin(Math.toRadians(position.getRotation())); // y vector

            // move bullet forward
            position.setPosition(
                    position.getX() + changeX * 3,
                    position.getY() + changeY * 3
            );
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1); // bullet shape
        bullet.setRadius(1);                                      // bullet radius

        PositionPart shooterPos = shooter.getPart(PositionPart.class);
        if (shooterPos == null) return null;

        double rotation = shooterPos.getRotation();
        double changeX = Math.cos(Math.toRadians(rotation));      // x vector
        double changeY = Math.sin(Math.toRadians(rotation));      // y vector

        // spawn bullet in front of shooter
        PositionPart bulletPos = new PositionPart(
                shooterPos.getX() + changeX * 10,
                shooterPos.getY() + changeY * 10,
                rotation
        );
        bullet.add(PositionPart.class, bulletPos);
        bullet.add(TypePart.class, new TypePart(TypePart.EntityType.BULLET));

        return bullet;
    }
}
