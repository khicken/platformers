import processing.core.PConstants;
import processing.core.PImage;

// displays stats in the game screen of playerdata

public class PlayerBar {
    private Window a;
    private Player p;
    private HealthBar healthBar;
    private XPBar xpBar;
    private PImage hpImg, xpImg, weaponImg;

    public PlayerBar(Window a, Player player) {
        this.a = a;
        this.p = player;
        
        healthBar = new HealthBar(a, 150, 10, 250, 35, player);
        xpBar = new XPBar(a, 150, 75-(35/2), 250, 35, player);

        hpImg = a.loadImage(System.getProperty("user.dir") + "/assets/game/heart.png");
        hpImg.resize(50, 50);
        xpImg = a.loadImage(System.getProperty("user.dir") + "/assets/game/xp.png");
        xpImg.resize(50, 50);
        weaponImg = player.getPlayerWeapon().getSprite();
        float k = weaponImg.width/60;
        weaponImg.resize(60, (int)(weaponImg.height/k));
    }

    public void draw() {
        // weapon frame
        a.fill(150);
        a.stroke(0);
        a.strokeWeight(3);
        a.ellipseMode(PConstants.CORNER);
        a.ellipse(15, 15, 75, 75);
        // weapon
        a.imageMode(PConstants.CENTER);
        a.image(weaponImg, 52.5f, 55f);
        // weapon bullet info frame
        a.fill(200);
        a.noStroke();
        a.rectMode(PConstants.CENTER);
        a.rect(52.5f, 90.0f, 75.0f, 25.0f, 5);
        a.fill(0);
        // weapon bullet info
        a.textAlign(PConstants.CENTER, PConstants.CENTER);
        a.textFont(a.createFont("Trebuchet MS", 15));
        if(p.getPlayerWeapon().getBulletsLeft() + p.getPlayerWeapon().getBulletsLeftInMagazine() < p.getPlayerWeapon().getMagazineCapacity())
            a.fill(100, 0, 0);
        else
            a.fill(0);
        a.text(p.getPlayerWeapon().getBulletsLeftInMagazine() + "|" + p.getPlayerWeapon().getBulletsLeft(), 52.5f, 88.0f);
        
        // hp
        a.imageMode(PConstants.CORNER);
        a.image(hpImg, 100, 15);
        healthBar.draw();

        // xp
        a.image(xpImg, 100, 75-20);
        xpBar.draw();
    }
}
