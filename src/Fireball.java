import processing.core.PImage;

/**
 * The Fireball class represents a fire ball which is one of the projectiles that the Hero can collect.
 * 
 * @version 5.6.21
 */
public class Fireball extends Projectile {
	
	/**
	 * Creates a new instance of a Fireball object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Fireball will look like in the game (sprite).
	 * @param x The X value of the Fireball's top left corner.
	 * @param y The Y value of the Fireball's top left corner.
	 * @param w The width of the Fireball
	 * @param h The height of the Fireball
	**/
	public Fireball(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
	}

}
