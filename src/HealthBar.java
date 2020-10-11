import processing.core.PConstants;

public class HealthBar {
    private Window a;
    private float x, y, w, h;
    private float currentHealth, maxHealth;
    
    public HealthBar(Window a, float x, float y, float currentHealth, float maxHealth) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = 250;
        this.h = 50;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
    }
        
    public HealthBar(Window a, float x, float y, float w, float h, float currentHealth, float maxHealth) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
    }

    public void draw(float x, float y) {
        a.pushMatrix();
        a.translate(x, y);
        a.fill(255, 0, 0);
        a.noStroke();
        a.rectMode(PConstants.CENTER);
        a.rect(w/2, h/2, w, h);

        a.fill(0, 0, 0, 0);
        a.stroke(0);
        a.strokeWeight(5);
        a.rect(w/2, h/2, w, h);
        
        a.fill(0);
        a.textAlign(PConstants.CENTER);
        a.text((int)currentHealth + " / " + (int)maxHealth, w/2, h/2);

        a.noStroke();
        a.popMatrix();
    }

    public void updateStats(float ch, float mh) {
        currentHealth = ch;
        maxHealth = mh;
    }
}