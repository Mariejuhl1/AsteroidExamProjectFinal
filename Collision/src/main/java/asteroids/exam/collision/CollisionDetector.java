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
        this.scoreService = scoreService;               // inject score service
    }

    public CollisionDetector() {
        // find the splitter implementation via ServiceLoader
        this.splitter = ServiceLoader.load(IAsteroidSplitter.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No AsteroidSplitter implementation found"));
    }

    @Override
    public void process(GameData gameData, World world) {
        // check every pair of entities for collisions
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (e1.getID().equals(e2.getID()) || !collides(e1, e2)) continue;
                handleCollision(e1, e2, world);          // resolve collision
            }
        }
    }

    private void handleCollision(Entity e1, Entity e2, World world) {
        EntityType t1 = getType(e1);
        EntityType t2 = getType(e2);
        if (t1 == null || t2 == null) return;

        if (isBulletAsteroidCollision(t1, t2)) {
            splitAsteroid(t2 == EntityType.ASTEROID ? e2 : e1, world); // break asteroid
            world.removeEntity(t1 == EntityType.BULLET ? e1 : e2);    // remove bullet
        } else if (isBulletShipCollision(t1, t2)) {
            handleShipHit(t1 == EntityType.BULLET ? e2 : e1, world);   // damage ship
            world.removeEntity(t1 == EntityType.BULLET ? e1 : e2);    // remove bullet
        } else if (isShipCrash(t1, t2)) {
            world.removeEntity(e1);                                    // remove both
            world.removeEntity(e2);
        }
    }

    private EntityType getType(Entity entity) {
        TypePart type = entity.getPart(TypePart.class);
        return type != null ? type.getType() : null;                  // extract type
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
        return type == EntityType.PLAYER || type == EntityType.ENEMY; // player or enemy
    }

    private void handleShipHit(Entity ship, World world) {
        LifePart life = ship.getPart(LifePart.class);
        if (life != null) {
            life.setLife(life.getLife() - 1);         // decrement life
            if (life.getLife() <= 0) {
                world.removeEntity(ship);              // remove if dead
            }
        } else {
            world.removeEntity(ship);                  // remove if no life part
        }
    }

    private void splitAsteroid(Entity asteroid, World world) {
        if (asteroid.getRadius() <= 5) {
            world.removeEntity(asteroid);              // too small, just remove
        } else {
            splitter.createSplitAsteroid(asteroid, world); // split into pieces
            world.removeEntity(asteroid);             // remove original
        }
        if (scoreService != null) {
            scoreService.submitScore(10);              // award points
        }
    }

    private boolean collides(Entity a, Entity b) {
        PositionPart p1 = a.getPart(PositionPart.class);
        PositionPart p2 = b.getPart(PositionPart.class);
        if (p1 == null || p2 == null) return false;
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        double distance = Math.hypot(dx, dy);
        return distance < (a.getRadius() + b.getRadius()); // bounding-circle test
    }
}
