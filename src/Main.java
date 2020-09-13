import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Main {
    public static int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 720;
	public static void main(String args[]) {
		Game g = new Game();
		PApplet.runSketch(new String[]{""}, g);
		PSurfaceAWT surf = (PSurfaceAWT)g.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
        JFrame window = (JFrame)canvas.getFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        window.setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        window.setLocation((int)screenSize.getWidth() - Main.WINDOW_WIDTH, (int)screenSize.getHeight() - Main.WINDOW_HEIGHT);
		window.setMinimumSize(new Dimension(640, 480));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        
		window.setVisible(true);
		canvas.requestFocus();
	}
}