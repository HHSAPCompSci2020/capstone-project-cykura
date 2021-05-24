
import processing.core.PImage;

/**
 * The MovingPlatform class represents a movable platform on which the Hero can stand.
 * 
 * @author alex_zheng
 * @version 5.23.21
 */
public class MovingPlatform extends MovingImage {
	private double vx, vy;
	private int fx, fy,sx,sy;
	private boolean isNeg;
	
	/**
	 * Creates a new instance of a MovingPlatform object having its left
	 * corner at the inputed (x, y) coordinates with a specified width and height.
	 * 
	 * @param img The PImage which the MovingPlatform will look like in the game (sprite).
	 * @param x The X value of the MovingPlatform's top left corner.
	 * @param y The Y value of the MovingPlatform's top left corner.
	 * @param w The width of the MovingPlatform
	 * @param h The height of the MovingPlatform
	 * @param fx The final X value of the MovingPlatform
	 * @param fy The final Y value of the MovingPlatform
	 * @param vx The velocity of the MovingPlatform in the x direction
	 * @param vy The velocity of the MovingPlatform in the y direction
	**/
	public MovingPlatform(PImage img, int x, int y, int w, int h, int fx, int fy, double vx, double vy) {
		super(img, x, y, w, h);
		this.fx=fx+1;
		this.fy=fy+1;
		this.vx=vx;
		this.vy=vy;
		sx=x-1;
		sy=y-1;
		if (vx<0||vy<0) {
			isNeg=true;
			this.fx=fx-1;
			this.fy=fy-1;
			sx=x+1;
			sy=y+1;
		}
	}
	
	/**
	 * Moves the platform.
	 */
	public void act() {
		atEnd();
		super.moveByAmount(vx, vy);
	}
	
	/**
	 * If the platform is at its end, it starts to move in the opposite direction
	 */
	private void atEnd() {
		if(!isNeg) {
			if(super.x>=fx||super.x<=sx) {
				vx=-vx;
				vy=-vy;
			}
			else if(super.y>=fy||super.y<=sy) {
				vx=-vx;
				vy=-vy;
			}
		}
		else if(isNeg) {
			if(super.x<=fx||super.x>=sx) {
				vx=-vx;
				vy=-vy;
			}
			else if(super.y<=fy||super.y>=sy) {
				vx=-vx;
				vy=-vy;
			}
		}
		return;
	}
	
	/**
	 * Gets vx
	 * @return vx
	 */
	public double getVX() {
		return vx;
	}
	
	/**
	 * Gets vy
	 * @return vy
	 */
	public double getVY() {
		return vy;
	}
}
