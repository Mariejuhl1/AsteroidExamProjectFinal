import asteroids.exam.enemyspaceship.EnemySpaceshipPlugin;
import asteroids.exam.enemyspaceship.EnemySpaceshipSystem;

module EnemySpaceship {
    exports asteroids.exam.enemyspaceship;

    requires java.desktop;
    requires Common;
    requires jdk.compiler;
    requires CommonBullet;

    uses asteroids.exam.commonbullet.BulletSPI;
    provides asteroids.exam.common.services.IGamePluginService with EnemySpaceshipPlugin;
    provides asteroids.exam.common.services.IEntityProcessingService with EnemySpaceshipSystem;
}