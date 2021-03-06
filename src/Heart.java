import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Heart class represents a heart which the Hero can collect to gain health
 * @author vicram_vijayakumar
 * @version 5.23.21
 */
public class Heart extends Rectangle2D.Double {

	private PImage image;
	public static final int HEART_WIDTH = 30;
	public static final int HEART_HEIGHT = 30;
	
	/**
	 * Creates a new instance of a Heart object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Heart will look like in the game (sprite).
	 * @param x The X value of the Heart's top left corner.
	 * @param y The Y value of the Heart's top left corner.
	**/
	public Heart(PImage img, int x, int y) {
		super(x,y, HEART_WIDTH, HEART_HEIGHT);
		image = img;
	}
	
	/**
	 * Sets the minimum and maximum x and y coordinates the Heart can have according to the window size.
	 * 	  
	 * @param windowWidth The width of the window.
	 * @param windowHeight The height of the window.
	**/
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	/**
	 * Draws the Heart.
	 * 
	 * @param g The PApplet on which the Heart is drawn.
	**/
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y, HEART_WIDTH, HEART_HEIGHT);
	}
	
}
