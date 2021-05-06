import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
/**
 * The surface that draws and runs the game
 * @author Animan
 *
 */
public class DrawingSurface extends PApplet{
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 500;
	private Rectangle screenRect;
	private Hero hero;
	private ArrayList<Shape> platforms;
//	private ArrayList<Projectile> projectiles;
//	private Enemy e1;
	private FireEnemy fe;
	private ArrayList<Integer> keys;
	

	/**
	 * Default Constructor
	 */
	public DrawingSurface() {
		super();
		keys = new ArrayList<Integer>();
		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
		platforms = generatePlatforms();
//		projectiles = new ArrayList<Projectile>();
	}
	
	private void spawnHero() {
		hero = new Hero(loadImage("sprites\\StandingHeroSprite.png"), DRAWING_WIDTH/2-Hero.HERO_WIDTH/2, 50);
		hero.setDash(true);
	}
	
	private void spawnEnemy() {
//		e1 = new Enemy(loadImage("sprites\\StandingEnemySprite.png"), DRAWING_WIDTH/2-Enemy.ENEMY_WIDTH/2-200, 50);
		fe = new FireEnemy(loadImage("sprites\\StandingFireEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2-200, 50);
	}
	
	public void setup() {
		spawnHero();
		spawnEnemy();
	}
	
	/**
	 * Draws everything and makes changes in the game
	 */
	public void draw() {
		background(0,255,255);   
		
		pushMatrix();
		
		int width = this.width;
		int height = this.height;
		
		float ratioX = (float)width/DRAWING_WIDTH;
		float ratioY = (float)height/DRAWING_HEIGHT;
		
		scale(ratioX, ratioY);
		
		fill(100);
		for (Shape s : platforms) {
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle)s;
				rect(r.x,r.y,r.width,r.height);
			}
		}
		
		if (hero.getHearts() > 0) {
			hero.draw(this);
		}
//		e1.draw(this);
		
		if (fe.getHealth() > 0) {
			fe.draw(this);
		}

		
		popMatrix();
		
		if (isPressed(KeyEvent.VK_LEFT)) {
//			System.out.println("l");
			hero.walk(-1);
			hero.setFacingDirection(180);
		}
		if (isPressed(KeyEvent.VK_RIGHT)) {
			//System.out.println("r");
			hero.walk(1);
			hero.setFacingDirection(0);
		}
			
		if (isPressed(KeyEvent.VK_UP)) {
//			System.out.println("up");
			hero.jump();
		}
		
		if(isPressed(KeyEvent.VK_D)) {
			hero.dash();
		}
		
		if(isPressed(KeyEvent.VK_SPACE)) {
//			hero.punch(e1);
			hero.punch(fe);

		}
		
		if (hero.getHearts() > 0) {
			hero.act(platforms, fe, fe.getFireballs());
//			hero.act(platforms, e1, e1.getProjectiles());
		}
		
//		e1.act(hero,platforms);
		if (fe.getHealth() > 0) {
			fe.act(hero, platforms);
		}
		
		if (!screenRect.intersects(hero))
			spawnHero();

		
	}
	
	private ArrayList<Shape> generatePlatforms(){
		ArrayList<Shape> p = new ArrayList<Shape>();
		p.add(new Rectangle(200,365,400,50));	//bottom middle
		p.add(new Rectangle(0,250,120,50)); 	// top left
		p.add(new Rectangle(680,250,120,50));	// top right
		p.add(new Rectangle(375,265,50,100));	// Vertical middle
		p.add(new Rectangle(300,250,200,50));	// top middle
		return p;
	}
	
	public void keyPressed() {
		//System.out.println("keyPressed");
		//
		keys.add(keyCode);
	}

	public void keyReleased() {
		//System.out.println("keyReleased");
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	

}
