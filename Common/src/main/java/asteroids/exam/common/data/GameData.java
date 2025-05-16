package asteroids.exam.common.data;

public class GameData {

    private int displayWidth  = 840;
    private int displayHeight = 640;
    private final GameKeys keys = new GameKeys();


    public GameKeys getKeys() {
        return keys;
    }

    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    public void setDisplayHeight(int height) {
        this.displayHeight = height;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }


}
