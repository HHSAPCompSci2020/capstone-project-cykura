import java.awt.*;
import java.util.*;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Hero class represents the playable main character which can move and use
 * special projectiles. Base code credit: Mr. Shelby
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
	private WaterWave wave;

	private boolean canThrowFireball;
	private boolean canWaterWave;
	private boolean canDash;

	private boolean onASurface;
	private int hearts;

	private int chargeTime;

	private boolean dashing;
	private int fireballCoolDown;
	private int punchCoolDown;
	private int waterWaveCoolDown;
	private int spikeCD;
	private boolean hitBySpike;

	/**
	 * Creates a new instance of a Hero object having its left corner at the inputed
	 * (x, y) coordinates.
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
		canThrowFireball = false;
		canWaterWave = false;
		canDash = false;
		hearts = 5;
		
		hitBySpike = false;

		gravity = 0.5;
		friction = 0.85;

		facingDirection = 0; // right direction
		dashing = false;


		fireballs = new ArrayList<Fireball>();

	}

	/**
	 * Makes the Hero jump if the Hero is on a surface.
	 * 
	 **/
	public void jump() {
		if (onASurface) {
				vy -= 11;
		}
	}

	/**
	 * 
	 * @return The fireballs which the Hero has thrown.
	 */
	public ArrayList<Fireball> getFireballs() {
		return fireballs;
	}

	/**
	 * Hero throws a fireball
	 */
	public void throwFireball() {
		if (canThrowFireball && fireballCoolDown <=0) {
			if (facingDirection == 0) { // Facing to the right
				Fireball f = new Fireball(GameScreen.fireball, (int) (x + 30), (int) (y + 20), 20, 20, 5, 0);
				fireballs.add(f);
				fireballCoolDown = 30;
			} else { // Facing to the left
				Fireball f = new Fireball(GameScreen.fireball, (int) (x - 10), (int) (y + 20), 20, 20, -5, 0);
				fireballs.add(f);
				fireballCoolDown = 30;
			}
		}
	}
	
	/**
	 * Hero creates a Water wave
	 */
	public void doWaterWave() {
		if (canWaterWave) {
			if (waterWaveCoolDown <= 0) {
				waterWaveCoolDown = 60;
				wave = new WaterWave((int) x +20, (int) y +30, (double) 50, (double)120, (double)4);
			}
		}
	}
	
	/**
	 * The hero gains an inputted number of hearts
	 * 
	 * @param h The number of hearts the hero gains.
	 */
	public void gainHearts(int h) {
		hearts += h;
	}

	/**
	 * 
	 * @return How long the hero has been charging
	 */
	public int getChargeTime() {
		return chargeTime;
	}

	/**
	 * 
	 * @return if the Hero is dashing or not
	 */
	public boolean isDashing() {
		return dashing;
	}

	/**
	 * Gets whether the Hero is on a surface or not.
	 * 
	 * @return true if the Hero is standing on a surface.
	 **/
	public boolean getOnASurface() {
		return onASurface;
	}
	
	public boolean canDash() {
		return canDash;
	}

	/**
	 * Sets whether the Hero is able to dash or not.
	 * 
	 * @param state The boolean which sets whether the Hero can dash or not.
	 **/
	public void setDash(boolean state) {
		canDash = state;
	}
	
	public boolean canThrowFireball() {
		return canThrowFireball;
	}
	
	/**
	 * Sets whether the Hero is able to throw a fireball or not.
	 * 
	 * @param state The boolean which sets whether the Hero can throw a fireball or not.
	 **/
	public void setCanThrowFireball(boolean state) {
		canThrowFireball = state;
	}
	
	public boolean canWaterWave() {
		return canWaterWave;
	}
	
	/**
	 * Sets whether the Hero is able to do a water wave or not.
	 * 
	 * @param state The boolean which sets whether the Hero is able to water wave or not.
	 **/
	public void setCanWaterWave(boolean state) {
		canWaterWave = state;
	}

	/**
	 * Sets what direction the Hero is facing.
	 * 
	 * @param x The facing direction of the Hero.
	 * @pre The value of x is either 0 (right) or 180 (less)
	 **/
	public void setFacingDirection(int x) { // 0 for facing right, 180 for left
		facingDirection = x;
	}

	/**
	 * Deals damage to an enemy by punching it.
	 * 
	 * @param e1 The enemy which the Hero deals damage to.
	 * @pre The distance from the center of the Hero's x coordinate to the center of
	 *      the Enemy's x coordinate must be less than 75 to cause damage.
	 **/
	public void punch(Enemy e1) {
			if (punchCoolDown <= 0) {	// if can punch
				punchCoolDown = 60;
//				System.out.println(e1);
				e1.loseHealth(30);
				
			}
//		}


	}
	
	public int getPunchCoolDown() {
		return punchCoolDown;
	}
	
	public int getFireballCoolDown() {
		return fireballCoolDown;
	}
	
	public int getWaterWaveCoolDown() {
		return waterWaveCoolDown;
	}

	/**
	 * Makes the Hero walk depending on the inputed direction.
	 * 
	 * @param direction The direction and magnitude which the Hero will move in.
	 **/
	public void walk(int direction) {
		if ((chargeTime <= 0 && dashing == false) && (hitBySpike == false)) {	// only walk if u are not charging or if u are not dashing & ur not in a spike
			if (vx <= 10 && vx >= -10)
				vx += direction;
		}
	}

	/**
	 * Deals damage to the Hero by losing an inputed number of hearts.
	 * 
	 * @param hearts The amount of hearts the hero loses
	 * @pre hearts must be less than six
	 */
	public void loseHearts(int heartsToLose) {
		hearts -= heartsToLose;
	}

	/**
	 * Charges the hero to dash.
	 * 
	 */
	public void charge() {
		if (canDash) {
			if (chargeTime < 180)	// Max Charge Time is 3 seconds
				chargeTime++;

		}
	}

	/**
	 * Makes the Hero dash depending on the direction which the Hero is facing
	 * and how long it is charged.
	 * 
	 **/
	public void dash() {
		// 1sec - 60, 2 sec - 120, 3 sec - 180
		if (canDash == true) {
//			System.out.println(canDash);
			if (chargeTime == 180) {	// equal to 3 sec (Max charge amount)
				chargeTime = 0;
				dashing = true;
				if (canDash && facingDirection == 0) { // Facing to the right
					moveByAmount(100, 0);
				} else if (canDash && facingDirection == 180) { // Facing to the left
					moveByAmount(-100, 0);
				}
			} else if (chargeTime >= 120) {	// greater than equal to 2 sec
				chargeTime = 0;
				dashing = true;
				if (canDash && facingDirection == 0) { // Facing to the right
					moveByAmount(75, 0);
				} else if (canDash && facingDirection == 180) { // Facing to the left
					moveByAmount(-75, 0);
				}
			} else if (chargeTime >= 60) {	// greater than equal to 1 sec 
				chargeTime = 0;
				dashing = true;
				if (canDash && facingDirection == 0) { // Facing to the right
					moveByAmount(50, 0);
				} else if (canDash && facingDirection == 180) { // Facing to the left
					moveByAmount(-50, 0);
				}
			} else if (chargeTime > 0) {	// greater than 0 secs but less than 1 sec
				chargeTime = 0;
				dashing = true;
				if (canDash && facingDirection == 0) { // Facing to the right
					moveByAmount(25, 0);
				} else if (canDash && facingDirection == 180) { // Facing to the left
					moveByAmount(-25, 0);
				}
			}
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
	 * @param g The PApplet to draw the Hero, its thrown fireballs, and water wave
	 **/
	public void draw(PApplet g) {
		super.draw(g); 	// draws hero
		for (Fireball f : fireballs) {	// draws fireballs
			if (f != null) {
				f.draw(g);
			}
		}
		
		

		
		if (wave != null) {
			wave.draw(g);
		}
	
		if (chargeTime > 0) {	
			g.fill(50);
			g.rect((int)x-10, (int)y-20, 60, 5, 3);	// draws the rectangle
			
			if (chargeTime == 180) {	// equal to 3 sec (Max charge amount)
				// green color fill
				g.fill(0, 255, 0);
			} else if (chargeTime >= 120) {	// greater than equal to 2 sec
				// yellow color fill
				g.fill(255, 215, 0);
			} else if (chargeTime >= 60) {	// greater than equal to 1 sec 
				// orange color fill
				g.fill(255, 165, 0);
			} else if (chargeTime > 0) {	// greater than 0 secs but less than 1 sec
				//red color fill
				g.fill(255, 0, 0);
			}

			g.rect((int)x-10, (int)y-20, (int)(chargeTime/3.0), 5,3);
			

		}
	}


	/**
	 * Makes the Hero act by moving, being affected by gravity and friction, and get
	 * hurt if attacked by enemy.
	 * 
	 * @param platforms The platforms which the hero can stand on and interact with.
	 * @param enemy The enemy which can attack the Hero and cause damage.
	 * @param enemyFireballs The fireballs which the Enemy has thrown.
	 **/
	public void act(ArrayList<Shape> platforms, ArrayList<Enemy> enemies, ArrayList<Token> tokens, ArrayList<Spike> spikes, ArrayList<Platform> movingPlatforms	) {
//		System.out.println("x :" + x);
//		System.out.println("y: " + y);
		
		if (fireballCoolDown > 0) {
			fireballCoolDown--;
		}
		if (punchCoolDown > 0) {
			punchCoolDown--;
		}
		
		if (waterWaveCoolDown > 0) {
			waterWaveCoolDown--;
		}
		
		if (spikeCD > 0) {
			spikeCD--;
		}
		
		if (invincibilityTime > 0) {
			invincibilityTime--;
		}
		
//		System.out.println(punchCoolDown);
		
		double x = getX();
		double y = getY();
		double width = getWidth();
		double height = getHeight();

		if (onASurface) {
			dashing = false;
		}
		
//		if (hitBySpike && !onASurface) {
//			hitBySpike = false;
//			hearts--;
//		}
		

		// ***********Y AXIS***********
		double y2 = y;
		
		vy += gravity; // GRAVITY
		y2 = y + vy;
		// System.out.println(vy);
		Rectangle2D.Double strechY = new Rectangle2D.Double(x, Math.min(y, y2), width, height + Math.abs(vy));

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
				y2 = r.getY() - height;
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
				y2 = r.getY() + r.getHeight();
			}
		}

		if (Math.abs(vy) < .5)
			vy = 0;
		
		

		vx *= friction;

		double x2 = x + vx;
		// System.out.println(vx);
		Rectangle2D.Double strechX = new Rectangle2D.Double(Math.min(x, x2), y2, width + Math.abs(vx), height);

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
				x2 = r.getX() - width;
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
				x2 = r.getX() + r.getWidth();
			}
		}

		if (Math.abs(vx) < .5)
			vx = 0;

		moveToLocation(x2, y2);

		if (enemies != null) {		// check if the hero is going to get hurt
			for (Enemy e: enemies) {
				if (e != null) {
					checkEnemyCollision(e);	// check if hero gets hit by enemy
					if (e instanceof FireEnemy) {
						if (((FireEnemy) e).getFireballs() != null) {	// if fireenemy has thrown fireballs
							checkFireballCollision(((FireEnemy) e).getFireballs());	// Check if hero got hit by FireEnemy's fireballs
						}
					} else if (e instanceof Boss) {		// if the enemy is the boss
						if (((Boss) e).getFireballs() != null) {	// if boss has thrown fireballs
//							System.out.println("Bos threw fire");
//							System.out.println(((Boss) e).getFireballs());
							checkFireballCollision(((Boss) e).getFireballs());	// Check if hero got hit by FireEnemy's fireballs
						}
					}
				}
			}
		}
		
//		System.out.println("hit by spike before spike check " + hitBySpike);
		
		for (int i = 0; i < spikes.size(); i++) {
			if (spikes.get(i).intersects(this)) {
//				System.out.println(i + " spike check: " + hitBySpike);
				if (hitBySpike == false) {
//					System.out.println("hit by spike is now true");
					hitBySpike = true;
					
				}
			}
			
		}
		
		/*if (hitBySpike) {
			if(spikeCD<=0) {
				spikeCD = 30;
				hearts--;
				if (dashing) {
					dashing = false;
				}
				vy=-15;
			}
			hitBySpike = false;
//			jump();
			
		}*/
		if (hitBySpike) {

			hearts--;
			if (dashing) {
				dashing = false;
			}
			
			if (vy > 0) {	// moving down
				vy = -13;			
			} else if (vx < 0) {	// moving left
//				System.out.println("Moving left");
				vx = 5;
			} else if (vx > 0) {	// moving right
//				System.out.println("Moving right");
				vx = -5;
			}
			hitBySpike = false;
//			jump();
			
		}
		
		// CHECK IF THE ENEMIES ARE GOING TO GET HURT
		if (this.fireballs != null && canThrowFireball) { // If the Hero has thrown some fireballs
			for (int i = 0; i < fireballs.size(); i++) {
				Fireball f = fireballs.get(i);
				if (f != null) {
					f.act();
					if (f.checkCollisionShape(platforms)) {
						fireballs.set(i, null);
					}
					
					if (enemies!= null) {
						for (Enemy e: enemies) {
							if (e!= null) {
								if(f.checkCollisionEnemy(e)) {
									fireballs.set(i, null);
									e.loseHealth(10);
								}
							}
						}
					}
					
				}
			}
		}
		
		if (wave != null) {	// if there is a wave
			wave.act();
			if (enemies != null) {
				for (Enemy e: enemies) {
					if (e != null) {
						if (wave.checkCollisionEnemy(e)) {
							e.loseHealth(20);
						}
					}
				}
			}
			if (wave.canRemove()) {
				wave = null;
			}
		}
		

			
		
//		
		for (int i = 0; i < tokens.size(); i++) {
			if (tokens.get(i) != null) {
				if (tokens.get(i).getImage() == GameScreen.fireToken) {
					if (tokens.get(i).intersects(this)) {
						canThrowFireball = true;
//						tokens.get(i).moveTo(10, 10);
//						tokens.set(i, null);
					}
				} else if (tokens.get(i).getImage() == GameScreen.waterToken) {
					if (tokens.get(i).intersects(this)) {
						canWaterWave = true;
						
//						tokens.set(i, null);
					}
				} else if (tokens.get(i).getImage() == GameScreen.grassToken) {
					if (tokens.get(i).intersects(this)) {
						canDash = true;
//						tokens.set(i, null);
					}
				}
			}

		}

	}

	/**
	 * Makes the Hero lose a heart and jump up if it collides with an enemy.
	 * The Hero also gains some invinciblity for some time after it collides with an enemy.
	 * 
	 * @param enemy The enemy which can attack the Hero and cause damage.
	 **/
	public void checkEnemyCollision(Enemy e1) {
		if (invincibilityTime <= 0) {
			if (this.intersects(e1)) {
				hearts--;
				jump();
				invincibilityTime = 120;
			}
		}
	}

	/**
	 * Checks whether the Hero gets hit by a Fireball. If so, the Hero loses
	 * damage and gains some time in which the Hero is invincible.
	 * 
	 * @param p The list of Fireballs to check with
	 **/
	public void checkFireballCollision(ArrayList<Fireball> p) {
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i) != null) {
//				System.out.println("n");
				if (this.intersects(p.get(i))) {
//					System.out.println("p");
					hearts--;
					p.set(i, null);
				}
			}
		}
	}


}
