import processing.core.PConstants;

public class XPBar {
    private Window a;
    private float x, y, w, h;
    private Player player;
    private float fillRatio;
    
    public XPBar(Window a, float x, float y, Player player) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = 250;
        this.h = 50;
        this.player = player;
    }
        
    public XPBar(Window a, float x, float y, float w, float h, Player player) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.player = player;
    }

    public void draw() {
        // color
        a.pushMatrix();
        a.translate(x, y);
        a.fill(67, 241, 250);
        a.noStroke();
        a.rectMode(PConstants.CENTER);
        a.rect(w/2, h/2, w, h, 15);

        // border
        a.fill(0, 0, 0, 0);
        a.stroke(0);
        a.strokeWeight(2);
        a.rect(w/2, h/2, w, h, 15);
        
        // text
        a.fill(0);
        a.textFont(a.createFont("Trebuchet MS", h/2));
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.text(player.getXp() + " / " + player.getXpToNextLevel(), w/2, h/2);

        a.noStroke();
        a.popMatrix();
    }
}