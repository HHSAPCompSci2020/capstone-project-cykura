import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The WaterEnemy class represents an Enemy with the Waterfall projectile which the Player can defeat.
 * 
 * @author alex_zheng
 * @version 5.6.21
 */
public class WaterEnemy extends Enemy {
	private WaterWave waterwave;
	/**
	 * Creates a new instance of a WaterEnemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the WaterEnemy will look like in the game (sprite).
	 * @param x The X value of the WaterEnemy's top left corner.
	 * @param y The Y value of the WaterEnemy's top left corner.
	**/
	public WaterEnemy(PImage img, int x, int y) {
		super(img, x, y);
		waterwave=null;
	}
	
	public void act(Hero h, ArrayList<Shape> platforms) {
		if(waterwave==null) {
			
		}
		if(health>0) {
			double x1 = h.x;
		    double y1 = h.y;
	
		    double diffX = x1 - x;
		    double diffY = y1 - y;
	
		    float angle = (float)Math.atan2(diffY, diffX);
		    if(this.intersects(h)) {
		   	     waitTime=45;
		    }
		    if(Math.sqrt(Math.pow(diffX,2)+Math.pow(diffY, 2))<=150&&waterwave==null&&waitTime<=0) {
		    	waterwave = new WaterWave((int)(x+20),(int)(y+30),50,250,5);
		    	waitTime=180;
		    }
		     if(waitTime<=0) {
		    	x += v * Math.cos(angle);
		    	y += v * Math.sin(angle);
		    	 waitTime = 0;
		     }
		     else {
		    	 waitTime--;
		     }
		     if (waterwave!=null) {
		    	 waterwave.act();
		    	 if(waterwave.checkCollisionHero(h)) {
		    		 h.loseHearts(2);
		    		 waterwave.hit=true;
		    	 }
		    	 if (waterwave.canRemove()) waterwave=null;
		     }
		     
		}
		else {
			//remove this enemy and drop the ability
		}
	}
	
	public void draw(PApplet g) {
		super.draw(g);
		if(waterwave!=null) waterwave.draw(g);
	}
}
