import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Boss class represents the final enemy which the Hero can defeat with all of the same abilities as the Hero.
 * 
 * @version 5.6.21
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
	 * Changes state of boss
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
					invertControls();
					GameScreen.flipped = true;
				}
				else {
					GameScreen.invertControls = false;
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
				
				if(w!=null) {
					w.act();
					if(w.canRemove()) {
						w = null;
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

	private void invertControls() {
		GameScreen.invertControls = true;
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
	
	public WaterWave getWaterWave() {
		return w;
	}
	
	public ArrayList<Fireball> getFireballs() {
		return fireballs;
	}
}
