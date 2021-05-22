import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PImage;

/**
 * The GrassEnemy class represents an Enemy with the Leaf Dash move which the Player can defeat.
 * 
 * @author vicram_vijayakumar
 * @version 5.12.21
 */
public class GrassEnemy extends Enemy {
	private int dashCoolDownTimeRemaining;
	
	/**
	 * Creates a new instance of a GrassEnemy object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the GrassEnemy will look like in the game (sprite).
	 * @param x The X value of the GrassEnemy's top left corner.
	 * @param y The Y value of the GrassEnemy's top left corner.
	**/
	public GrassEnemy(PImage img, int x, int y) {
		super(img, x, y);
	}
	
	public void act(Hero hero, ArrayList<Shape> obstacles, ArrayList<Token> tokens) {
		if(health > 0) {
			if (Math.abs(x - spawnPoint.x) < 500 && Math.abs(y - spawnPoint.y) < 300 && Math.abs(spawnPoint.x - hero.x) < 500 && Math.abs(spawnPoint.y - hero.y) < 300) {	// If u are close to spawn

			     double diffX = hero.x - x;
			     double diffY = hero.y - y;
		
			     float angle = (float)Math.atan2(diffY, diffX);
			     if(this.intersects(hero)) {	// If it collides with the hero
			    	 waitTime=30;	// Wait Time to start Moving again
			    	 dashCoolDownTimeRemaining = 60;	// Increase Dash time to not dash right after hitting the hero
			     }
			     if (waitTime <= 0) {
			    	 x += v * Math.cos(angle);
			    	 y += v * Math.sin(angle);
			    	 waitTime = 0;
			     } else {
			    	 waitTime--;
			     }
			     
			     if (dashCoolDownTimeRemaining <= 0) {	// if u can dash
				     if (Math.abs(diffY) <= 300 && Math.abs(diffX) <= 500) {
					 		if(hero.x < this.x) {	// The Hero is to the left
								moveByAmount(-50, 0);
								dashCoolDownTimeRemaining = 60;
							} else if (hero.x > this.x) {		// Hero is to the right
								moveByAmount(50, 0);
								dashCoolDownTimeRemaining = 60;
							}
					  }
			     } else {	// if u still have a cool down time
			    	 dashCoolDownTimeRemaining--;
			     }
			     
			     if (hero.getFireballs() != null) {	// check if enemy got hit by fireball
				     for(int i=0;i<hero.getFireballs().size();i++) {
				    	 Fireball f = hero.getFireballs().get(i);
				    	 if(f!=null) {
				    		 if (f.checkCollisionEnemy(this)) {	// If the hero's fireball hits an enemy
				    			 this.loseHealth(10); 	// Enemy loses 10 hp
				    			 hero.getFireballs().set(i, null);
				    			 if(health<=0) {
				    				 tokens.add(new Token(GameScreen.grassToken,(int)x,(int)y));
				    			 }
			    			 }

				    	 }
				     }
			     }
			     
			     if (hero.getWaterWave() != null) {	// hero has done a water wave
			    	 if (hero.getWaterWave().checkCollisionEnemy(this)) {	// if the hero's water wave hits the enemy
			    		 hero.getWaterWave().hit = true;
			    		 this.loseHealth(20);
			    		 if(health<=0) {
		    				 tokens.add(new Token(GameScreen.grassToken,(int)x,(int)y));
		    			 }
			    	 }
			     }
			     
			} else {	// If away from spawn
				float angle = (float) Math.atan2(spawnPoint.y - y, spawnPoint.x - x);
				x += (v) * Math.cos(angle);
				y += (v) * Math.sin(angle);
			}
			
		} else {	// health < 0 so should die
			tokens.add(new Token(GameScreen.grassToken,(int)x,(int)y));
//			if(GameScreen.grassToken1==null)
//				GameScreen.grassToken1 = new Token(GameScreen.grassToken,(int)x,(int)y);
			
			x = -20;
			y =- 20;
		}
	}

}
