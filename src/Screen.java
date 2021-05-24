/**
 * The abstract class Screen represents a Screen
 * Outside Code Credit: ProcessingScreenSwitchingDemo
 * 
 * @author Mr. Shelby
 * @version 5.23.21
 */
public abstract class Screen {

	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * Creates the Screen with a specified width and height.
	 * @param width The width of the Screen
	 * @param height The height of the Screen
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * Sets up the Screen
	 */
	public void setup() {
		
	}
	
	/**
	 * Draws the Screen
	 */
	public void draw() {
		
	}
	
	/**
	 * Does various things if the mouse is pressed depending on the Screen
	 */
	public void mousePressed() {
		
	}
	
	/**
	 * Does various things if the mouse is moved depending on the Screen
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * Does various things if the mouse is dragged depending on the Screen
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * Does various things if the mouse is released depending on the Screen
	 */
	public void mouseReleased() {
		
	}
	
	
	
}
