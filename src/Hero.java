import java.awt.*;
import java.util.*;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Hero class represents the playable main character which can move and use special projectiles.
 * Base code credit: Mr. Shelby
 * 
 * @author vicram_vijayakumar
 * @version 5.6.21
 */
public class Hero extends MovingImage{

	public static final int HERO_WIDTH = 40;
	public static final int HERO_HEIGHT = 60;

	private double vx, vy;
	private boolean onASurface;
	private double friction;
	private double gravity;
	private int hearts;
	private ArrayList<Projectile> collectedProjectiles;
	private boolean canDash;
	private int facingDirection;
	private int invincibilityTime;
	
	/**
	 * Creates a new instance of a Hero object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the Hero will look like in the game (sprite).
	 * @param x The X value of the Hero's top left corner.
	 * @param y The Y value of the Hero's top left corner.
	**/
	public Hero(PImage img, int x, int y) {
		super(img, x, y, HERO_WIDTH, HERO_HEIGHT);
		vx = 0;
		vy = 0;
		onASurface = false;
		gravity = 0.9;
		friction = 0.75;
		hearts = 5;
		canDash = false;
		collectedProjectiles = new ArrayList<Projectile>();
		facingDirection = 0;	// right direction
	}
	
	/**
	 * Adds a projectile to the Hero's list of projectiles.
	 * @param a An projectile to add to the Hero's list of projectiles.
	**/
	public void addAbility(Projectile a) {
		collectedProjectiles.add(a);
	}
	
	/**
	 * Sets whether the Hero is able to dash or not.
	 *  
	 * @param state The boolean which sets whether the Hero can dash or not.
	**/
	public void setDash(boolean state) {
		canDash = state;
	}
	
	/**
	 * Sets what direction the Hero is facing.
	 *  
	 * @param x The facing direction of the Hero.
	 * @pre The value of x is either 0 (right) or 180 (less)
	**/
	public void setFacingDirection(int x) {	// 0 for facing right, 180 for left
		facingDirection = x;
	}
	
	/**
	 * Deals damage to an enemy by punching it.
	 *  
	 * @param e1 The enemy which the Hero deals damage to.
	 * @pre The distance from the center of the Hero's x coordinate to the center of the Enemy's x coordinate must be less than 75 to cause damage.
	**/
	public void punch(Enemy e1) {
//		System.out.println("h " + this.getCenterX());
//		System.out.println("e " + e1.getCenterX());
//		System.out.println(e1.getCenterX() - this.getCenterX());
		if (e1 != null) {
			if(Math.abs(e1.getCenterX() - this.getCenterX()) < 75) {
				e1.loseHealth(3);
			}
		}
	}
	
	/**
	 * Makes the Hero walk depending on the inputed direction.
	 *  
	 * @param direction The direction and magnitude which the Hero will move in.
	**/
	public void walk(int direction) {
		if (vx <= 10 && vx >= -10)
			vx += direction;
		//System.out.println("Walk is called");
	}
	
	/**
	 * Makes the Hero jump if the Hero is on a surface.
	 *   
	**/
	public void jump() {
		//System.out.println("Jump is called");
		if (onASurface) {
			vy -= 15;
		}
	}
	
	/**
	 * Makes the Hero dash depending on the direction which the Hero is facing.
	 *  
	**/
	public void dash() {
		if(canDash && facingDirection == 0) {
			vx += (6);
		} else if (canDash && facingDirection == 180) {
			vx += (-6);
		}
	}
	
	/**
	 * Gets the amount of hearts the Hero currently has.
	 *  
	**/
	public int getHearts() {
		return hearts;
	}
	
	/**
	 * Draws the Hero and displays its number of hearts above the Hero.
	 * 
	 * @param g The PApplet to draw the Hero and display the text which has the number of hearts the Hero has.
	**/
	public void draw(PApplet g) {
		super.draw(g);
		g.text("Hearts: " + hearts, (int)x-10, (int)y-20);
	}
	
	/**
	 * Makes the Hero act by moving, being affected by gravity and friction, and get hurt if attacked by enemy.
	 * 
	 * @param platforms The platforms which the hero can stand on and interact with.
	 * @param enemy The enemy which can attack the Hero and cause damage.
	**/
	public void act(ArrayList<Shape> platforms, Enemy enemy, ArrayList<Fireball> fireballs) {
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
		
		if (enemy != null) {
			checkCollision(enemy);
			checkProjectileCollsion(fireballs);
		}
		//System.out.println(x2+" "+y2);
		moveToLocation(x2,y2);
	}
	
	/**
	 * Makes the Hero lose a heart and jump up if it collides with an enemy.
	 * 
	 * @param enemy The enemy which can attack the Hero and cause damage.
	**/
	public void checkCollision(Enemy e1) {
		if (invincibilityTime > 0) {
			invincibilityTime--;
		}
		
		if (((this.intersects(e1)) && (invincibilityTime == 0))) {
			hearts--;
			jump();
			invincibilityTime = 80;
		}
	}
	
	public void checkProjectileCollsion(ArrayList<Fireball> p) {
		if (invincibilityTime > 0) {
			invincibilityTime--;
		}
		for (Projectile p1: p) {
			if (((this.intersects(p1)) && (invincibilityTime == 0))) {
				hearts--;
				invincibilityTime = 80;
				p1 = null;
			}
		}
	}
	

}
