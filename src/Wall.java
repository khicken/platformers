public class Wall extends Entity {
    Wall(Window a, float x, float y, float w, String fileName) {
        super(a, x, y, w, System.getProperty("user.dir") + "/assets/tiles/", fileName);
    }
}
