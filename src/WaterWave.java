import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Waterfall class represents a water fall which is one of the abilities that the Hero can collect.
 * 
 * @author animan_patil
 * @version 5.6.21
 */
public class WaterWave extends Circle {
	private int x,y;
	private double r,fr,vr;
	/**
	 * Creates a new instance of a Waterfall object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Waterfall will look like in the game (sprite).
	 * @param x The X value of the Waterfall's top left corner.
	 * @param y The Y value of the Waterfall's top left corner.
	 * @param w The width of the Waterfall
	 * @param h The height of the Waterfall
	 * @param vx The X component of the Projectile's velocity
	 * @param vy The Y component of the Projectile's velocity
	**/
	public WaterWave(int x, int y, double r, double fr, double vr){
		super(x,y,2*r);
		this.fr=fr;
		this.vr=vr;
	}
	public void act() {
		
	}
	public void draw(PApplet board) {
		
	}
	
	public boolean checkCollision(Enemy e, Hero h) {
		if (this.intersects(e)||this.intersects(h)) {
			return true;
		}
		return false;
	}
}
