package asteroids.exam.collision;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.LifePart;
import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.entityparts.TypePart;
import asteroids.exam.common.entityparts.TypePart.EntityType;
import asteroids.exam.common.services.IPostEntityProcessingService;
import asteroids.exam.common.services.ScoreService;
import asteroids.exam.commonasteroids.IAsteroidSplitter;

import java.util.ServiceLoader;

public class CollisionDetector implements IPostEntityProcessingService {

    private final IAsteroidSplitter splitter;
    private ScoreService scoreService;

    public void setScoreService(ScoreService scoreService) {
        this.scoreService = scoreService;
    }


    public CollisionDetector() {
        this.splitter = ServiceLoader.load(IAsteroidSplitter.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No AsteroidSplitter implementation found"));
    }

    @Override
    public void process(GameData gameData, World world) {
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (e1.getID().equals(e2.getID()) || !collides(e1, e2)) continue;
                handleCollision(e1, e2, world);
            }
        }
    }

    private void handleCollision(Entity e1, Entity e2, World world) {
        EntityType t1 = getType(e1);
        EntityType t2 = getType(e2);

        if (t1 == null || t2 == null) return;

        if (isBulletAsteroidCollision(t1, t2)) {
            splitAsteroid(t2 == EntityType.ASTEROID ? e2 : e1, world);
            world.removeEntity(t1 == EntityType.BULLET ? e1 : e2);
        } else if (isBulletShipCollision(t1, t2)) {
            handleShipHit(t1 == EntityType.BULLET ? e2 : e1, world);
            world.removeEntity(t1 == EntityType.BULLET ? e1 : e2);
        } else if (isShipCrash(t1, t2)) {
            world.removeEntity(e1);
            world.removeEntity(e2);
        }
    }

    private EntityType getType(Entity entity) {
        TypePart type = entity.getPart(TypePart.class);
        return type != null ? type.getType() : null;
    }

    private boolean isBulletAsteroidCollision(EntityType t1, EntityType t2) {
        return (t1 == EntityType.BULLET && t2 == EntityType.ASTEROID) ||
                (t2 == EntityType.BULLET && t1 == EntityType.ASTEROID);
    }

    private boolean isBulletShipCollision(EntityType t1, EntityType t2) {
        return (t1 == EntityType.BULLET && isShip(t2)) ||
                (t2 == EntityType.BULLET && isShip(t1));
    }

    private boolean isShipCrash(EntityType t1, EntityType t2) {
        return (isShip(t1) && (isShip(t2) || t2 == EntityType.ASTEROID)) ||
                (isShip(t2) && (isShip(t1) || t1 == EntityType.ASTEROID));
    }

    private boolean isShip(EntityType type) {
        return type == EntityType.PLAYER || type == EntityType.ENEMY;
    }

    private void handleShipHit(Entity ship, World world) {
        LifePart life = ship.getPart(LifePart.class);
        if (life != null) {
            life.setLife(life.getLife() - 1);
            if (life.getLife() <= 0) {
                world.removeEntity(ship);
            }
        } else {
            world.removeEntity(ship);
        }
    }

    private void splitAsteroid(Entity asteroid, World world) {
        if (asteroid.getRadius() <= 5) {
            world.removeEntity(asteroid);
        } else {
            splitter.createSplitAsteroid(asteroid, world);
            world.removeEntity(asteroid);
        }
        if (scoreService != null) {
            scoreService.submitScore(10);
        }
    }

    private boolean collides(Entity a, Entity b) {
        PositionPart p1 = a.getPart(PositionPart.class);
        PositionPart p2 = b.getPart(PositionPart.class);
        if (p1 == null || p2 == null) return false;

        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        double distance = Math.hypot(dx, dy);
        return distance < (a.getRadius() + b.getRadius());
    }
}
