import processing.core.PImage;

/**
 * The FireEnemy class represents an Enemy with the Fireball Ability which the Player can defeat.
 * 
 * @version 5.6.21
 */
public class FireEnemy extends Enemy{
	
	/**
	 * Creates a new instance of a FireEnemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the FireEnemy will look like in the game (sprite).
	 * @param x The X value of the FireEnemy's top left corner.
	 * @param y The Y value of the FireEnemy's top left corner.
	**/
	public FireEnemy(PImage img, int x, int y) {
		super(img, x, y);
	}

}
