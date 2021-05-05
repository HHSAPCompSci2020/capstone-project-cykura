import java.awt.Shape;
import java.util.ArrayList;

import processing.core.PImage;

public class Enemy extends MovingImage{
	public int health;

	public Enemy(PImage img, int x, int y, int w, int h) {
		super(img, x, y, w, h);
		
	}
	
	public void move(Hero hero, ArrayList<Shape> obstacles) {
		
	}

}
