import java.awt.*;
import java.util.*;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
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
	private int hearts;
	private ArrayList<Ability> collectedAbilities;
	private boolean canDash;
	private int facingDirection;
	private int invincibilityTime;

	public Hero(PImage img, int x, int y) {
		super(img, x, y, HERO_WIDTH, HERO_HEIGHT);
		vx = 0;
		vy = 0;
		onASurface = false;
		gravity = 0.9;
		friction = 0.75;
		hearts = 5;
		canDash = false;
		collectedAbilities = new ArrayList<Ability>();
		facingDirection = 0;	// right direction
	}
	
	public void addAbility(Ability a) {
		collectedAbilities.add(a);
	}
	
	public void setDash(boolean state) {
		canDash = state;
	}
	
	public void setFacingDirection(int x) {	// 0 for facing right, 180 for left
		facingDirection = x;
	}
	
	public void punch(Enemy e1) {
//		System.out.println("h " + this.getCenterX());
//		System.out.println("e " + e1.getCenterX());
//		System.out.println(e1.getCenterX() - this.getCenterX());
		if(Math.abs(e1.getCenterX() - this.getCenterX()) < 75) {
			e1.loseHealth(3);
		}
	}

	public void walk(int direction) {
		if (vx <= 10 && vx >= -10)
			vx += direction;
		//System.out.println("Walk is called");
	}

	public void jump() {
		//System.out.println("Jump is called");
		if (onASurface) {
			vy -= 15;
		}
	}
	
	public void dash(double direction) {
		if(canDash && facingDirection == 0) {
			vx += (direction*6);
		} else if (canDash && facingDirection == 180) {
			vx += (direction*-6);
		}
	}
	
	public int getHearts() {
		return hearts;
	}
	
	public void draw(PApplet g) {
		super.draw(g);
		g.text("Hearts: " + hearts, (int)x-10, (int)y-20);
	}

	public void act(ArrayList<Shape> platforms, Enemy enemy) {
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
		//System.out.println(vx);
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
		
		checkCollision(enemy);
		
		//System.out.println(x2+" "+y2);
		moveToLocation(x2,y2);
	}
	
	
	public void checkCollision(Enemy e1) {
		if (invincibilityTime > 0) {
			invincibilityTime--;
		}
		
		if ((this.intersects(e1)) && (invincibilityTime == 0)) {
			hearts--;
			jump();
			invincibilityTime = 80;
		}
	}
	

}
