import java.awt.Point;
import java.awt.Rectangle;

/**
 * The InstructionScreen class represents the Screen which displays the instructions and the keyboard layout.
 * @author vicram_vijayakumar
 * @version 5.23.21
 *
 */
public class InstructionScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle firstScreenButton;

	
//	public InstructionScreen(int width, int height) {
//		super(width, height);
//	}
	
	/**
	 * Creates the instruction screen with button.
	 * @param surface The Drawing Surface to draw the Instruction Screen on
	 */
	public InstructionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

//		gameButton = new Rectangle(800/2-100,600/2-75,200,100);	// x, y, w, h
//		instructionsButton = new Rectangle(800/2-100,600/2-250,200,100);
		firstScreenButton = new Rectangle(800/2-100,600/2+225,200,50);	// x, y, w, h
	}
	
	/**
	 * Draws the Instruction Screen with a button for heading back to the options menu.
	 */
	public void draw() {
		surface.pushStyle();
		
		surface.background(255,255,255);
		surface.rect(10, 10, 780, 500);	// x = 10, y = 10, w = 780, h = 500
		surface.fill(0);
		surface.textSize(40);
		String str = "Game Instructions: \nLeft arrow key - Move to the left\n" + 
				"Right arrow key - Move to the right\n" + 
				"Up arrow key - Jump\n" + 
				"Space Bar - Punch\n" + 
				"A - Use Fireball\n" + 
				"S - Use Water Wave\n" + 
				"D - Use Leaf Dash (Hold to charge)\n" + 
				"";
		float w = surface.textWidth(str);
		surface.text(str, 400 - w/2, 65);
		
		
		surface.textSize(30);
		surface.fill(255);
		surface.rect(firstScreenButton.x, firstScreenButton.y, firstScreenButton.width, firstScreenButton.height, 10, 10, 10, 10);
		surface.fill(0);
		String str1 = "Option Menu";
		float w1 = surface.textWidth(str1);
		surface.text(str1, firstScreenButton.x+firstScreenButton.width/2-w1/2, (firstScreenButton.y+firstScreenButton.height/2) +10);		
		
		surface.popStyle();

	}
	
	/**
	 * Switches the screen depending on where the mouse is pressed.
	 */
	public void mousePressed() {
		Point p = surface.actualCoordinatesToAssumed(new Point(surface.mouseX,surface.mouseY));
		if (firstScreenButton.contains(p))
			surface.switchScreen(ScreenSwitcher.SCREEN1);
				
	}

}
