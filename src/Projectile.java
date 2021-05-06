import processing.core.PImage;

/**
 * The Projectile class represents the different projectiles which the player can gain and the enemies have.
 * 
 * @version 5.6.21
 */
public class Projectile extends MovingImage{
	private double vy, vx;
	/**
	 * Creates a new instance of a Projectile object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Projectile will look like in the game (sprite).
	 * @param x The X value of the Projectile's top left corner.
	 * @param y The Y value of the Projectile's top left corner.
	 * @param w The width of the Projectile
	 * @param h The height of the Projectile
	**/
	public Projectile(PImage img, int x, int y, int w, int h, double vy, double vx) {
		super(img, x, y, w, h);
		this.vy=vy;
		this.vx=vx;
	}
	public void act() {
		super.moveByAmount(vx,vy);
	}
}