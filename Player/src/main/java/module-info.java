import asteroids.exam.commonbullet.BulletSPI;
import asteroids.exam.player.PlayerControlSystem;
import asteroids.exam.player.PlayerPlugin;

module Player {
    exports asteroids.exam.player;
    requires Common;
    requires CommonBullet;   
    uses BulletSPI;
    provides asteroids.exam.common.services.IGamePluginService with PlayerPlugin;
    provides asteroids.exam.common.services.IEntityProcessingService with PlayerControlSystem;
}
