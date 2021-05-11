import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Waterfall class represents a water fall which is one of the abilities that the Hero can collect.
 * 
 * @author Alex Zheng
 * @version 5.6.21
 */
public class WaterWave extends Circle {
	private double fd,vd;
	/**
	 * Creates a new instance of a Waterfall object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Waterfall will look like in the game (sprite).
	 * @param x The X value of the Waterwave's center.
	 * @param y The Y value of the Waterwave's center.
	 * @param d The diameter of the Waterwave
	 * @param fd The max or final diameter of the Waterwave
	 * @param vd The velocity of the diameter of the Waterwave
	**/
	public WaterWave(int x, int y, double d, double fd, double vd){
		super(x,y,d);
		this.fd=fd;
		this.vd=vd;
	}
	public void act() {
		super.extent+=vd;
	}
	public boolean canRemove() {
		if(super.extent>=fd) return true;
		return false;
	}
	public boolean checkCollisionEnemy(Enemy e) {
		if (this.intersects(e.getBounds2D())) return true;
		return false;
	}
	public boolean checkCollisionHero(Hero h) {
		if (this.intersects(h.getBounds2D())) return true;
		return false;
	}
}
