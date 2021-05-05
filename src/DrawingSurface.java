import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

public class DrawingSurface extends PApplet{
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 500;
	private Rectangle screenRect;
	private Hero hero;
	private ArrayList<Shape> platforms;
	private Enemy e1;
	private ArrayList<Integer> keys;
	//private World world;
	
	public void setup() {
		spawnHero();
		e1 = new Enemy(loadImage("sprites\\StandingHeroSprite.png"), DRAWING_WIDTH/2-Hero.HERO_WIDTH/2-200, 50);
	}
	
	public DrawingSurface() {
		super();
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		keys = new ArrayList<Integer>();
		platforms = generatePlatforms();
		//e1 = new Enemy(null, 0, 0, 0, 0);
		//world = new World();
	}
	
	public void draw() {
		background(0,255,255);   
		pushMatrix();
		/*int width = getWidth();
		int height = getHeight();
		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;
		scale(ratioX, ratioY);*/
		fill(100);
		for (Shape s : platforms) {
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle)s;
				rect(r.x,r.y,r.width,r.height);
			}
		}
		hero.draw(this);
		e1.draw(this);
		popMatrix();
		
		if (isPressed(KeyEvent.VK_LEFT)) {
			hero.walk(-1);
		}
		if (isPressed(KeyEvent.VK_RIGHT))
			hero.walk(1);
		if (isPressed(KeyEvent.VK_UP))
			hero.jump();
		
		hero.act(platforms);
		e1.act(hero,platforms);
		
		if (!screenRect.intersects(hero))
			spawnHero();

//		e1.act(hero, platforms);
		//world.draw(this);
	}
	
	private ArrayList<Shape> generatePlatforms(){
		ArrayList<Shape> p = new ArrayList<Shape>();
		p.add(new Rectangle(200,400,400,50));
		p.add(new Rectangle(0,250,100,50));
		p.add(new Rectangle(700,250,100,50));
		p.add(new Rectangle(375,300,50,100));
		p.add(new Rectangle(300,250,200,50));
		return p;
	}
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	private void spawnHero() {
		hero = new Hero(loadImage("sprites\\StandingHeroSprite.png"), DRAWING_WIDTH/2-Hero.HERO_WIDTH/2, 50);
	}
}
