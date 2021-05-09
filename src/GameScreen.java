import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends Screen {

	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 500;
	public static PImage fireball;
	public static PImage water;
	public static float Right_Margin = 400;
	public static float Left_Margin = 60;
	public static float Vertical_Margin = 40;
	public float view_x;
	public float view_y;
	
	private int x, y;
	private DrawingSurface surface;
//	private Rectangle screenRect;
	private Hero hero;
	private ArrayList<Shape> platforms;
	private ArrayList<Enemy> enemies;
	

	/**
	 * Default Constructor
	 */
	public GameScreen(DrawingSurface surface) {
		super(800,500);
		this.surface = surface;
		
		x = 30;
		y = 30;
		platforms = generatePlatforms();

		
//		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
	}
	
	private void spawnHero() {
		hero = new Hero(surface.loadImage("sprites\\StandingHeroSprite.png"), DRAWING_WIDTH/2-Hero.HERO_WIDTH/2, 50);
		hero.setDash(true);
	}
	

	
	public void setup() {
		spawnHero();
		enemies = generateEnemies();
//		spawnEnemy();
		fireball = surface.loadImage("sprites\\FireballSprite.png");
		water = surface.loadImage("sprites\\FireballSprite.png");
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Draws everything and makes changes in the game
	 */
	public void draw() {
		
		scroll();
		//surface.pushMatrix();
		
		surface.background(0, 255, 255);
		
		surface.stroke(0);     // Set line drawing color to white
		surface.noFill();

		surface.rect(x,y,30,30);
		
		surface.fill(0);
		surface.text("Move: Arrow keys",10,30);
		surface.text("Menu: Space",10,50);
		
		surface.fill(100);
		for (Shape s : platforms) {
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle)s;
				surface.rect(r.x,r.y,r.width,r.height);
			}
		}
		
		if (hero.getHearts() > 0) {
			hero.draw(surface);
		}
		
		for (Enemy e: enemies) {
			e.draw(surface);
		}
//		
		if (surface.isPressed(KeyEvent.VK_LEFT)) {
//			System.out.println("l");
			hero.walk(-1);
			hero.setFacingDirection(180);
		}

		if (surface.isPressed(KeyEvent.VK_RIGHT)) {
//			System.out.println("r");
			hero.walk(1);
			hero.setFacingDirection(0);
		}
			
		if (surface.isPressed(KeyEvent.VK_UP)) {
//			System.out.println("up");
			hero.jump();
		}
		
		if(surface.isPressed(KeyEvent.VK_D)) {
			hero.dash();
		}
		
		if(surface.isPressed(KeyEvent.VK_SPACE)) {
			for (Enemy e: enemies) {
				hero.punch(e);
			}
		}
		
//		if (hero.getHearts() > 0) {
//			for (Enemy e: enemies) {
//				e.act(hero, platforms);
//				if (e instanceof FireEnemy) {
//					hero.act(platforms, (FireEnemy) e, ((FireEnemy) e).getFireballs());
//				} else {
//					hero.act(platforms, e, null);
//				}
//			}
//		}
		
//		for (Enemy e: enemies) {
//			e.act(hero, platforms);
//		}
		
		//surface.popMatrix();
		
		
	}
	
	private ArrayList<Shape> generatePlatforms(){
		ArrayList<Shape> p = new ArrayList<Shape>();
		p.add(new Rectangle(200,365,400,50));	//bottom middle
		p.add(new Rectangle(0,250,120,50)); 	// top left
		p.add(new Rectangle(680,250,120,50));	// top right
		p.add(new Rectangle(375,265,50,100));	// Vertical middle
		p.add(new Rectangle(300,250,200,50));	// top middle
		p.add(new Rectangle(980,250,120,50));
		p.add(new Rectangle(1280,250,120,50));
		return p;
	}
	
	private ArrayList<Enemy> generateEnemies() {
		ArrayList<Enemy> c = new ArrayList<Enemy>();
		//c.add(new Enemy(surface.loadImage("sprites\\StandingEnemySprite.png"), DRAWING_WIDTH/2-Enemy.ENEMY_WIDTH/2-200, 50));
		c.add(new FireEnemy(surface.loadImage("sprites\\StandingFireEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2-200, 50));	// Fire Enemy
		c.add(new WaterEnemy(surface.loadImage("sprites\\StandingFireEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2+160, 150));	// Water Enemy
		return c;
	}
	
	
	public void scroll() {
		float r_b = view_x+DRAWING_WIDTH-Right_Margin;
		if(hero.x+hero.width>r_b) {
			view_x+=hero.x+hero.width-r_b;
		}
		
		float l_b = view_x+Left_Margin;
		if(hero.x<l_b) {
			view_x-=l_b-hero.x;
		}
		
		float t_b = view_y+Vertical_Margin;
		if(hero.y<t_b) {
			view_y-=t_b-hero.y;
		}
		surface.translate(-view_x,-view_y);
	}
	

}

	
