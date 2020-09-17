import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

public class Main {
    private static JFrame window;
    public static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
	public static void main(String args[]) {
		Window g = new Window();
		PApplet.runSketch(new String[]{""}, g);
		PSurfaceAWT surf = (PSurfaceAWT)g.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
        window = (JFrame)canvas.getFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        window.setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        
        int windowPosX = ((int)screenSize.getWidth() - Main.WINDOW_WIDTH)/2;
        int windowPosY = ((int)screenSize.getHeight() - Main.WINDOW_HEIGHT)/2;
        window.setLocation(windowPosX, windowPosY);
		window.setMinimumSize(new Dimension(640, 480));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        
		window.setVisible(true);
		canvas.requestFocus();
    }

    public static int getWindowWidth() {
        return WINDOW_WIDTH;
    }

    public static int getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    public static void setWindowSize(int width, int height) {
        WINDOW_WIDTH = width;
        WINDOW_HEIGHT = height;
        window.setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    }
}