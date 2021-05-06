import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends MovingImage{
	
	public static final int ENEMY_WIDTH = 40;
	public static final int ENEMY_HEIGHT = 60;
	public int health;
	private int v;
	private int waitTime;

	public Enemy(PImage img, int x, int y) {
		super(img, x, y, 40, 60);
		v = 2;
		health = 100;
	}
	
	public void act(Hero hero, ArrayList<Shape> obstacles) {
		 double x1 = hero.x;
	     double y1 = hero.y;

	     double diffX = x1 - x;
	     double diffY = y1 - y;

	     float angle = (float)Math.atan2(diffY, diffX);
	     if(this.intersects(hero)) {
	    	 System.out.println("Collided with hero");
	    	 waitTime=45;
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
	
	public void draw(PApplet g) {
		super.draw(g);
		g.rect((int)x-10, (int)y-20, 60, 5,3);
		if(health<30) g.fill(255,0,0);
		else if(health<60) g.fill(255,255,0);
		else g.fill(0,255,0);
		g.rect((int)x-10, (int)y-20, (int)(health*0.6), 5,3);
		//g.text("Health: "+health, (int)x-10, (int)y-20);
	}
}
