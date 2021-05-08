import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The WaterEnemy class represents an Enemy with the Waterfall projectile which the Player can defeat.
 * 
 * @author animan_patil
 * @version 5.6.21
 */
public class WaterEnemy extends Enemy {
	private Waterfall w;
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
	
	public void act(Hero h, ArrayList<Shape> platforms) {
		if(w==null)
//		w = new Waterfall(DrawingSurface.water,(int)x-10,(int)y+40,60,20,0,6);
		w.act();
		w.checkPlatforms(platforms);
	}
	
	public void draw(PApplet g) {
		super.draw(g);
		if(w!=null)
		w.draw(g);
	}
}
