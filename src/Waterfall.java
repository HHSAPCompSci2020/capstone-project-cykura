import processing.core.PImage;

/**
 * The Waterfall class represents a water fall which is one of the abilities that the Hero can collect.
 * 
 * @author vicram_vijayakumar
 * @version 5.6.21
 */
public class Waterfall extends Projectile {
	private int waterHeight;
	private int max;
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
	public Waterfall(PImage img, int x, int y, int w, int h, double vx, double vy){
		super(img, x, y, w, h, vx, vy);
	}
	public void act() {
		if(waterHeight<max) {
			waterHeight+=vy;
		}
		//Is this really a projectile? 
		//Extend the waterfall height till ground/set height and then it will be solid block and dissapear
	}
	public boolean checkCollision(Enemy e, Hero h) {
		super.checkCollision(e, h);
		return false;
	}
}
