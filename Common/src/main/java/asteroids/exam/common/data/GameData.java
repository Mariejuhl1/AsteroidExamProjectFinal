package asteroids.exam.common.data;

public class GameData {

    private int displayWidth  = 840;                      // default width
    private int displayHeight = 640;                      // default height
    private final GameKeys keys = new GameKeys();         // input state

    public GameKeys getKeys() {
        return keys;                                      // access keys
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;                        // change width
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;                      // change height
    }

    public int getDisplayWidth() {
        return displayWidth;                              // current width
    }

    public int getDisplayHeight() {
        return displayHeight;                             // current height
    }
}
