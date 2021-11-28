public class Enemy extends Character {
    private float damage;

    private HealthBar healthBar;

    public Enemy(Window a, float x, float y, float w, String fileName, float maxHealth, float damage) {
        super(a, x, y, w, System.getProperty("user.dir") + "/assets/enemies/", fileName, maxHealth);

        this.damage = damage;
        
        healthBar = new HealthBar(a, x, y, this);
    }

    @Override
    public void draw() {
        super.draw();

        healthBar.draw(x+w/2, y-w/4);
    }

    public void takeDamage(float val) {
        currentHealth -= val;
        currentHealth = constrain(currentHealth, 0, maxHealth);

        if(currentHealth == 0)
            isDead = true;
    }

    /*************************** GETTERS ***************************/

    public boolean isDead() {
        return isDead;
    }

    public float getDamage() {
        return damage;
    }
}