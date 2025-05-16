package asteroids.exam.common.services;

import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;

/**
 * Interface for game plugins that can be loaded at runtime.
 * Plugins are responsible for creating and removing their own entities.
 */
public interface IGamePluginService {

    /**
     * Called when the plugin is started. This method should create and add entities to the world.
     *
     * @param gameData the current game data
     * @param world the world containing all game entities.
     *
     * Preconditions:
     * gameData and world are initialized an not null.
     *
     * Postconditions:
     * One or more new entities have been added to the world.
     */
    void start(GameData gameData, World world);

    /**
     * Called when the plugin is stopped. This method should remove the plugin’s entities from the world.
     *
     * @param gameData same
     * @param world same
     *
     * Preconditions:
     * - gameData and world are initialized and not null.
     *
     * Postconditions:
     * All entities created by the plugin are removed from the world.
     */
    void stop(GameData gameData, World world);
}
