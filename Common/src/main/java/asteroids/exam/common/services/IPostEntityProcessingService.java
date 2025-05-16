package asteroids.exam.common.services;

import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;

/**
 * This interface is for systems that run after the game has updated all entities.
 * It is usually used to check if things have crashed into each other (collisions)
 * or to clean up things that should be removed.
 */
public interface IPostEntityProcessingService {

    /**
     * Runs after all game objects have been updated.
     * For example, this can be used to check if any objects have collided or should be removed.
     *
     * @param gameData the current game state.
     * @param world the game world with updated entities.
     *
     * Preconditions:
     * - All IEntityProcessingService systems have already run for this frame.
     * - gameData and world are valid and not null.
     *
     * Postconditions:
     * Some entities may have been removed after collision
     */
    void process(GameData gameData, World world);
}
