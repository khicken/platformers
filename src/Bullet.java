public class Bullet {
    private Window a;
    private float x, y;
    private float v; // velocity of bullet vector
    private double angle; // angle of bullet vector (in deg)
    private float xv, yv;

    public Bullet(Window a, float x, float y, float v, double angleInDeg) {
        this.a = a;
        this.x = x;
        this.y = y;
        this.v = v;
        this.angle = angleInDeg;

        this.xv = 0;
        this.yv = 0;
    }

    public void draw() {
        update();

        a.pushMatrix();
        a.noStroke();
        a.translate(x, y);
        a.rotate((float)Math.toRadians(angle));
        a.fill(255, 240, 28);
        a.rect(0, 0, 6, 12, 3);
        a.popMatrix();

        move();
    }

    private void update() {
        x += xv;
        y += yv;
    }

    private void move() {
        xv = (float)Math.cos(angle)*v;
        yv = (float)Math.sin(angle)*v;
    }
}