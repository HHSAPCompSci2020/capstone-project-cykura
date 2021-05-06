import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

/**
 * Main class that runs the program
 * @author Alex
 *
 */
public class Main {
	
	
	public static void main(String args[]) {

		DrawingSurface drawing = new DrawingSurface();
		PApplet.runSketch(new String[]{""}, drawing);
		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame)canvas.getFrame();

		window.setSize(800, 500);
		window.setMinimumSize(new Dimension(100,100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);
		
		JOptionPane.showMessageDialog(window,
				"Space: Punch (Get Close to enemy to hit)" + "\n" + "D to Dash" + "\n"
						+ "Arrow Keys to Move." + "\n" + "If you can't move the player, click the window" +"\n" +"Good Luck!");
		
	}

}
