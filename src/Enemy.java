import java.util.ArrayList;
import processing.core.PConstants;

public class Enemy extends Character {

    public Enemy(Window a, float x, float y, float w, String fileName, float maxHealth) {
        super(a, x, y, w, ".\\..\\assets\\enemies\\", fileName, maxHealth);
    }

    @Override
    public void draw() {
        super.draw();
    }
}