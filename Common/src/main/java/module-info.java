import asteroids.exam.common.services.IEntityProcessingService;
import asteroids.exam.common.services.IGamePluginService;
import asteroids.exam.common.services.IPostEntityProcessingService;

module Common {
    requires java.desktop;
    exports asteroids.exam.common.services;
    exports asteroids.exam.common.data;
    exports asteroids.exam.common.util;
    exports asteroids.exam.common.entityparts;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}
