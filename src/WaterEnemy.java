import processing.core.PImage;

/**
 * The WaterEnemy class represents an Enemy with the Waterfall projectile which the Player can defeat.
 * 
 * @author vicram_vijayakumar
 * @version 5.6.21
 */
public class WaterEnemy extends Enemy {
	
	/**
	 * Creates a new instance of a WaterEnemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the WaterEnemy will look like in the game (sprite).
	 * @param x The X value of the WaterEnemy's top left corner.
	 * @param y The Y value of the WaterEnemy's top left corner.
	**/
	public WaterEnemy(PImage img, int x, int y) {
		super(img, x, y);
	}

}
