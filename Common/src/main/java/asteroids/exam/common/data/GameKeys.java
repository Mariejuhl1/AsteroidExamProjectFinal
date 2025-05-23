package asteroids.exam.common.data;

public class GameKeys {

    private static boolean[] key;                         // current state
    private static boolean[] previouskey;                 // previous state

    private static final int NUM_KEYS = 4;                // total keys
    public static final int W     = 0;                    // move up
    public static final int A     = 1;                    // move left
    public static final int D     = 2;                    // move right
    public static final int SPACE = 3;                    // fire

    public GameKeys() {
        key = new boolean[NUM_KEYS];                      // init arrays
        previouskey = new boolean[NUM_KEYS];
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            previouskey[i] = key[i];                      // store last state
        }
    }

    public void setKey(int keyCode, boolean isPressed) {
        key[keyCode] = isPressed;                         // set key down/up
    }

    public boolean isDown(int keyCode) {
        return key[keyCode];                              // is key held?
    }

    public boolean isPressed(int keyCode) {
        return key[keyCode] && !previouskey[keyCode];     // was just pressed
    }
}
