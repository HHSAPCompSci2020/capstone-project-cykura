import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Spike class represents a Spike which can cause damage to the Hero and Enemies if they walk into it.
 * 
 * @author vicram_vijayakumar
 * @version 5.6.21
 */
public class Spike extends Rectangle2D.Double {

	private PImage img;
	private int invincibilityTime;
	
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
		this.img = img;
		invincibilityTime = 0;
	}
	
	/**
	 * Draws the Spike.
	 * 
	 * @param g The PApplet on which the Spike is drawn.
	**/
	public void draw(PApplet g) {
		g.image(img,(int)x,(int)y,(int)width,(int)height);
	}
	
	public void act(ArrayList<Enemy> enemies, Hero h) {
//		System.out.println(invincibilityTime);
		if (invincibilityTime <= 0) {
			if (h.intersects(this)) {
				h.jump();
				invincibilityTime = 60;
				h.loseHearts(1);

			}
		} else {
			invincibilityTime--;
		}
	}
	
//	public boolean checkCollision(Enemy e, Hero h) {
//		if (this.intersects(e)||this.intersects(h)) {
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean checkHeroCollsion(Hero h) {
//		return false;
//	}
	
	/**
	 * Sets the minimum and maximum x and y coordinates the Spike can have according to the window size.
	 * 	  
	 * @param windowWidth The width of the window.
	 * @param windowHeight The height of the window.
	**/
	public void applyWindowLimits(int windowWidth, int windowHeight) {
		x = Math.min(x,windowWidth-width);
		y = Math.min(y,windowHeight-height);
		x = Math.max(0,x);
		y = Math.max(0,y);
	}
	
	
}
