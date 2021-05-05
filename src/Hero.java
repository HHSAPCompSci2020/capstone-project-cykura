import java.awt.*;
import java.util.*;
import java.awt.geom.Rectangle2D;
import processing.core.PImage;

/*
 * Base Code Credit: Mr. Shelby
 * Modified by: Vicram Vijayakumar
 * Date Modified: 5.4.21
 */
public class Hero extends MovingImage{
	
	public static final int HERO_WIDTH = 40;
	public static final int HERO_HEIGHT = 60;
	
	private double vx, vy;
	private boolean onASurface;
	private double friction;
	private double gravity;
	
	public Hero(PImage img, int x, int y) {
		super(img, x, y, HERO_WIDTH, HERO_HEIGHT);
		vx = 0;
		vy = 0;
		onASurface = false;
		gravity = 0.7;
		friction = .85;
		//jumpStrength = 15;
	}
	
	public void walk(int direction) {
		if (vx <= 10 && vx >= -10)
			vx += direction;
		System.out.println(vx);
	}
	
	public void jump() {
		if (onASurface) {
			vy -= 15;
		}
	}
	
	public void act(ArrayList<Shape> platforms) {
		double x = getX();
		double y = getY();
		double width = getWidth();
		double height = getHeight();
		
		// ***********Y AXIS***********

				vy += gravity; // GRAVITY
				double y2 = y + vy;
				//System.out.println(vy);
				Rectangle2D.Double strechY = new Rectangle2D.Double(x,Math.min(y,y2),width,height+Math.abs(vy));

				onASurface = false;

				if (vy > 0) {
					Shape standingSurface = null;
					for (Shape s : platforms) {
						if (s.intersects(strechY)) {
							onASurface = true;
							standingSurface = s;
							vy = 0;
						}
					}
					if (standingSurface != null) {
						Rectangle r = standingSurface.getBounds();
						y2 = r.getY()-height;
					}
				} else if (vy < 0) {
					Shape headSurface = null;
					for (Shape s : platforms) {
						if (s.intersects(strechY)) {
							headSurface = s;
							vy = 0;
						}
					}
					if (headSurface != null) {
						Rectangle r = headSurface.getBounds();
						y2 = r.getY()+r.getHeight();
					}
				}

				if (Math.abs(vy) < .5)
					vy = 0;

				// ***********X AXIS***********


				vx *= friction;

				double x2 = x + vx;
				System.out.println(vx);
				Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(x,x2),y2,width+Math.abs(vx),height);

				if (vx > 0) {
					Shape rightSurface = null;
					for (Shape s : platforms) {
						if (s.intersects(strechX)) {
							rightSurface = s;
							vx = 0;
						}
					}
					if (rightSurface != null) {
						Rectangle r = rightSurface.getBounds();
						x2 = r.getX()-width;
					}
				} else if (vx < 0) {
					Shape leftSurface = null;
					for (Shape s : platforms) {
						if (s.intersects(strechX)) {
							leftSurface = s;
							vx = 0;
						}
					}
					if (leftSurface != null) {
						Rectangle r = leftSurface.getBounds();
						x2 = r.getX()+r.getWidth();
					}
				}


				if (Math.abs(vx) < .5)
					vx = 0;
				System.out.println(x2+" "+y2);
				moveToLocation(x2,y2);
	}

}
