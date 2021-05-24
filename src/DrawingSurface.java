

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * The DrawingSurface class represents the surface on which the Active Screen is drawn.

 * 
 * @author Mr. Shelby
 * @version 5.23.21
 */
public class DrawingSurface extends PApplet implements ScreenSwitcher {

	public float ratioX, ratioY;
	
	private ArrayList<Integer> keys;
//	private ArrayList<Integer> keysReleased;
	
//	private ArrayList<Integer> keysTapped;
	
	private Screen activeScreen;
	private ArrayList<Screen> screens;

	/**
	 * Creates a Drawing Surface and sets the Active Screen to the First Screen.
	 * 
	 */
	public DrawingSurface() {
		screens = new ArrayList<Screen>();
		
		keys = new ArrayList<Integer>();
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
	
	/**
	 * Sets the size of the Drawing Surface to the size of the Active Screen.
	 */
	public void settings() {
		// size(DRAWING_WIDTH, DRAWING_HEIGHT, P2D);
		size(activeScreen.DRAWING_WIDTH, activeScreen.DRAWING_HEIGHT);
	}
	
	/**
	 * Sets the drawing surface resizable.
	 * 
	 */
	public void setup() {
		surface.setResizable(true);
		for (Screen s : screens)
			s.setup();
	}
	
	/**
	 * Draws the Active Screen and scales it.
	 */
	public void draw() {
		pushMatrix();
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
	}
	
	/**
	 * Adds the key pressed to the list of keys.
	 */
	public void keyPressed() {
		keys.add(keyCode);
		
	}
	
	/**
	 * Removes the key released from the list of keys.
	 */
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
		
	}
	
	
	/**
	 * Checks if the inputted key is pressed.
	 * 
	 * @param code The Integer value of the key to check if it is being pressed.
	 * @return true if the Key is pressed.
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	/**
	 * Does various things if the mouse is pressed depending on the active screen.
	 */
	public void mousePressed() {
		activeScreen.mousePressed();
	}
	
	/**
	 * Does various things if the mouse is moved depending on the active screen.
	 */
	public void mouseMoved() {
		activeScreen.mouseMoved();
	}
	
	/**
	 * Does various things if the mouse is dragged depending on the active screen.
	 */
	public void mouseDragged() {
		activeScreen.mouseDragged();
	}
	
	/**
	 * Does various things if the mouse is released depending on the active screen.
	 */
	public void mouseReleased() {
		activeScreen.mouseReleased();
	}
	
	/**
	 * Returns the actual point coordinate given the assumed point coordinate.
	 * @param assumed The assumed Point coordinate.
	 * @return the Actual point coordinate.
	 */
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}
	
	/**
	 * Returns the assumed coordinate given the actual coordinate.
	 * @param actual The Actual Point coordinate.
	 * @return the assumed Point coordinate
	 */
	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}

	@Override
	/**
	 * Switches the screen
	 * 
	 * @param i The integer value of the screen to switch to.
	 */
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}
	
	/**
	 * 
	 * @return true if the Active Screen is Game Screen.
	 */
	public boolean gameScreenActive() {
		return activeScreen instanceof GameScreen;
	}
	
	/**
	 *
	 * @return The Active Screen
	 */
	public Screen getActiveScreen() {
		return activeScreen;
	}
}
