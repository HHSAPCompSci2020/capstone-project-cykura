import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PApplet;


/**
 * The FireEnemy class represents an Enemy with the Fireball projectile which the Player can defeat.
 * 
 * @author animan_patil
 * @version 5.6.21
 */
public class FireEnemy extends Enemy{
	private ArrayList<Fireball> fireballs;
	
	/**
	 * Creates a new instance of a FireEnemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the FireEnemy will look like in the game (sprite).
	 * @param x The X value of the FireEnemy's top left corner.
	 * @param y The Y value of the FireEnemy's top left corner.
	**/
	public FireEnemy(PImage img, int x, int y) {
		super(img, x, y);
		fireballs = new ArrayList<Fireball>();
	}
	
	/**
	 * Changes state of fireenemy in game
	 * @param hero the player
	 * @param obstacles all platforms in the game
	 */
	public void act(Hero hero, ArrayList<Shape> obstacles) {
		if(health>0) {
			if(Math.abs(x-spawnPoint.x)<500&&Math.abs(y-spawnPoint.y)<300) {
				double x1 = hero.x;
			    double y1 = hero.y;
		
			    double diffX = x1 - x;
			    double diffY = y1 - y;
		
			    float angle = (float)Math.atan2(diffY, diffX);
			    if(this.intersects(hero)) {
		//	    	 System.out.println("Collided with hero");
			   	     waitTime=45;
			    }
			    if(Math.random()>0.995) {
			    	Fireball f = new Fireball(GameScreen.fireball, (int)x, (int)y,20,20, v*Math.cos(angle)*3, v*Math.sin(angle)*3);
			    	fireballs.add(f);
			    }
			     if(waitTime<=0) {
			    	x += v * Math.cos(angle);
			    	y += v * Math.sin(angle);
			    	 waitTime = 0;
			     }
			     else {
			    	 waitTime--;
			     }
			}
			else {
				float angle = (float)Math.atan2(spawnPoint.y-y, spawnPoint.x-x);
		    	 x += (v) * Math.cos(angle);
		    	 y += (v) * Math.sin(angle);
			}
		     for(int i=0;i<fireballs.size();i++) {
		    	 Fireball f = fireballs.get(i);
		    	 if(f!=null) {
		    		 f.act();
		    		 if (f.checkCollisionHero(hero)) {
		    			 hero.loseHearts(1);
		    			 fireballs.set(i, null);
		    		 }
		    		 if(f.checkCollisionShape(obstacles)) {
		    			 fireballs.set(i, null);
		    		 }
		    	 }
		     }
		}
		else {
			if(token==null)
			token = new Token(GameScreen.fireToken,(int)x,(int)y);
			System.out.println(token);
			x=-20;
			y=-20;
		}
	}
	
	/**
	 * Return the fireballs shot by this enemy
	 * @return
	 */
	public ArrayList<Fireball> getFireballs() {
		return fireballs;
	}

	/**
	 * Draw fireenemy
	 * @param g surface to be drawn on
	 */
	public void draw(PApplet g) {
		super.draw(g);
		for(Fireball f:fireballs) {
			if(f!=null) {
				f.draw(g);
			}
		}
		System.out.println(token);
		if(token!=null) {
			token.draw(g);
		}
	}
}
