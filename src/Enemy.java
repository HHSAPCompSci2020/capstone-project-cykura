import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PImage;

public class Enemy extends MovingImage{
	public int health;
	private int v;

	public Enemy(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		v=2;
		
	}
	
	public void move(Hero hero, ArrayList<Shape> obstacles) {
		 int MoveToX = hero.getX();
	     int MoveToY = hero.getY();

	     int diffX = MoveToX - x;
	     int diffY = MoveToY - y;

	     float angle = (float)Math.atan2(diffY, diffX);

	     x += v * Math.cos(angle);
	     y += v * Math.sin(angle);
	}

}
