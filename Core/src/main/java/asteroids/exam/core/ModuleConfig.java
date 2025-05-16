package asteroids.exam.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import asteroids.exam.collision.CollisionDetector;
import asteroids.exam.common.services.IEntityProcessingService;
import asteroids.exam.common.services.IGamePluginService;
import asteroids.exam.common.services.IPostEntityProcessingService;
import asteroids.exam.common.services.ScoreService;
import asteroids.exam.common.util.ServiceLocator;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Configuration
class ModuleConfig {

    @Bean
    public Game game() {
        return new Game(
                gamePluginServices(),
                entityProcessingServiceList(),
                postEntityProcessingServices(scoreService(restTemplate()))
        );
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServiceList() {
        return ServiceLocator.INSTANCE.locateAll(IEntityProcessingService.class);
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLocator.INSTANCE.locateAll(IGamePluginService.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ScoreService scoreService(RestTemplate restTemplate) {
        return new ScoreClient(restTemplate);
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices(ScoreService scoreService) {
        return ServiceLocator.INSTANCE.locateAll(IPostEntityProcessingService.class)
                .stream()
                .peek(svc -> {
                    if (svc instanceof CollisionDetector) {
                        ((CollisionDetector) svc).setScoreService(scoreService);
                    }
                })
                .collect(toList());
    }
}
