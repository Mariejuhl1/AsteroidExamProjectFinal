import asteroids.exam.asteroids.AsteroidSplitterImpl;
import asteroids.exam.asteroids.AsteroidPlugin;
import asteroids.exam.asteroids.AsteroidProcessor;

module Asteroid {
    exports asteroids.exam.asteroids;
    requires Common;
    requires CommonAsteroids;
    provides asteroids.exam.common.services.IGamePluginService with AsteroidPlugin;
    provides asteroids.exam.common.services.IEntityProcessingService with AsteroidProcessor;
    provides asteroids.exam.commonasteroids.IAsteroidSplitter
            with AsteroidSplitterImpl;

}