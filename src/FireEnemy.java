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
	
	public void act(Hero hero, ArrayList<Shape> obstacles) {
		if(health>0) {
			double x1 = hero.x;
		    double y1 = hero.y;
	
		    double diffX = x1 - x;
		    double diffY = y1 - y;
	
		    float angle = (float)Math.atan2(diffY, diffX);
		    if(this.intersects(hero)) {
	//	    	 System.out.println("Collided with hero");
		   	     waitTime=45;
		    }
		    if(Math.random()>0.985) {
		    	Fireball f = new Fireball(GameScreen.fireball, (int)x, (int)y,20,20, v*Math.cos(angle)*2, v*Math.sin(angle)*2);
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
		     for(int i=0;i<fireballs.size();i++) {
		    	 Fireball f = fireballs.get(i);
		    	 if(f!=null) {
		    		 f.act();
		    		 if(f.checkCollision(this, hero, obstacles)) {
		    			 fireballs.set(i, null);
		    		 }
		    	 }
		     }
		}
		else {
			x=-20;
			y=-20;
		}
	}
	
	public ArrayList<Fireball> getFireballs() {
		return fireballs;
	}

	public void draw(PApplet g) {
		super.draw(g);
		for(Fireball f:fireballs) {
			if(f!=null) {
				f.draw(g);
			}
		}
	}
}
