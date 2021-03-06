import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.Timer;
import java.awt.event.KeyEvent;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * The GameScreen represents the game screen and has all of the Enemies, Platforms, Checkpoints, Spikes and Hero.
 * 
 * @author animan_patil
 * @version 5.23.21
 *
 */
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
	public static PImage movingPlatform;
	
//	public static PImage checkPoint;

	
//	private PImage bg;
	
	public static float Right_Margin = 400;
	public static float Left_Margin = 100;
	public static float Vertical_Margin = 40;
	public static float Horizontal_Margin = 100;
//	public static boolean invertControls;
	public static boolean flipped;
	public float view_x;
	public float view_y;
	
	private int x, y;
	public static DrawingSurface surface;
//	private Rectangle screenRect;
//	private ArrayList<Heart> hearts;
	private Hero hero;
	public static ArrayList<Shape> platforms;
	private ArrayList<Enemy> enemies;
	private ArrayList<Token> tokens;
	private ArrayList<Checkpoint> checkpoints;
	private ArrayList<Spike> spikes;
	private ArrayList<MovingPlatform> movingPlatforms;
	private long startTime;
	public static long currTime;
	private PFont f;
	/**
	 * Creates the GameScreen
	 * 
	 * @param surface The DrawingSurface to draw the screen on.
	 */
	public GameScreen(DrawingSurface surface) {
		super(800,500);
		GameScreen.surface = surface;
		x = 30;
		y = 30;
		platforms = generatePlatforms();
		
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
//		hero.canThrowFireball();
//		hero.setDash(true);
	}
	
	/**
	 * 
	 * @return the Tokens in the Screen
	 */
	public ArrayList<Token> getTokens() {
		return tokens;
	}
	
	/**
	 * 
	 * @return the X value of the viewable screen
	 */
	public float getViewX() {
		return view_x;
	}
	
	/**
	 * 
	 * @return the Y value of the viewable screen
	 */
	public float getViewY() {
		return view_y;
	}

	/**
	 * Generates the Hero, enemies, tokens, platforms, and checkpoints
	 */
	public void setup() {
//		bg = surface.loadImage("sprites/gameScreenBackground.png");
//		System.out.println(bg.width);
		spawnHero();
		f = surface.createFont("Arial", 16,true);
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
		movingPlatform = surface.loadImage("sprites\\PlatformSprite.png");
		spikes = generateSpikes();
		
		movingPlatform = surface.loadImage("sprites\\PlatformSprite.png");
		movingPlatforms=generateMovingPlatforms();
		tokens = generateTokens();
		checkpoints = generateCheckpoints();

		
		
	}
	
	/**
	 * Changes the Hero's image. If the hero is invincible, it will revert back to the original.
	 * If it is not invincible, it will change the image to invincible.
	 * @param h The Hero which can be played.
	 */
	public void changeHeroImage(Hero h) {
		if (h.isInvincible()) {	// Hero is invincibile
			h.setImage(surface.loadImage("sprites\\StandingHeroSpriteInvincible.png"));
		} else {
			h.setImage(surface.loadImage("sprites\\StandingHeroSprite.png"));
		}
		 
	}
		
	
	/**
	 * Draws everything and makes changes in the game
	 */
	public void draw() {
		if(currTime==0) {
			startTime = System.currentTimeMillis();
			currTime = startTime;
		}
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
		
		for (Checkpoint c: checkpoints) {
			c.draw(surface);
		}
		
		if (hero.getHearts() > 0) {
			changeHeroImage(hero);
			hero.draw(surface);
			ArrayList<Heart> h = new ArrayList<Heart>();
			if (!flipped) {
//				System.out.println("!flipped");
//				System.out.println("View X " + view_x);
//				System.out.println("View Y " + view_y);
//				System.out.println();
				for (int i = 0; i < hero.getHearts(); i++) {
					if (i == 0) {
						h.add(new Heart(GameScreen.heart, (int) (view_x + 20), (int) (view_y + 20)));
//						System.out.println("H x " + h.get(0).getX());
//						System.out.println("H y " + h.get(0).getY());
					} else {
						//h.add(new Heart(GameScreen.heart, 10, (int) (h.get(0).y + 20), 30, 30));
						h.add(new Heart(GameScreen.heart, (int) (h.get(0).x+40*i), (int) h.get(0).y));
					}
				}
			} else {
//				System.out.println("flipped");
//				System.out.println("View X " + view_x);
//				System.out.println("View Y " + view_y);
//				h.add(new Heart(GameScreen.heart, (int) (view_x) + 700, (int) (view_y + 400)));
//				System.out.println();
//				surface.translate(-800,-500);
//				surface.rotate(surface.radians(-180));
				for (int i = 0; i < hero.getHearts(); i++) {
					if (i == 0) {
						h.add(new Heart(GameScreen.heart, (int) (view_x + 20), (int) (view_y + 20)));
//						System.out.println("H x " + h.get(0).getX());
//						System.out.println("H y " + h.get(0).getY());
					} else {
						//h.add(new Heart(GameScreen.heart, 10, (int) (h.get(0).y + 20), 30, 30));
						h.add(new Heart(GameScreen.heart, (int) (h.get(0).x+40*i), (int) h.get(0).y));
					}
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
		for (MovingPlatform m:movingPlatforms) {
			m.draw(surface);
		}

		
		for (Enemy e: enemies) {
			if(e!=null)
			e.draw(surface);
		}
		

		
//		
//		if(!flipped) {
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
//		}
//		else {
//			if (surface.isPressed(KeyEvent.VK_LEFT)) {
//				hero.walk(1);
//				hero.setFacingDirection(180);
//			}
//				
//			if (surface.isPressed(KeyEvent.VK_RIGHT)) {
//				hero.walk(-1);
//				hero.setFacingDirection(0);
//			}
//		}
		
		if (!flipped) {
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
			hero.dash(platforms, movingPlatforms, enemies);
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
					if (Math.abs(hero.getCenterX() - enemies.get(i).getCenterX()) < 75  && Math.abs(hero.getCenterY() - enemies.get(i).getCenterY()) < 75) {
						hero.punch(enemies.get(i));
					}
				}
			}
		}
		
		for (Checkpoint c: checkpoints) {
			c.act(hero);
		}
		
		if (hero.getHearts() > 0) {
			currTime = System.currentTimeMillis()-startTime;
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

			
			for(int i=0;i<movingPlatforms.size();i++) {
				MovingPlatform m=movingPlatforms.get(i);
				if (m!=null) {
					m.act();
				}
			}
			hero.act(platforms, enemies, tokens, spikes,movingPlatforms);
		}
		else {
			hero.moveToLocation(hero.getCheckpointX(), hero.getCheckpointY());
			hero.gainHearts(3);
			hero.setVelocityX(0);
			hero.setVelocityY(0);
//			surface.textFont(f);
//			surface.fill(255, 0, 0);
//			surface.text("YOU LOSE", view_x+400, view_y+250);
		}
		
		surface.textFont(f);
		surface.fill(0);
		surface.text((int)(currTime/1000)+" ", view_x+397, view_y+50);
		
		
	}
	
	/**
	 * Generates the platforms in the game screen
	 * @return an ArrayList of Shapes representing platforms
	 */
	private ArrayList<Shape> generatePlatforms(){
		ArrayList<Shape> p = new ArrayList<Shape>();
		p.add(new Rectangle(0,495,60000,500));	//bottom 
		
		p.add(new Rectangle(0,-300,120,1200)); 	// top left
		p.add(new Rectangle(300,345,200,50));	// top middle
		//Parkour1
		//250 is around max leap distance
		p.add(new Rectangle(600,345,50,50)); 
		p.add(new Rectangle(700,345,100,50));
		p.add(new Rectangle(1050,345,100,50));
		p.add(new Rectangle(1300,345,50,50));
		p.add(new Rectangle(1425,345,25,50));
		//Fire enemy
		p.add(new Rectangle(1700,345,250,50));
		p.add(new Rectangle(2050,345,250,50));		
		p.add(new Rectangle(1925,130,150,50));
		p.add(new Rectangle(2200,-300,50,585));
		p.add(new Rectangle(1750,-300,50,585));
		p.add(new Rectangle(2125, 235,75,50));
		p.add(new Rectangle(1800, 235,75,50));
		//Parkour2 
		p.add(new Rectangle(2450,345,200,50)); //2 Spikes in middle
		p.add(new Rectangle(2800,345,200,50)); //Spike in front, space, another spike
		p.add(new Rectangle(3100,345,100,50)); //Spike on end
		p.add(new Rectangle(3300,305,40,10)); //Spike on top
		p.add(new Rectangle(3300,385,50,30));
		//Water enemy
		p.add(new Rectangle(3500,345,250,50));
		p.add(new Rectangle(3850,345,250,50));		
		p.add(new Rectangle(3725,130,150,50));
		p.add(new Rectangle(4000,-300,50,585));
		p.add(new Rectangle(3550,-300,50,585));
		p.add(new Rectangle(3925,235,75,50));
		p.add(new Rectangle(3600, 235,75,50));
		//Parkour3
		//Moving Platforms
		//Grass Enemy
		p.add(new Rectangle(5300,345,250,50));
		p.add(new Rectangle(5650,345,250,50));		
		p.add(new Rectangle(5525,130,150,50));
		p.add(new Rectangle(5800,-300,50,585));
		p.add(new Rectangle(5350,-300,50,585));
		p.add(new Rectangle(5725, 235,75,50));
		p.add(new Rectangle(5400, 235,75,50));
		//Parkour4
		p.add(new Rectangle(6000,345,450,50));
		//Final Boss
		p.add(new Rectangle(7000,345,900,50));	
		p.add(new Rectangle(7050,-300,50,585));
		p.add(new Rectangle(7450,245,100,100));
		p.add(new Rectangle(7150,145,200,50));
		p.add(new Rectangle(7650,140,200,50));
		p.add(new Rectangle(7900,-300,1200,5000));
		
		return p;
	}
	
	
	/**
	 * Generates the checkpoints in the game screen
	 * @return an ArrayList of checkpoints
	 */
	private ArrayList<Checkpoint> generateCheckpoints() {
		ArrayList<Checkpoint> c = new ArrayList<Checkpoint>();
		c.add(new Checkpoint(surface.loadImage("sprites\\CheckpointSprite.png"), surface.loadImage("sprites\\CheckpointSpriteActivated.png"), 400, 270));
		c.add(new Checkpoint(surface.loadImage("sprites\\CheckpointSprite.png"), surface.loadImage("sprites\\CheckpointSpriteActivated.png"), 1704, 270));
		c.add(new Checkpoint(surface.loadImage("sprites\\CheckpointSprite.png"), surface.loadImage("sprites\\CheckpointSpriteActivated.png"), 3505, 270));
		c.add(new Checkpoint(surface.loadImage("sprites\\CheckpointSprite.png"), surface.loadImage("sprites\\CheckpointSpriteActivated.png"), 5304, 270));
		c.add(new Checkpoint(surface.loadImage("sprites\\CheckpointSprite.png"), surface.loadImage("sprites\\CheckpointSpriteActivated.png"), 7102, 270));

		return c;
	}
	
	/**
	 * Generates the enemies in the game screen
	 * @return an ArrayList of checkpoints
	 */
	private ArrayList<Enemy> generateEnemies() {
		ArrayList<Enemy> c = new ArrayList<Enemy>();
		c.add(new Enemy(surface.loadImage("sprites\\StandingEnemySprite.png"), 280, 50));
		c.add(new FireEnemy(surface.loadImage("sprites\\StandingFireEnemySprite.png"), 1976, 60));	// Fire Enemy
		c.add(new WaterEnemy(surface.loadImage("sprites\\StandingWaterEnemySprite.png"), 3778, 60));	// Water Enemy
		c.add(new GrassEnemy(surface.loadImage("sprites\\StandingGrassEnemySprite.png"), 5578, 60));	// Grass Enemy
		c.add(new Boss(surface.loadImage("sprites\\StandingBossSprite.png"),7484, 60));	// Boss
		return c;
	}
	
	/**
	 * Generates the tokens in the game screen
	 * @return an ArrayList of tokens
	 */
	private ArrayList<Token> generateTokens() {
		ArrayList<Token> t = new ArrayList<Token>();
//		System.out.println(fistToken);
//		System.out.println(view_x);
//		System.out.println(view_y);
		t.add(new Token(surface.loadImage("sprites\\tokens\\FistTokenSprite.png"), (int) (300), (int) 10));
		t.add(new Token(heart,1700,305));
		t.add(new Token(heart,3500,305));
		t.add(new Token(heart,5300,305));
		t.add(new Token(heart,7100,305));
		return t;
	}
	
	/**
	 * Generates the spikes in the game screen
	 * @return an ArrayList of spikes
	 */
	private ArrayList<Spike> generateSpikes() {
		ArrayList<Spike> s = new ArrayList<Spike>();
//		int x = 90;
		//Floor
		for (int i = 0; i < 194; i++) {
			s.add(new Spike(spike, 135 + (i*40), 465, 30, 30));
//			x+=30;
		}
		//Parkour2
		s.add(new Spike(spike,2510,305,40,40));
		s.add(new Spike(spike,2560,305,40,40));
		s.add(new Spike(spike,2800,305,40,40));
		s.add(new Spike(spike,2910,305,40,40));
		s.add(new Spike(spike,3160,305,40,40));
		s.add(new Spike(spike,3300,265,40,40));
		s.add(new Spike(spike,6070,305,40,40));
		s.add(new Spike(spike,6110,305,40,40));
		s.add(new Spike(spike,6150,305,40,40));
		s.add(new Spike(spike,6270,305,40,40));
		s.add(new Spike(spike,6310,305,40,40));
		s.add(new Spike(spike,6350,305,40,40));
		s.add(new Spike(spike,6560,305,40,40));
		s.add(new Spike(spike,6700,275,70,70));
		s.add(new Spike(spike,6870,305,40,40));
		return s;
		
	}
	
	/**
	 * Generates the moving platforms in the game screen
	 * @return an ArrayList of moving platforms
	 */
	private ArrayList<MovingPlatform> generateMovingPlatforms(){
		ArrayList<MovingPlatform> m=new ArrayList<MovingPlatform>();
		m.add(new MovingPlatform(movingPlatform, 4250,345,200,50, 4350,345,3,0)); 
		m.add(new MovingPlatform(movingPlatform, 4800,345,100,50, 4700,345,-3,0));
		m.add(new MovingPlatform(movingPlatform,4950,345,150,50,5050,345,3,0));
		m.add(new MovingPlatform(movingPlatform,6450,345,200,50,6710,345,3,0));
		return m;
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
		

		if(flipped) {
			surface.translate(view_x,-view_y);
		}
		else {
			surface.translate(-view_x,-view_y);
		}
	}
	
	/**
	 * 
	 * @return the Enemies in the game screen
	 */
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	/**
	 * 
	 * @return the Hero in the game screen
	 */
	public Hero getHero() {
		return hero;
	}

}

	
