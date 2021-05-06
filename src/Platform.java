import processing.core.PImage;

/**
 * The Platform class represents a movable platform on which the Hero and enemies can stand. The Platform may contain Spikes.
 * 
 * @version 5.6.21
 */
public class Platform extends MovingImage {

	/**
	 * Creates a new instance of a Platform object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Platform will look like in the game (sprite).
	 * @param x The X value of the Platform's top left corner.
	 * @param y The Y value of the Platform's top left corner.
	 * @param w The width of the Platform
	 * @param h The height of the Platform
	**/
	public Platform(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
	}

}
