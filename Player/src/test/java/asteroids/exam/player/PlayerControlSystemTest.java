package asteroids.exam.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import asteroids.exam.common.data.GameData;
import asteroids.exam.common.data.GameKeys;
import asteroids.exam.common.data.World;
import asteroids.exam.common.entityparts.PositionPart;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControlSystemTest {

    private PlayerControlSystem pcs;  // system under test
    private GameData gameData;        // shared game state
    private World world;              // game world with entities
    private Player player;            // player entity
    private PositionPart pos;         // player's position part

    @BeforeEach
    void setUp() {
        pcs      = new PlayerControlSystem();                     // create control system
        gameData = new GameData();                                // init game data
        world    = new World();                                   // init world

        // create player at (100,100) facing 0°
        player = new Player();
        pos    = new PositionPart(100.0, 100.0, 0.0);
        player.add(PositionPart.class, pos);
        world.addEntity(player);                                  // add to world
    }

    @Test
    void testNoKeyPressed_PlayerStaysInPlace() {
        double x0 = pos.getX();
        double y0 = pos.getY();
        double r0 = pos.getRotation();

        pcs.process(gameData, world);                             // run with no keys

        assertEquals(x0, pos.getX(), "X should not change when no key is pressed");
        assertEquals(y0, pos.getY(), "Y should not change when no key is pressed");
        assertEquals(r0, pos.getRotation(), "Rotation should not change when no key is pressed");
    }

    @Test
    void testLeftKey_PlayerRotatesLeft() {
        gameData.getKeys().setKey(GameKeys.A, true);              // press rotate-left key

        pcs.process(gameData, world);

        assertEquals(-5.0, pos.getRotation(), "Rotation should decrease by 5° on LEFT key");
    }

    @Test
    void testRightKey_PlayerRotatesRight() {
        gameData.getKeys().setKey(GameKeys.D, true);              // press rotate-right key

        pcs.process(gameData, world);

        assertEquals(5.0, pos.getRotation(), "Rotation should increase by 5° on RIGHT key");
    }

    @Test
    void testUpKey_PlayerMovesForward() {
        pos.setRotation(90.0);                                    // face up
        gameData.getKeys().setKey(GameKeys.W, true);              // press thrust key

        pcs.process(gameData, world);

        // cos(90°)=0, sin(90°)=1 so only Y moves +1
        assertEquals(100.0, pos.getX(), 1e-6, "X should remain the same when moving up");
        assertEquals(101.0, pos.getY(), 1e-6, "Y should increase by 1 when moving up");
    }

    @Test
    void testBounds_PlayerClampedInsideScreen() {
        // place player out of bounds
        pos.setX(-10.0);
        pos.setY(gameData.getDisplayHeight() + 20.0);

        pcs.process(gameData, world);                             // run clamping logic

        // expect X clamped to 1, Y clamped to max-1
        assertEquals(1.0, pos.getX(), "X below 0 should be clamped to 1");
        assertEquals(gameData.getDisplayHeight() - 1.0,
                pos.getY(),
                "Y above max should be clamped to height - 1");
    }
}
