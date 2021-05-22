import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

public class Checkpoint extends Rectangle2D.Double {

	private PImage image;
	private PImage activeImage;
	public static final int CHECKPOINT_WIDTH = 30;
	public static final int CHECKPOINT_HEIGHT = 75;
	
	/**
	 * Creates a new instance of a Heart object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Heart will look like in the game (sprite).
	 * @param x The X value of the Heart's top left corner.
	 * @param y The Y value of the Heart's top left corner.
	**/
	public Checkpoint(PImage img, PImage activeImg, int x, int y) {
		super(x,y, CHECKPOINT_WIDTH, CHECKPOINT_HEIGHT);
		image = img;
		activeImage = activeImg;
	}
	
	public void act(Hero h) {
		if (this.intersects(h) && image != activeImage) {
			System.out.println("h");
			image = activeImage;
		}
	}
	
	
	/**
	 * Sets the minimum and maximum x and y coordinates the Checkpoint can have according to the window size.
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
	 * @param g The PApplet on which the Checkpoint is drawn.
	**/
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y, CHECKPOINT_WIDTH, CHECKPOINT_HEIGHT);
	}
	
}
