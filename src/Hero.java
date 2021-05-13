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
public class Hero extends MovingImage {

	public static final int HERO_WIDTH = 40;
	public static final int HERO_HEIGHT = 60;

	private double vx, vy;
	private double friction;
	private double gravity;
	
	private int facingDirection;
	private int invincibilityTime;
	
	private ArrayList<Fireball> fireballs;
//	private ArrayList<Heart> hearts;
	
	private boolean canThrowFireball;
	private boolean canWaterWave;
	private boolean canDash;
	
	private boolean onASurface;
	private int hearts;
	
	private int chargeTime;
	
//	private boolean affectedByGravity;
	private boolean dashing;
	private int chargeCoolDown;
	private int fireballCoolDown;
	
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
		canThrowFireball = true;
		canDash = false;
		hearts = 5;

		gravity = 0.5;
		friction = 0.85;

		facingDirection = 0;	// right direction
		dashing = false;
		
		fireballs = new ArrayList<Fireball>();
		
//		hearts = new ArrayList<Heart>();
//		hearts.add(new Heart(GameScreen.heart, 10, 10, 30, 30));
//		generateHearts();
		
//		affectedByGravity = true;
	}
	
	/**
	 * Makes the Hero jump if the Hero is on a surface.
	 *   
	**/
	public void jump() {
//		System.out.println("Jump is called");
		if (onASurface) {
			vy -= 11;
		}
	}
	
	public ArrayList<Fireball> getFireballs() {
		return fireballs;
	}
	
	public void throwFireball() {
		if (canThrowFireball&&fireballCoolDown>30) {
			if (facingDirection == 0) {		// Facing to the right
		    	Fireball f = new Fireball(GameScreen.fireball, (int)(x+30), (int)(y+20), 20, 20, 5, 0);
		    	fireballs.add(f);
		    	fireballCoolDown = 0;
			} else {	// Facing to the left
		    	Fireball f = new Fireball(GameScreen.fireball, (int)(x-10), (int)(y+20), 20, 20, -5, 0);
		    	fireballs.add(f);
		    	fireballCoolDown = 0;
			}
		}
	}
	
	
//	public void makeWaterWave() {
//		
//	}
	
	
	public int getChargeTime() {
		return chargeTime;
	}
	
//	public boolean isCharging() {
//		return charging;
//	}
	
	public boolean isDashing() {
		return dashing;
	}
	
	/**
	 * Gets whether the Hero is on a surface or not.
	 * @return true if the Hero is standing on a surface.
	**/
	public boolean getOnASurface() {
		return onASurface;
	}
	
	
	/**
	 * Sets whether the Hero is able to dash or not.
	 *  
	 * @param state The boolean which sets whether the Hero can dash or not.
	**/
	public void setDash(boolean state) {
		canDash = state;
	}
	
	public void setCanThrowFireball(boolean state) {
		canThrowFireball = state;
	}
	
	public void setCanWaterWave(boolean state) {
		canWaterWave = state;
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
//		System.out.println("Walk is called");
	}
	
	
	/**
	 * Deals damage to the Hero by losing an inputted number of hearts.
	 * 
	 * @param hearts The amount of hearts the hero loses
	 * @pre hearts must be less than six
	 */
	public void loseHearts(int heartsToLose) {
		hearts -= heartsToLose;
	}
	
	
	public void charge() {
		chargeTime++;
	}
	
	
	/**
	 * Makes the Hero dash depending on the direction which the Hero is facing.
	 *  
	**/
	public void dash() {
//		charging = false;
//		if (dashing == false) {
//			dashing = true;
		if (chargeTime > 100) {
			dashing = true;
			chargeTime = 0;
			if(canDash && facingDirection == 0) {	// Facing to the right
				moveByAmount(50, 0);
			} else if (canDash && facingDirection == 180) {		// Facing to the left
				moveByAmount(-50, 0);
			}
		}

//			dashing = false;
//		} else {
//			dashing = false;
//		}

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
		for(Fireball f: fireballs) {
			if(f!=null) {
				f.draw(g);
			}
		}
//		drawHearts(g);
		
		
		
//		for (Heart h: hearts) {
//			h.draw(g);
//		}
//		g.text(" " + hearts.size(), (int) x-10, (int) y-10);
//		hearts.get(0).draw(g);
//		g.text("Hearts: " + hearts.size(), (int)x-10, (int)y-20);
	}
	
	
//	public void drawHearts(PApplet g) {
//		ArrayList<Heart> h = new ArrayList<Heart>();
//		for (int i = 0; i < hearts; i++) {
//			if (i == 0) {
//				h.add(new Heart(GameScreen.heart, (int) (x-350), 30, 30, 30));
//			} else {
//				//h.add(new Heart(GameScreen.heart, 10, (int) (h.get(0).y + 20), 30, 30));
//				h.add(new Heart(GameScreen.heart, (int) (h.get(0).x+40*i), (int) h.get(0).y, 30, 30));
//			}
//		}
//		
//		for (Heart he : h) {
//			he.draw(g);
//		}
//	}
	
	/**
	 * Makes the Hero act by moving, being affected by gravity and friction, and get hurt if attacked by enemy.
	 * 
	 * @param platforms The platforms which the hero can stand on and interact with.
	 * @param enemy The enemy which can attack the Hero and cause damage.
	**/
	public void act(ArrayList<Shape> platforms, Enemy enemy, ArrayList<Fireball> enemyFireballs) {
		fireballCoolDown++;
		double x = getX();
		double y = getY();
		double width = getWidth();
		double height = getHeight();
		
		if (onASurface) {
			dashing = false;
		}
		
//		System.out.println("ct: " + chargeTime);
//		System.out.println(dashing);
//		System.out.println();
		
		// ***********Y AXIS***********
		double y2 = y;
		if ((chargeTime <= 0 && dashing == false) || (chargeTime <= 0 && dashing == true)) {	// get affected by gravity if charging or if finished dashing but has not touched ground
//			System.out.println("d");
			vy += gravity; // GRAVITY
			y2 = y + vy;
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
			
		} 
//		else if (dashing == true && chargeTime <= 0) {	// Done dashing and now should fall
////			System.out.println("dashing/charging");
//			vy += gravity; // GRAVITY
//			y2 = y + vy;
//			if (Math.abs(vy) < .5)
//				vy = 0;
//			
//		}


		// ***********X AXIS***********

		double x2 = x;
		
		if (chargeTime <= 0 && dashing == false) {
			vx *= friction;

			x2 = x + vx;
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
			
			moveToLocation(x2,y2);
			
		} else if (dashing == true && chargeTime <= 0) {	// Done dashing and now should fall
//			System.out.println("dashing/charging");
			moveToLocation(x2, y2);
		}
		


		if (enemy != null) {
			checkEnemyCollision(enemy);
		}
		
		if (enemyFireballs != null) {
			checkFireballCollision(enemyFireballs);	// checking if Hero got hit with Fireballs
			/*for(int i = 0; i < fireballs.size(); i++) {
		    	 Fireball f = fireballs.get(i);
		    	 if(f != null) {
		    		 f.act();
		    	 }
		     }*/
		}
		
		
		if (this.fireballs != null && canThrowFireball) {	// If the Hero has thrown some fireballs
			for(int i = 0; i < fireballs.size(); i++) {
				Fireball f = fireballs.get(i);
				if(f != null) {
					f.act();
		    		 if(f.checkCollisionShape(platforms)) {
		    			 fireballs.set(i, null);
		    		 }
				}
			}
		}
		
		
		//System.out.println(x2+" "+y2);
	}
	
	/**
	 * Makes the Hero lose a heart and jump up if it collides with an enemy.
	 * 
	 * @param enemy The enemy which can attack the Hero and cause damage.
	**/
	public void checkEnemyCollision(Enemy e1) {
		if (invincibilityTime > 0) {
			invincibilityTime--;
		}
		
		if (((this.intersects(e1)) && (invincibilityTime == 0))) {
			hearts--;
			jump();
			invincibilityTime = 150;
		}
	}
	
	/**
	 * Checks whether the Hero gets hit by a Projectile.
	 * If so, the Hero loses damage and gains some time in which the Hero is invincible.
	 * 
	 * @param p The list of Fireballs to check with
	**/
	public void checkFireballCollision(ArrayList<Fireball> p) {
//		if (invincibilityTime > 0) {
//			invincibilityTime--;
//		}
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i) != null) {
//				if (((this.intersects(p.get(i))) && (invincibilityTime == 0))) {
				if (this.intersects(p.get(i))) {
//					System.out.println(hearts);
					hearts--;
//					System.out.println(hearts);
//					invincibilityTime = 80;
					//p.remove(i);
					//i--;
				}
			}
		}
	}
	
	
//	private void generateHearts() {
//		hearts.add(new Heart(GameScreen.heart, (int) 10, (int) 10, 30, 30));
//		hearts.add(new Heart(surface.loadImage("sprites\\FullHeart.png"), (int) (hearts.get(0).x + 35), (int) (hearts.get(0).y)));
//		return hearts;
//	}
	

}
