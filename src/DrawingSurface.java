

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * 
 * @author Mr. Shelby
 *
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
	private ArrayList<Integer> keysTyped;
//	private ArrayList<Integer> keysReleased;
	
//	private ArrayList<Integer> keysTapped;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;

	
	public DrawingSurface() {
		
		
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
		keysTyped = new ArrayList<Integer>();
//		keysReleased = new ArrayList<Integer>();

//		keysTapped = new ArrayList<Integer>();
		
		
		FirstScreen screen1 = new FirstScreen(this);
		screens.add(screen1);
		
		GameScreen screen2 = new GameScreen(this);
		screens.add(screen2);
		
		InstructionScreen screen3 = new InstructionScreen(this);
		screens.add(screen3);
		
		VictoryScreen screen4 = new VictoryScreen(this);
		screens.add(screen4);
		
		activeScreen = screens.get(0);
		
	}
	
	public void settings() {
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	public void draw() {
		pushMatrix();
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	public void keyPressed() {
		keys.add(keyCode);
		
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
		
	}
	
	public void keyTyped() {
		keysTyped.add(keyCode);
	}
	
	public boolean isTyped(Integer code) {
		return keysTyped.contains(code);
	}
	

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
	public boolean gameScreenActive() {
		return activeScreen instanceof GameScreen;
	}

}
