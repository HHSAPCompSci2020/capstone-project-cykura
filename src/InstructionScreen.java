import java.awt.Rectangle;

/**
 * 
 * @author vicram_vijayakumar
 *
 */
public class InstructionScreen extends Screen {

	private DrawingSurface surface;

	
	public InstructionScreen(int width, int height) {
		super(width, height);
	}
	
	public InstructionScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

//		gameButton = new Rectangle(800/2-100,600/2-75,200,100);	// x, y, w, h
//		instructionsButton = new Rectangle(800/2-100,600/2-250,200,100);
	}

}
