import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Timer;
import java.awt.event.KeyEvent;

import processing.core.PApplet;
import processing.core.PImage;

public class GameScreen extends Screen {
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 500;
	public static PImage fireball;
	public static PImage heart;
	
	public static PImage fireToken;
	public static PImage fireTokenUsed;
//	public static Token fireToken1;
	
	public static PImage waterToken;
	public static PImage waterTokenUsed;
//	public static Token waterToken1;
	
	public static PImage grassToken;
	public static PImage grassTokenUsed;
//	public static Token grassToken1;
	
	public static PImage fistToken;
	public static PImage fistTokenUsed;
//	public static Token fistToken1;
	
	public static PImage spike;
	

	
//	private PImage bg;
	
	public static float Right_Margin = 400;
	public static float Left_Margin = 100;
	public static float Vertical_Margin = 40;
	public static float Horizontal_Margin = 100;
	public static boolean invertControls;
	public static boolean flipped;
	public float view_x;
	public float view_y;
	
	private int x, y;
	private DrawingSurface surface;
//	private Rectangle screenRect;
//	private ArrayList<Heart> hearts;
	private Hero hero;
	public static ArrayList<Shape> platforms;
	private ArrayList<Enemy> enemies;
	private ArrayList<Token> tokens;
	private ArrayList<Spike> spikes;
	private long startTime;

	/**
	 * Default Constructor
	 */
	public GameScreen(DrawingSurface surface) {
		super(800,500);
		this.surface = surface;
		x = 30;
		y = 30;
		platforms = generatePlatforms();
		startTime = System.currentTimeMillis();
		
//		tokens = new ArrayList<Token>();
//		flipped =true;
//		invertControls = true;
//		screenRect = new Rectangle(0,0,DRAWING_WIDTH,DRAWING_HEIGHT);
	}
	
	/**
	 * Creates a new Hero
	 */
	private void spawnHero() {
		hero = new Hero(surface.loadImage("sprites\\StandingHeroSprite.png"), DRAWING_WIDTH/2-Hero.HERO_WIDTH/2, 50);
//		hero.setDash(true);
	}
	
	
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	public float getViewX() {
		return view_x;
	}
	
	public float getViewY() {
		return view_y;
	}

	
	public void setup() {
//		bg = surface.loadImage("sprites/gameScreenBackground.png");
//		System.out.println(bg.width);
		spawnHero();
		enemies = generateEnemies();
		
		fireball = surface.loadImage("sprites\\FireballSprite.png");
		heart = surface.loadImage("sprites\\FullHeart.png");
		
		fireToken = surface.loadImage("sprites\\tokens\\FireballTokenSprite.png");
		fireTokenUsed = surface.loadImage("sprites\\tokens\\FireballTokenSpriteUsed.png");

		
		waterToken = surface.loadImage("sprites\\tokens\\WaterWaveTokenSprite.png");
		waterTokenUsed = surface.loadImage("sprites\\tokens\\WaterWaveTokenSpriteUsed.png");
		
		grassToken = surface.loadImage("sprites\\tokens\\GrassTokenSprite.png");
		grassTokenUsed = surface.loadImage("sprites\\tokens\\GrassTokenSpriteUsed.png");

		
		fistToken = surface.loadImage("sprites\\tokens\\FistTokenSprite.png");
		fistTokenUsed = surface.loadImage("sprites\\tokens\\FistTokenSpriteUsed.png");
		
		spike = surface.loadImage("sprites\\SpikeSprite.png");
		spikes = generateSpikes();
		tokens = generateTokens();

		
		
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
//		System.out.println("vx " + view_x + " vy " + view_y);
//		bg.resize(DRAWING_WIDTH, DRAWING_HEIGHT);
//		surface.background(bg);
		scroll();
		if(flipped) {
			surface.translate(800,500);
			surface.rotate(surface.radians(180));
		}
		//surface.pushMatrix();
		
		surface.background(0, 255, 255);
		
		surface.stroke(0);     // Set line drawing color to white
		surface.noFill();
		surface.fill(100);
		
		for (Shape s : platforms) {	// Draw rectangle platforms
			if (s instanceof Rectangle) {
				Rectangle r = (Rectangle)s;
				surface.rect(r.x,r.y,r.width,r.height);
			}
		}
		
		surface.fill(205, 133, 63);
		surface.rect(view_x + 630, view_y, 170, 40);		// Brown Rectangle
		
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
		
		if (tokens.size() != 0) {
			if (hero.getPunchCoolDown() <= 0) {	// can punch since there is no cooldown
				tokens.set(0, new Token(fistToken, (int) (view_x + 640), (int) (view_y + 5)));
			} else {	// can't punch since there is a cooldown
				tokens.set(0, new Token(fistTokenUsed, (int) (view_x + 640), (int) (view_y + 5)));
//				tokens.add(new Token(fistTokenUsed, (int) (view_x + 300), (int) view_y + 10));
			}
			
			tokens.get(0).draw(surface);
			
			for (int i = 1; i <tokens.size(); i ++) {	// goes thru other tokens
				if (tokens.get(i) != null) { 
					if (tokens.get(i).getImage() == fireToken || tokens.get(i).getImage() == fireTokenUsed) {
						if (hero.canThrowFireball()) {	// if the hero has already touched the token
							if (hero.getFireballCoolDown() <= 0) {
								tokens.set(i, new Token(fireToken, (int) (view_x + 680), (int) (view_y + 5)));
							} else {
								tokens.set(i , new Token(fireTokenUsed, (int) (view_x + 680), (int) (view_y + 5)));
							}
						}
					} else if (tokens.get(i).getImage() == waterToken || tokens.get(i).getImage() == waterTokenUsed) {
						if (hero.canWaterWave()) {
							if (hero.getWaterWaveCoolDown() <= 0) {
								tokens.set(i, new Token(waterToken, (int) (view_x + 720), (int) (view_y + 5)));
							} else {
								tokens.set(i , new Token(waterTokenUsed, (int) (view_x + 720), (int) (view_y + 5)));
							}
						}
					} else if (tokens.get(i).getImage() == grassToken || tokens.get(i).getImage() == grassTokenUsed) {
						if (hero.canDash()) {
							if (hero.isDashing() == false) {	// if the hero is not dashing
								tokens.set(i, new Token(grassToken, (int) (view_x + 760), (int) (view_y + 5)));
							} else {
								tokens.set(i , new Token(grassTokenUsed, (int) (view_x + 760), (int) (view_y + 5)));
							}
						}
					}
					
					
					tokens.get(i).draw(surface);
				}
			}
			
			
		}
		
		for (Spike s: spikes) {
			s.draw(surface);
		}


		
		for (Enemy e: enemies) {
			if(e!=null)
			e.draw(surface);
		}
		
		surface.stroke(255);
		surface.text((int)((System.currentTimeMillis()-startTime)/1000), view_x+500, view_y+50);
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
//			if (hero.getChargeTime() >= 80) {
//				hero.dash();
//			}
		} else {
			hero.dash();
		}
		
		if(surface.isPressed(KeyEvent.VK_A)) {
			hero.throwFireball();
//			surface.removeKey(KeyEvent.VK_A);
		}
		
		if(surface.isPressed(KeyEvent.VK_S)) {
			hero.doWaterWave();
//			surface.removeKey(KeyEvent.VK_A);
		}
		
		
		if(surface.isPressed(KeyEvent.VK_SPACE)) {
			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i) != null) {
//					System.out.println(enemies.get(i));
					if (Math.abs(hero.getCenterX() - enemies.get(i).getCenterX()) < 150) {
						hero.punch(enemies.get(i));
					}
				}
			}
			
//			System.out.println();
//			for (Enemy e: enemies) {
//				if (e!= null) {
//					System.out.println(e);
//					hero.punch(e);
//				}
//			}
//			surface.removeKey(KeyEvent.VK_SPACE);
		}
		
		if (hero.getHearts() > 0) {
			for (int i = 0; i < enemies.size(); i++) {
				Enemy e = enemies.get(i);
				if(e!=null) {
					if(e instanceof Boss) {
						Boss b = (Boss)e;
						b.act(hero);
					} else {
						e.act(hero, platforms, tokens);
					}
					if (e.canRemove()) {	// Enemies heath is less than zero
						enemies.set(i, null);
					}
				}
				
			}
			for (int i = 0; i < spikes.size(); i++) {
				Spike s = spikes.get(i);
				if (s != null) {
					s.act(enemies, hero);
				}
			}
			hero.act(platforms, enemies, getTokens());
		}
		
		
	}
	
	private ArrayList<Shape> generatePlatforms(){
		ArrayList<Shape> p = new ArrayList<Shape>();
		p.add(new Rectangle(0,450,6000,500));	//bottom 
		
		p.add(new Rectangle(0,-300,120,1200)); 	// top left
		p.add(new Rectangle(300,345,200,50));	// top middle
		//Fire enemy
		p.add(new Rectangle(700,345,250,50));
		p.add(new Rectangle(1050,345,250,50));		
		p.add(new Rectangle(925,130,150,50));
		p.add(new Rectangle(1200,-300,50,585));
		p.add(new Rectangle(750,-300,50,585));
		p.add(new Rectangle(1125, 235,75,50));
		p.add(new Rectangle(800, 235,75,50));
		//Water enemy
		p.add(new Rectangle(1500,345,250,50));
		p.add(new Rectangle(1850,345,250,50));		
		p.add(new Rectangle(1725,130,150,50));
		p.add(new Rectangle(2000,-300,50,585));
		p.add(new Rectangle(1550,-300,50,585));
		p.add(new Rectangle(1925, 235,75,50));
		p.add(new Rectangle(1600, 235,75,50));
		//Grass Enemy
		p.add(new Rectangle(2300,345,250,50));
		p.add(new Rectangle(2650,345,250,50));		
		p.add(new Rectangle(2525,130,150,50));
		p.add(new Rectangle(2800,-300,50,585));
		p.add(new Rectangle(2350,-300,50,585));
		p.add(new Rectangle(2725, 235,75,50));
		p.add(new Rectangle(2400, 235,75,50));
		//Final Boss
		p.add(new Rectangle(3100,345,800,50));	
		p.add(new Rectangle(3450,245,100,100));
		p.add(new Rectangle(3150,145,200,50));
		p.add(new Rectangle(3650,140,200,50));
		
		return p;
	}
	
	private ArrayList<Enemy> generateEnemies() {
		ArrayList<Enemy> c = new ArrayList<Enemy>();
		c.add(new Enemy(surface.loadImage("sprites\\StandingEnemySprite.png"), 280, 50));
		c.add(new FireEnemy(surface.loadImage("sprites\\StandingFireEnemySprite.png"), 980, 50));	// Fire Enemy
		c.add(new WaterEnemy(surface.loadImage("sprites\\StandingWaterEnemySprite.png"), 1780, 50));	// Water Enemy
		c.add(new GrassEnemy(surface.loadImage("sprites\\StandingGrassEnemySprite.png"), 2580, 50));	// Grass Enemy
		c.add(new Boss(surface.loadImage("sprites\\StandingBossSprite.png"),3480, 40));	// Boss
		//c.add(new Boss(surface.loadImage("sprites\\StandingFireEnemySprite.png"),280, 100));
		return c;
	}
	
	private ArrayList<Token> generateTokens() {
		ArrayList<Token> t = new ArrayList<Token>();
//		System.out.println(fistToken);
//		System.out.println(view_x);
//		System.out.println(view_y);
		t.add(new Token(surface.loadImage("sprites\\tokens\\FistTokenSprite.png"), (int) (300), (int) 10));
		
		return t;
	}
	
	private ArrayList<Spike> generateSpikes() {
		ArrayList<Spike> s = new ArrayList<Spike>();
		int x = 90;
		for (int i = 0; i < 100; i++) {
			s.add(new Spike(spike, x+35, 420, 30, 30));
			x+=30;
		}
		return s;
	}
	
	/**
	 * Shifts the screen based on players location
	 */
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
		if(flipped) {
			surface.translate(view_x,-view_y);
		}
		else {
			surface.translate(-view_x,-view_y);
		}
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

	
