import asteroids.exam.common.services.IEntityProcessingService;
import asteroids.exam.common.services.IGamePluginService;
import asteroids.exam.common.services.IPostEntityProcessingService;

module Core {
    requires Common;
    requires CommonBullet;    
    requires javafx.graphics;   
    requires spring.context;
    requires spring.core;
    requires spring.beans;
    requires Collision;
    requires spring.web;
    exports asteroids.exam.core;
    opens asteroids.exam.core to javafx.graphics,spring.core;
    uses IGamePluginService;
    uses IEntityProcessingService;
    uses IPostEntityProcessingService;
}


