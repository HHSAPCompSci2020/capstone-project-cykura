import processing.core.PImage;

/**
 * The GrassEnemy class represents an Enemy with the Leaf Dash move which the Player can defeat.
 * 
 * @author alex_zheng
 * @version 5.6.21
 */
public class GrassEnemy extends Enemy {

	/**
	 * Creates a new instance of a GrassEnemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the GrassEnemy will look like in the game (sprite).
	 * @param x The X value of the GrassEnemy's top left corner.
	 * @param y The Y value of the GrassEnemy's top left corner.
	**/
	public GrassEnemy(PImage img, int x, int y) {
		super(img, x, y);
	}

}
