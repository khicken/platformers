import processing.core.PApplet;

public class Main {
	public static void main(String args[]) {
		Window g = new Window();
        PApplet.runSketch(new String[]{""}, g);
    }

    public static int booleanToInt(boolean b) {
        if(b) return 1;
        return 0;
    }
}