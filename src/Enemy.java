import java.util.ArrayList;

public class Enemy extends Character {
    private HealthBar healthBar;
    public Enemy(Window a, float x, float y, float w, String fileName, float maxHealth) {
        super(a, x, y, w, System.getProperty("user.dir") + "/assets/enemies/", fileName, maxHealth);

        healthBar = new HealthBar(a, x, y, this);
    }

    @Override
    public void draw() {
        super.draw();

        healthBar.draw(x+w/2, y-w/4);
    }

    public void damage(float val) {
        currentHealth -= val;
        currentHealth = constrain(currentHealth, 0, maxHealth);

        if(currentHealth == 0)
            isDead = false;
    }
}