package asteroids.exam.common.data;

public class GameKeys {

    private static boolean[] key;
    private static boolean[] previouskey;

    private static final int NUM_KEYS = 4;
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int SPACE = 3;

    public GameKeys() {
        key = new boolean[NUM_KEYS];
        previouskey = new boolean[NUM_KEYS];
    }

    public void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            previouskey[i] = key[i];
        }
    }

    public void setKey(int keyCode, boolean isPressed) {
        key[keyCode] = isPressed;
    }

    public boolean isDown(int keyCode) {
        return key[keyCode];
    }

    public boolean isPressed(int keyCode) {
        return key[keyCode] && !previouskey[keyCode];
    }

}
