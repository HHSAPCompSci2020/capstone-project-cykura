import java.awt.geom.Rectangle2D;

import processing.core.PImage;

/**
 * The Spike class represents a Spike which can cause damage to the Hero and Enemies if they walk into it.
 * 
 * @author vicram_vijayakumar
 * @version 5.6.21
 */
public class Spike extends Rectangle2D.Double {


	/**
	 * Creates a new instance of a Spike object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Spike will look like in the game (sprite).
	 * @param x The X value of the Spike's top left corner.
	 * @param y The Y value of the Spike's top left corner.
	 * @param w The width of the Spike
	 * @param h The height of the Spike
	**/
	public Spike(PImage img, int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	
}
