import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Checkpoint class represents a checkpoint which the hero can activate to respawn to.
 *  
 * @author vicram_vijayakumar
 * @version 5.23.21
 */
public class Checkpoint extends Rectangle2D.Double {

	private PImage image;
	private PImage activeImage;
	public static final int CHECKPOINT_WIDTH = 30;
	public static final int CHECKPOINT_HEIGHT = 75;
	
	/**
	 * Creates a new instance of a Checkpoint object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the deactivated Checkpoint will look like in the game (sprite).
	 * @param activeImg The PImage which the activated Checkpoint will look like in the game (sprite).
	 * @param x The X value of the Checkpoint's top left corner.
	 * @param y The Y value of the Checkpoint's top left corner.
	**/
	public Checkpoint(PImage img, PImage activeImg, int x, int y) {
		super(x,y, CHECKPOINT_WIDTH, CHECKPOINT_HEIGHT);
		image = img;
		activeImage = activeImg;
	}
	
	/**
	 * Changes its image if the Hero activates it
	 * and sets the Hero's respawn location.
	 * 
	 * @param h The Hero which can be played.
	 */
	public void act(Hero h) {
		if (this.intersects(h) && image != activeImage) {
//			System.out.println("h");
			image = activeImage;
			h.setCheckpoint(this.x, this.y);
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
	 * Draws the Checkpoint.
	 * 
	 * @param g The PApplet on which the Checkpoint is drawn.
	**/
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y, CHECKPOINT_WIDTH, CHECKPOINT_HEIGHT);
	}
	
}
