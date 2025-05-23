package asteroids.exam.common.services;

import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.World;

/**
 * A system that updates entities during the game loop.
 */
public interface IEntityProcessingService {

    /**
     * Updates relevant entities based on the current game state.
     *
     * @param gameData the current game data (inputs, display size, etc.).
     * @param world the world containing all game entities.

     * Preconditions:
     * - gameData and world are initialized and not null.
     * - The world contains zero or more entities.

     * Postconditions:
     * - The state of relevant entities has been updated.
     */
    void process(GameData gameData, World world);
}
