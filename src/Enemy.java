import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PImage;

public class Enemy extends MovingImage{
	public int health;
	private int v;

	public Enemy(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		v = 2;
	}
	
	public void move(Hero hero, ArrayList<Shape> obstacles) {
		 double x1 = hero.x;
	     double y1 = hero.y;

	     double diffX = x1 - x;
	     double diffY = y1 - y;

	     float angle = (float)Math.atan2(diffY, diffX);

	     x += v * Math.cos(angle);
	     y += v * Math.sin(angle);
	}

}
