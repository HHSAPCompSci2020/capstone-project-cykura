import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PImage;

/**
 * The Fireball class represents a fire ball which is a projectile that can be thrown.
 * 
 * @author alex_zheng
 * @version 5.23.21
 */
public class Fireball extends MovingImage {
	private double vy, vx;
	
	/**
	 * Creates a new instance of a Fireball object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Fireball will look like in the game (sprite).
	 * @param x The X value of the Fireball's top left corner.
	 * @param y The Y value of the Fireball's top left corner.
	 * @param w The width of the Fireball
	 * @param h The height of the Fireball
	 * @param vx The X component of the Fireball's velocity
	 * @param vy The Y component of the Fireball's velocity
	**/
	public Fireball(PImage img, int x, int y, int w, int h,double vx, double vy) {
		super(img, x, y, w, h);
		this.vx=vx;
		this.vy=vy;
	}
	
	/**
	 * Moves the Fireball according to its velocity.
	 */
	public void act() {
		super.moveByAmount(vx,vy);
	}
	
	/**
	 * Check collision with platforms
	 * @param shapes platforms in game
	 * @return true if fireball collides with any platforms
	 */
	public boolean checkCollisionShape (ArrayList<Shape> shapes) {
		for (int i=0;i<shapes.size();i++) {
			if(this.intersects((Rectangle2D) shapes.get(i))) return true;
		}
		return false;
	}
	
	/**
	 * Check collision with enemy
	 * @param e enemy 
	 * @return true if collides with enemy
	 */
	public boolean checkCollisionEnemy (Enemy e) {
		if (this.intersects(e)) return true;
		return false;
	}
	
	/**
	 * Check collision with hero
	 * @param h the hero
	 * @return true if collided with hero
	 */
	public boolean checkCollisionHero (Hero h) {
		if (this.intersects(h)) return true;
		return false;
	}
}
