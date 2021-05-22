
import java.awt.geom.Rectangle2D;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The MovingImage class represents an image which moves.
 * 
 * @author Mr. Shelby
 * @version 5.6.21
 */
public class MovingImage extends Rectangle2D.Double {
	
	// FIELDS
	protected PImage image;
	
	// CONSTRUCTORS
	
	/**
	 * Creates a new instance of a MovingImage object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the MovingImage will look like in the game (sprite).
	 * @param x The X value of the MovingImage's top left corner.
	 * @param y The Y value of the MovingImage's top left corner.
	 * @param w The width of the MovingImage
	 * @param h The height of the MovingImage
	**/
	public MovingImage(PImage img, int x, int y, int w, int h) {
		super(x,y,w,h);
		image = img;
	}
	
	
	// METHODS	
	
	/**
	 * Moves the MovingImage to a specified location.
	 * 	  
	 * @param x The X coordinate of the location to move to.
	 * @param y The Y coordinate of the location to move to.
	**/
	public void moveToLocation(double x, double y) {
		super.x = x;
		super.y = y;
	}
	
	/**
	 * Moves the MovingImage by a certain amount.
	 * 	  
	 * @param x The X value to move the MovingImage by.
	 * @param y The Y value to move the MovingImage by.
	**/
	public void moveByAmount(double x, double y) {
		super.x += x;
		super.y += y;
	}
	
	/**
	 * Sets the minimum and maximum x and y coordinates the MovingImage can have according to the window size.
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
	 * Draws the MovingImage.
	 * 
	 * @param g The PApplet on which the MovingImage is drawn.
	**/
	public void draw(PApplet g) {
		g.image(image,(int)x,(int)y,(int)width,(int)height);
	}
	
	
	public void setImage(PImage img) {
		image = img;
	}
	
}

