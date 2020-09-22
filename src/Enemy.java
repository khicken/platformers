public class Enemy extends Entity {
    public Enemy(Window a, float x, float y, float w, String fileName) {
        super(a, x, y, w, ".\\..\\assets\\enemies\\", fileName);
    }

    // public void draw() {
    //     a.imageMode(PConstants.CENTER);
    //     a.pushMatrix();
    //     a.translate(x, y);
    //     a.image(img, 0, 0);
    //     a.popMatrix();
    // }
}