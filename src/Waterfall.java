import processing.core.PImage;

/**
 * The Waterfall class represents a water fall which is one of the abilities that the Hero can collect.
 * 
 * @version 5.6.21
 */
public class Waterfall extends Projectile {
	
	/**
	 * Creates a new instance of a Waterfall object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Waterfall will look like in the game (sprite).
	 * @param x The X value of the Waterfall's top left corner.
	 * @param y The Y value of the Waterfall's top left corner.
	 * @param w The width of the Waterfall
	 * @param h The height of the Waterfall
	**/
	public Waterfall(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
	}

}
