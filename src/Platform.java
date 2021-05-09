import processing.core.PImage;

/**
 * The Platform class represents a movable platform on which the Hero and enemies can stand. The Platform may contain Spikes.
 * 
 * @version 5.6.21
 */
public class Platform extends MovingImage {
	private double vx, vy;
	private int fx, fy;
	
	/**
	 * Creates a new instance of a Platform object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the Platform will look like in the game (sprite).
	 * @param x The X value of the Platform's top left corner.
	 * @param y The Y value of the Platform's top left corner.
	 * @param w The width of the Platform
	 * @param h The height of the Platform
	**/
	public Platform(PImage img, int x, int y, int w, int h, int fx, int fy, double vx, double vy) {
		super(img, x, y, w, h);
		this.fx=fx;
		this.fy=fy;
		this.vx=vx;
		this.vy=vy;
	}
	public void act() {
		atEnd();
		super.moveByAmount(vx, vy);
	}
	private void atEnd() {
		if(super.x==fx&&super.y==fy) {
			vx=-vx;
			vy=-vy;
		}
		return;
	}
}
