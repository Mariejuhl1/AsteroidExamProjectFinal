import asteroids.exam.bullet.BulletControlSystem;
import asteroids.exam.bullet.BulletPlugin;

module Bullet {
    requires Common;
    requires CommonBullet;
    provides asteroids.exam.common.services.IGamePluginService with BulletPlugin;
    provides asteroids.exam.commonbullet.BulletSPI with BulletControlSystem;
    provides asteroids.exam.common.services.IEntityProcessingService with BulletControlSystem;
}