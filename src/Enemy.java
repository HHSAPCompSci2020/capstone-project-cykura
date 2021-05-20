import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Enemy class represents the enemy which can move and cause damage to the Hero.
 * 
 * @author animan_patil
 * @version 5.6.21
 */
public class Enemy extends MovingImage{
	
	public static final int ENEMY_WIDTH = 40;
	public static final int ENEMY_HEIGHT = 60;
	public int health;
	protected int v;
	protected int waitTime;
	protected Point spawnPoint;
	
	/**
	 * Creates a new instance of a Enemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the Enemy will look like in the game (sprite).
	 * @param x The X value of the Enemy's top left corner.
	 * @param y The Y value of the Enemy's top left corner.
	**/
	public Enemy(PImage img, int x, int y) {
		super(img, x, y, 40, 60);
		v = 2;
		health = 100;
		spawnPoint = new Point(x,y);
	}
	
	/**
	 * Changes state of enemy according to position in game
	 * @param hero the player
	 * @param obstacles the platforms
	 */
	public void act(Hero hero, ArrayList<Shape> obstacles, ArrayList<Token> tokens) {
		if(health>0) {
			 double x1 = hero.x;
		     double y1 = hero.y;
	
		     double diffX = x1 - x;
		     double diffY = y1 - y;
//		     System.out.println("1x: " + x + ", 1y: " + y);
//		     System.out.println("1sx: " + spawnPoint.x + ", 1sy: " + spawnPoint.y);
//		     System.out.println();
		     if(Math.abs(x-spawnPoint.x)<500&&Math.abs(y-spawnPoint.y)<300&&Math.abs(spawnPoint.x-hero.x)<500&&Math.abs(spawnPoint.y-hero.y)<300) {
			     float angle = (float)Math.atan2(diffY, diffX);
			     if(this.intersects(hero)) {
		//	    	 System.out.println("Collided with hero");
			    	 waitTime=30;
			     }
			     if(waitTime<=0) {
			    	 x += v * Math.cos(angle);
			    	 y += v * Math.sin(angle);
			    	 waitTime = 0;
			     }
			     else {
			    	 waitTime--;
			     }
		     } else {
//		    	 System.out.println("far");
//			     System.out.println("x: " + x + ", y: " + y);
//			     System.out.println("sx: " + spawnPoint.x + ", sy: " + spawnPoint.y);
		    	 
		    	 float angle = (float)Math.atan2(spawnPoint.y-y, spawnPoint.x-x);
//		    	 System.out.println("angle " + angle);
//			     System.out.println("cosAngle " + Math.cos(angle));
//			     System.out.println("sinAngle " + Math.sin(angle));
//			     System.out.println("V " + v);
		    	 x += (v) * Math.cos(angle);
		    	 y += (v) * Math.sin(angle);
		    	 
		    	 
//			     System.out.println();

		     }
		     
		     if (hero.getFireballs() != null) {
			     for(int i=0;i<hero.getFireballs().size();i++) {
			    	 Fireball f = hero.getFireballs().get(i);
			    	 if(f!=null) {
			    		 if (f.checkCollisionEnemy(this)) {	// If the hero's fireball hits an enemy
			    			 this.loseHealth(10); 	// Enemy loses 10 hp
			    			 hero.getFireballs().set(i, null);
		    			 }

			    	 }
			     }

		     }
		     
		     
		}
		else {
			tokens.add(new Token(GameScreen.heart,(int)x,(int)y));
			x = -20;
			y=-20;
		}
	}
	
	/**
	 * Makes enemy lose health
	 * @param damage health to be lost
	 */
	public void loseHealth(int damage) {
		health-=damage;
	}
	
	/**
	 * Get health of enemy
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Draws enemy with healthbar
	 * @param g surface to be drawn on
	 */
	public void draw(PApplet g) {
		if(health>0) {
			super.draw(g);
			g.fill(50);
			g.rect((int)x-10, (int)y-20, 60, 5,3);
			if(health<30) g.fill(255,0,0);
			else if(health<60) g.fill(255,255,0);
			else g.fill(0,255,0);
			g.rect((int)x-10, (int)y-20, (int)(health*0.6), 5,3);
		}
		//g.text("Health: "+health, (int)x-10, (int)y-20);
	}
	
	/**
	 * Checks is enemy can be removed
	 * @return true is can be removed
	 */
	public boolean canRemove() {
		return health<=0;
	}
	
}
