import processing.core.PImage;

/**
 * The Fireball class represents a fire ball which is one of the projectiles that the Hero can collect.
 * 
 * @author alex_zheng
 * @version 5.6.21
 */
public class Fireball extends Projectile {
	
	/**
	 * Creates a new instance of a Fireball object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Fireball will look like in the game (sprite).
	 * @param x The X value of the Fireball's top left corner.
	 * @param y The Y value of the Fireball's top left corner.
	 * @param w The width of the Fireball
	 * @param h The height of the Fireball
	 * @param vx The X component of the Projectile's velocity
	 * @param vy The Y component of the Projectile's velocity
	**/
	public Fireball(PImage img, int x, int y, int w, int h,double vx, double vy) {
		super(img, x, y, w, h, vx, vy);
	}
	public void act() {
		super.act();
	}
	public boolean checkCollision(Enemy e, Hero h, Platform p) {
		if (this.intersects(p)) return true;
		super.checkCollision(e, h);
		return false;
	}
}
