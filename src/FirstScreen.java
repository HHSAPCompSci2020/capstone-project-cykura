


import java.awt.Point;
import java.awt.Rectangle;

/**
 * The FirstScreen class represents an Option Menu.
 * @author Mr. Shelby
 * @version 5.23.21
 *
 */
public class FirstScreen extends Screen {

	private DrawingSurface surface;
	
	private Rectangle gameButton;
	private Rectangle instructionsButton;
	private Rectangle exitButton;
	
	/**
	 * Creates a firstScreen with buttons.
	 * 
	 * @param surface The drawing Surface on which to draw the screen.
	 */
	public FirstScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		gameButton = new Rectangle(800/2-100,600/2-75,200,100);	// x, y, w, h
		instructionsButton = new Rectangle(800/2-100,600/2-250,200,100);
		exitButton = new Rectangle(800/2-100,600/2+100,200,100);
	}

	
	/**
	 * Draws the screen with buttons
	 */
	public void draw() {

		surface.pushStyle();
		
		surface.background(255,255,255);
		
		// Game Button
		surface.rect(gameButton.x, gameButton.y, gameButton.width, gameButton.height, 10);
		surface.fill(0);
		String str = "Cykura";
		float w = surface.textWidth(str);
		surface.text(str, gameButton.x+gameButton.width/2-w/2, gameButton.y+gameButton.height/2);
			
	
		// Instructions Button
		surface.fill(255);
		surface.rect(instructionsButton.x, instructionsButton.y, instructionsButton.width, instructionsButton.height, 10);
		surface.fill(0);
		String str1 = "Instructions";
		float w1 = surface.textWidth(str1);
		surface.text(str1, instructionsButton.x+instructionsButton.width/2-w1/2, instructionsButton.y+instructionsButton.height/2);
		
		
		// Quit Button
		surface.fill(255);
//		surface.rec
		surface.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height, 10);
		surface.fill(0);
		String str2 = "Quit";
		float w2 = surface.textWidth(str2);
		surface.text(str2, exitButton.x+exitButton.width/2-w2/2, exitButton.y+exitButton.height/2);
		
		
		
		surface.popStyle();
	}



	/**
	 * Switches the screen depending on where the mouse was pressed.
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (gameButton.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN2);
		
		if(instructionsButton.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN3);
		
		if(exitButton.contains(p)) {
			System.exit(0);
		}
		
	}
	

}

