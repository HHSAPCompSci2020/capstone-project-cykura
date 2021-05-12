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
	public static PImage heart;
	
	public static float Right_Margin = 400;
	public static float Left_Margin = 100;
	public static float Vertical_Margin = 40;
	public static float Horizontal_Margin = 100;
	public static boolean invertControls;
	public float view_x;
	public float view_y;
	
	private int x, y;
	private DrawingSurface surface;
//	private Rectangle screenRect;
//	private ArrayList<Heart> hearts;
	private Hero hero;
	public static ArrayList<Shape> platforms;
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

//		invertControls = true;
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
		heart = surface.loadImage("sprites\\FullHeart.png");
//		hearts = generateHearts();
		
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
		//surface.rotate(surface.radians(45));
		//surface.pushMatrix();
		
		surface.background(0, 255, 255);
		
		surface.stroke(0);     // Set line drawing color to white
		surface.noFill();

//		surface.rect(x,y,30,30);
//		
//		surface.fill(0);
//		surface.text("Move: Arrow keys",10,30);
//		surface.text("Menu: Space",10,50);
		
		surface.fill(100);
		for (Shape s : platforms) {
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle)s;
				surface.rect(r.x,r.y,r.width,r.height);
			}
		}
		
		if (hero.getHearts() > 0) {
			hero.draw(surface);
			ArrayList<Heart> h = new ArrayList<Heart>();
			for (int i = 0; i < hero.getHearts(); i++) {
				if (i == 0) {
					h.add(new Heart(GameScreen.heart, (int) (view_x + 20), (int) (view_y + 20)));
				} else {
					//h.add(new Heart(GameScreen.heart, 10, (int) (h.get(0).y + 20), 30, 30));
					h.add(new Heart(GameScreen.heart, (int) (h.get(0).x+40*i), (int) h.get(0).y));
				}
			}
			
			for (Heart he : h) {
				he.draw(surface);
			}
		}
		
		for (Enemy e: enemies) {
			if(e!=null)
			e.draw(surface);
		}
//		
		if(!invertControls) {
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
		}
		else {
			if (surface.isPressed(KeyEvent.VK_LEFT)) {
				hero.walk(1);
				hero.setFacingDirection(180);
			}
				
			if (surface.isPressed(KeyEvent.VK_RIGHT)) {
				hero.walk(-1);
				hero.setFacingDirection(0);
			}
		}
		
		if (!invertControls) {
			if (surface.isPressed(KeyEvent.VK_UP)) {
//				System.out.println("up");
				hero.jump();
			}
		} else {
			if (surface.isPressed(KeyEvent.VK_DOWN)) {
//				System.out.println("up");
				hero.jump();
			}
		}
		
		
		if(surface.isPressed(KeyEvent.VK_D)) {
			hero.charge();
			if (hero.getChargeTime() >= 80) {
				hero.dash();
			}
		}
		
		if(surface.isPressed(KeyEvent.VK_A)) {
			hero.throwFireball();
//			surface.removeKey(KeyEvent.VK_A);
		}
		
		
		if(surface.isPressed(KeyEvent.VK_SPACE)) {
			for (Enemy e: enemies) {
				hero.punch(e);
			}
//			surface.removeKey(KeyEvent.VK_SPACE);
		}
		
		if (hero.getHearts() > 0) {
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				if(e!=null) {
					if(e instanceof Boss) {
						Boss b = (Boss)e;
						b.act(hero);
					}
					else {
						e.act(hero, platforms);
					}
					if (e.canRemove()) {	// Enemies heath is less than zero
						enemies.set(i, null);
					}
				}
					if (e instanceof FireEnemy) {
						hero.act(platforms, (FireEnemy) e, ((FireEnemy) e).getFireballs());
					} else {
						hero.act(platforms, e, null);
					}
				
			}
		}
		
		//for (Enemy e: enemies) {
		//	e.act(hero, platforms);
		//}
		
		//surface.popMatrix();
		
		
	}
	
	private ArrayList<Shape> generatePlatforms(){
		ArrayList<Shape> p = new ArrayList<Shape>();
		p.add(new Rectangle(50,50,120,50));
		p.add(new Rectangle(100,150,120,50));
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
//		c.add(new FireEnemy(surface.loadImage("sprites\\StandingFireEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2-200, 50));	// Fire Enemy
//		c.add(new WaterEnemy(surface.loadImage("sprites\\StandingWaterEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2+160, 150));	// Water Enemy
		c.add(new GrassEnemy(surface.loadImage("sprites\\StandingGrassEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2+160, 150));	// Grass Enemy
//		c.add(new Boss(surface.loadImage("sprites\\StandingBossSprite.png"),DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2-100, 100));	// Boss
		//c.add(new Boss(surface.loadImage("sprites\\StandingFireEnemySprite.png"),280, 100));
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
		float h_b = view_y+DRAWING_HEIGHT-Horizontal_Margin;
		if(hero.y+hero.height>h_b) {
			view_y-=h_b-hero.y-hero.height;
		}
		
//		ArrayList<Heart> h = new ArrayList<Heart>();
//		for (int i = 0; i < hero.getHearts(); i++) {
//			if (i == 0) {
//				h.add(new Heart(GameScreen.heart, (int) (view_x + 50), (int) (view_y + 50)));
//			} else {
//				//h.add(new Heart(GameScreen.heart, 10, (int) (h.get(0).y + 20), 30, 30));
//				h.add(new Heart(GameScreen.heart, (int) (h.get(0).x+40*i), (int) h.get(0).y));
//			}
//		}
//		
//		for (Heart he : h) {
//			he.draw(surface);
//		}
		
		surface.translate(-view_x,-view_y);
	}
	
//	private ArrayList<Heart> generateHearts() {
//		ArrayList<Heart> hearts = new ArrayList<Heart>();
//		hearts.add(new Heart(surface.loadImage("sprites\\FullHeart.png"), DRAWING_WIDTH/2-Heart.HEART_WIDTH/2 - 200, 10));
//		hearts.add(new Heart(surface.loadImage("sprites\\FullHeart.png"), (int) (hearts.get(0).x + 35), (int) (hearts.get(0).y)));
//		
//		return hearts;
//	}
	
//	private void displayHearts(Hero h) {
//		
//	}
	

}

	
