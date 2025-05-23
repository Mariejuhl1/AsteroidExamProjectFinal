
module Collision {
    exports asteroids.exam.collision;
    requires Common;
    requires CommonAsteroids;
    requires CommonBullet;

    uses asteroids.exam.commonasteroids.IAsteroidSplitter;
    provides asteroids.exam.common.services.IPostEntityProcessingService
            with asteroids.exam.collision.CollisionDetector;
}
