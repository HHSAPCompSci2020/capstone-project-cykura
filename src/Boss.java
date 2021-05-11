import java.util.ArrayList;

import processing.core.PImage;

/**
 * The Boss class represents the final enemy which the Hero can defeat with all of the same abilities as the Hero.
 * 
 * @version 5.6.21
 */
public class Boss extends Enemy {
	private int invertTime;
	public ArrayList<Fireball> fireballs;
	public WaterWave w;
	private int cooldown;
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
	}
	
	public void act(Hero hero) {
		double x1 = hero.x;
	    double y1 = hero.y;

	    double diffX = x1 - x;
	    double diffY = y1 - y;
	    
	    float angle = (float)Math.atan2(diffY, diffX);
	    //Inverting Screen
		if(Math.random()>0.993&&GameScreen.invertControls==false) {
			invertControls((int)Math.random()*5000+3000);
		}
		if(invertTime>0) {
			invertTime--;
		}
		else {
			invertTime = 0;
			GameScreen.invertControls = false;
		}
		//Shooting Fireballs
		if(Math.random()>0.99&& w==null) {
			Fireball f = new Fireball(GameScreen.fireball, (int)x, (int)y,20,20, v*Math.cos(angle)*2, v*Math.sin(angle)*2);
			fireballs.add(f);
			cooldown=60;
		}
		if(cooldown>0) {
			cooldown--;
		}
		//Use WaterWave
		if(Math.random()>0.99&&cooldown<=0) {
			w = new WaterWave((int)x,(int)y,100,100,5);
		}
		
	}

	private void invertControls(int duration) {
		GameScreen.invertControls = true;
		invertTime = duration;
	}
}
