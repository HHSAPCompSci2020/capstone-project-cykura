import java.awt.Rectangle;

/**
 * 
 * @author vicram_vijayakumar
 *
 */
public class InstructionScreen extends Screen {

	private DrawingSurface surface;

	
//	public InstructionScreen(int width, int height) {
//		super(width, height);
//	}
	
	public InstructionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

//		gameButton = new Rectangle(800/2-100,600/2-75,200,100);	// x, y, w, h
//		instructionsButton = new Rectangle(800/2-100,600/2-250,200,100);
	}
	
	public void draw() {
		surface.pushStyle();
		
		surface.background(255,255,255);
		surface.rect(10, 10, 780, 500);
		surface.fill(0);
		String str = "Instruction";
		float w = surface.textWidth(str);
		surface.text(str, 10, 10);

		
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
