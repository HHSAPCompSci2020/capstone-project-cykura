import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PImage;

/**
 * The Fireball class represents a fire ball which is one of the projectiles that the Hero can collect.
 * 
 * @author alex_zheng
 * @version 5.6.21
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
	public void act() {
		super.moveByAmount(vx,vy);
	}
	public boolean checkCollisionShape (ArrayList<Shape> shapes) {
		for (int i=0;i<shapes.size();i++) {
			if(this.intersects((Rectangle2D) shapes.get(i))) return true;
		}
		return false;
	}
	public boolean checkCollisionEnemy (Enemy e) {
		if (this.intersects(e)) return true;
		return false;
	}
	public boolean checkCollisionHero (Hero h) {
		if (this.intersects(h)) return true;
		return false;
	}
}
