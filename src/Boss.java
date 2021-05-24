import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Boss class represents the final enemy which the Hero can defeat with all of the same abilities as the Hero.
 * 
 * @author animan_patil
 * @version 5.23.21
 */
public class Boss extends Enemy {
	private int cnt;
	public ArrayList<Fireball> fireballs;
	public WaterWave w;
	//Cooldown time between shooting fireballs and using a waterwave
	private int cooldown;
	private int rotation;
	private int fireballCnt;
	private int waterCnt;
	
	/**
	 * Creates a new instance of a Boss object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the Boss will look like in the game (sprite).
	 * @param x The X value of the Boss' top left corner.
	 * @param y The Y value of the Boss' top left corner.
	**/
	public Boss(PImage img, int x, int y) {
		super(img, x, y);
		fireballs = new ArrayList<Fireball>();
		health = 200;
	}
	
	/**
	 * Makes the Boss act by shooting fireballs, dashing, 
	 * and making a water wave to deal damage to hero.
	 * Flips screen if below half health.
	 * 
	 * @param hero The player
	 */
	public void act(Hero hero) {
		if(health>0) {
			if(Math.abs(x-spawnPoint.x)<500&&Math.abs(y-spawnPoint.y)<300&&Math.abs(x-hero.x)<500&&Math.abs(y-hero.y)<300) {
				cnt++;
				if(rotation==1) {
					if(Math.random()>0.5) {
						x+=100;
						spawnPoint.setLocation(spawnPoint.x+100, spawnPoint.y);
					}
					else {
						x-=100;
						spawnPoint.setLocation(spawnPoint.x-100, spawnPoint.y);
					}
					rotation = 0;
					waterCnt=0;
					fireballCnt = 0;
				}
				//System.out.println(cnt);
				double x1 = hero.x;
			    double y1 = hero.y;
		
			    double diffX = x1 - x;
			    double diffY = y1 - y;
			    
			    float angle = (float)Math.atan2(diffY, diffX);
			    //Inverting Screen
				if((int)((cnt/300.0)%2)==1&&health<=125) {
					GameScreen.flipped = true;
				}
				else {
					GameScreen.flipped = false;
				}
				if(rotation==0) {
					//Shooting Fireballs
					if(Math.random()>0.99&& w==null) {
						Fireball f = new Fireball(GameScreen.fireball, (int)x, (int)y,20,20, v*Math.cos(angle)*2, v*Math.sin(angle)*2);
						fireballs.add(f);
						cooldown=60;
						fireballCnt++;
					}
					if(cooldown>0) {
						cooldown--;
					}
					//Use WaterWave
					if(Math.random()>0.98&&cooldown<=0&&w==null&&waterCnt<=2) {
						w = new WaterWave((int)(x+20),(int)(y+30),50,250,5);
						waterCnt++;
						//System.out.println(x+" "+y);
					}
					if(fireballCnt>=5&&waterCnt>=2) {
						rotation++;
					}
				}
				//Removing fireballs if colliding with platforms
				for(int i=0;i<fireballs.size();i++) {
			    	 Fireball f = fireballs.get(i);
			    	 if(f!=null) {
			    		 f.act();
			    		 if(f.checkCollisionShape(GameScreen.platforms)) {
			    			 fireballs.set(i, null);
			    		 }
			    	 }
			     }
				
				if(w!=null) {	// Increases the water wave radius
					w.act();
					if(w.canRemove()) {
						w = null;
					}
				}
				
			     if (hero.getFireballs() != null) {	// check if enemy got hit by fireball
				     for(int i=0;i<hero.getFireballs().size();i++) {
				    	 Fireball f = hero.getFireballs().get(i);
				    	 if(f!=null) {
				    		 if (f.checkCollisionEnemy(this)) {	// If the hero's fireball hits an enemy
				    			 this.loseHealth(10); 	// Enemy loses 10 hp
				    			 hero.getFireballs().set(i, null);
			    			 }
				    		 if(health<=0) {
				    			 GameScreen.surface.switchScreen(ScreenSwitcher.SCREEN4);
			    			 }

				    	 }
				     }
			     }
			     
			     if (hero.getWaterWave() != null) {	// hero has done a water wave
			    	 if (hero.getWaterWave().checkCollisionEnemy(this)) {	// if the hero's water wave hits the enemy
			    		 hero.getWaterWave().hit = true;
			    		 this.loseHealth(35);
			    		 if(health<=0) {
			    			 GameScreen.surface.switchScreen(ScreenSwitcher.SCREEN4);
		    			 }
			    	 }
			     }
			}
			else {
				float angle = (float)Math.atan2(spawnPoint.y-y, spawnPoint.x-x);
		    	 x += (v) * Math.cos(angle);
		    	 y += (v) * Math.sin(angle);
		    	 for(int i=0;i<fireballs.size();i++) {
			    	 Fireball f = fireballs.get(i);
			    	 if(f!=null) {
			    		 f.act();
			    		 if(f.checkCollisionShape(GameScreen.platforms)) {
			    			 fireballs.set(i, null);
			    		 }
			    	 }
			     }
			}
		}
		else {
			GameScreen.surface.switchScreen(ScreenSwitcher.SCREEN4);
		}
			
		
		/*if(rotateCooldown>0)
		rotateCooldown--;
		
		if(rotateCooldown2>0)
		rotateCooldown2--;*/
		
	}

	
	/**
	 * Draw the boss
	 * @param g surface to be drawn on
	 */
	public void draw(PApplet g) {
		super.draw(g);
		for(Fireball f:fireballs) {
			if(f!=null) {
				f.draw(g);
			}
		}
		if(w!=null) {
			//System.out.println("drawing water");
			w.draw(g);
		}
		if(Math.random()>0.9) {
			//System.out.println("rotate");
			//g.rotate(g.PI);
		}
			
	}
	
	/**
	 * Returns the waterwave
	 * @return waterwave
	 */
	public WaterWave getWaterWave() {
		return w;
	}
	
	/**
	 * Returns the fireballs
	 * @return fireballs
	 */
	public ArrayList<Fireball> getFireballs() {
		return fireballs;
	}
	
	protected void healthBar(PApplet g) {
		g.fill(50);
		g.rect((int)x-10, (int)y-20, 60, 5,3);
		if(health<60) g.fill(255,0,0);
		else if(health<120) g.fill(255,255,0);
		else g.fill(0,255,0);
		g.rect((int)x-10, (int)y-20, (int)(health*0.3), 5,3);
	}
}
