import java.awt.Rectangle;

/**
 * 
 * @author vicram_vijayakumar
 *
 */
public class InstructionScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle firstScreenButton;

	
//	public InstructionScreen(int width, int height) {
//		super(width, height);
//	}
	
	public InstructionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

//		gameButton = new Rectangle(800/2-100,600/2-75,200,100);	// x, y, w, h
//		instructionsButton = new Rectangle(800/2-100,600/2-250,200,100);
		firstScreenButton = new Rectangle(800/2-100,600/2+225,200,50);	// x, y, w, h
	}
	
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
				"S - Use Waterfall\n" + 
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

		
//		surface.rect(gameButton.x, gameButton.y, gameButton.width, gameButton.height, 10, 10, 10, 10);
//		surface.fill(0);
//		String str = "Cykura";
//		float w = surface.textWidth(str);
//		surface.text(str, gameButton.x+gameButton.width/2-w/2, gameButton.y+gameButton.height/2);
//		
//		surface.fill(255);
//		surface.rect(instructionsButton.x, instructionsButton.y, instructionsButton.width, instructionsButton.height, 10, 10, 10, 10);
//		surface.fill(0);
//		String str1 = "Instructions";
//		float w1 = surface.textWidth(str1);
//		surface.text(str1, instructionsButton.x+instructionsButton.width/2-w1/2, instructionsButton.y+instructionsButton.height/2);
		
		
		surface.popStyle();

	}

}
