import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * The surface that draws and runs the game
 * @author Animan
 *
 */
public class DrawingSurface extends PApplet{
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 500;
	public static PImage fireball;
	public static PImage water;
	public static float Right_Margin = 400;
	public static float Left_Margin = 60;
	public static float Vertical_Margin = 40;
	public float view_x;
	public float view_y;
	
	private Rectangle screenRect;
	private Hero hero;
	private ArrayList<Shape> platforms;
//	private ArrayList<Projectile> projectiles;
//	private Enemy e1;
	private FireEnemy fe;
	private WaterEnemy we;
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
		we = new WaterEnemy(loadImage("sprites\\StandingFireEnemySprite.png"), DRAWING_WIDTH/2-FireEnemy.ENEMY_WIDTH/2+160, 150);
	}
	
	public void setup() {
		spawnHero();
		spawnEnemy();
		fireball = loadImage("sprites\\FireballSprite.png");
		water = loadImage("sprites\\FireballSprite.png");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Draws everything and makes changes in the game
	 */
	public void draw() {
		background(0,255,255);   
		scroll();
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
		
		
		fe.draw(this);
		we.draw(this);	


		
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
		fe.act(hero, platforms);
		we.act(hero, platforms);
		
		//if (!screenRect.intersects(hero))
			//spawnHero();

		
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
		translate(-view_x,-view_y);
	}

}
