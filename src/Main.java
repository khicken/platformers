import processing.core.PApplet;

// note: might abandon this project for a while, game enginer much better as of 12/6/21

public class Main {
	public static void main(String[] args) {
		Window g = new Window();
        PApplet.runSketch(new String[]{""}, g);
    }

    public static int booleanToInt(boolean b) {
        if(b) return 1;
        return 0;
    }
}