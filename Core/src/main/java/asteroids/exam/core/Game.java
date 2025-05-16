package asteroids.exam.core;

import asteroids.exam.common.data.Entity;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.GameKeys;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.PositionPart;
import asteroids.exam.common.services.IEntityProcessingService;
import asteroids.exam.common.services.IGamePluginService;
import asteroids.exam.common.services.IPostEntityProcessingService;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Game {

    private final GameData gameData = new GameData();
    private final World world = new World();
    private final Pane gameWindow = new Pane();
    private final Map<Entity, Polygon> shapes = new ConcurrentHashMap<>();

    private final List<IGamePluginService> pluginList;
    private final List<IEntityProcessingService> processorList;
    private final List<IPostEntityProcessingService> postProcessorList;

    Game(List<IGamePluginService> plugins,
         List<IEntityProcessingService> processors,
         List<IPostEntityProcessingService> postProcessors) {
        this.pluginList = plugins;
        this.processorList = processors;
        this.postProcessorList = postProcessors;
    }

    public void start(Stage window) {
        gameWindow.setPrefSize(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        gameWindow.getChildren().add(new Text(10, 20, "Destroyed asteroids: 0"));

        Scene scene = new Scene(gameWindow);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) gameData.getKeys().setKey(GameKeys.LEFT, true);
            if (event.getCode() == KeyCode.RIGHT) gameData.getKeys().setKey(GameKeys.RIGHT, true);
            if (event.getCode() == KeyCode.UP) gameData.getKeys().setKey(GameKeys.UP, true);
            if (event.getCode() == KeyCode.SPACE) gameData.getKeys().setKey(GameKeys.SPACE, true);
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) gameData.getKeys().setKey(GameKeys.LEFT, false);
            if (event.getCode() == KeyCode.RIGHT) gameData.getKeys().setKey(GameKeys.RIGHT, false);
            if (event.getCode() == KeyCode.UP) gameData.getKeys().setKey(GameKeys.UP, false);
            if (event.getCode() == KeyCode.SPACE) gameData.getKeys().setKey(GameKeys.SPACE, false);
        });

        for (IGamePluginService plugin : pluginList) {
            plugin.start(gameData, world);
        }

        for (Entity entity : world.getEntities()) {
            Polygon shape = new Polygon(entity.getPolygonCoordinates());
            shapes.put(entity, shape);
            gameWindow.getChildren().add(shape);
        }

        window.setScene(scene);
        window.setTitle("Asteroids Exam");
        window.show();
    }

    public void render() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
                draw();
                gameData.getKeys().update();
            }
        }.start();
    }

    private void update() {
        for (IEntityProcessingService processor : processorList) {
            processor.process(gameData, world);
        }
        for (IPostEntityProcessingService postProcessor : postProcessorList) {
            postProcessor.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : shapes.keySet()) {
            if (!world.getEntities().contains(entity)) {
                Polygon removed = shapes.remove(entity);
                gameWindow.getChildren().remove(removed);
            }
        }

        for (Entity entity : world.getEntities()) {
            Polygon shape = shapes.get(entity);
            if (shape == null) {
                shape = new Polygon(entity.getPolygonCoordinates());
                shapes.put(entity, shape);
                gameWindow.getChildren().add(shape);
            }

            PositionPart position = entity.getPart(PositionPart.class);
            if (position != null) {
                shape.setTranslateX(position.getX());
                shape.setTranslateY(position.getY());
                shape.setRotate(position.getRotation());
            }
        }
    }

    public List<IGamePluginService> getGamePluginServices() {
        return pluginList;
    }

    public List<IEntityProcessingService> getEntityProcessingServices() {
        return processorList;
    }

    public List<IPostEntityProcessingService> getPostEntityProcessingServices() {
        return postProcessorList;
    }
}
