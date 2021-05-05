import processing.core.PImage;

/*
 * 
 * Author: Vicram Vijayakumar
 * Date Modified: 5.4.21
 */
public class Hero extends MovingImage{
	
	public static final int HERO_WIDTH = 40;
	public static final int HERO_HEIGHT = 60;
	
//	private double vx, vy;
//	private boolean onASurface;
//	private double friction;
//	private double gravity;
	
	public Hero(PImage img, int x, int y, int w, int h) {
		super(img, x, y, HERO_WIDTH, HERO_HEIGHT);
	}

}
