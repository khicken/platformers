public class PlayerBar {
    private Window a;

    public PlayerBar(Window a) {
        this.a = a;
    }

    public void draw() {
        a.fill(150);
        a.noStroke();
        a.beginShape();
        a.vertex(1280/6, 720);
        a.vertex(1280/5, 720-100);
        a.vertex(1280-(1280/5), 720-100);
        a.vertex(1280-(1280/6), 720);
        a.vertex(1280/6, 720);
        a.endShape();
    }
}
