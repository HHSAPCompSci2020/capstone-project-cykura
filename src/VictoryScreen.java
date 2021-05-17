import java.awt.Point;
import java.awt.Rectangle;

public class VictoryScreen extends Screen {

	private DrawingSurface surface;
	private Rectangle firstScreenButton;

	
//	public InstructionScreen(int width, int height) {
//		super(width, height);
//	}
	
	public VictoryScreen(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

//		gameButton = new Rectangle(800/2-100,600/2-75,200,100);	// x, y, w, h
//		instructionsButton = new Rectangle(800/2-100,600/2-250,200,100);
//		firstScreenButton = new Rectangle(800/2-100,600/2+225,200,50);	// x, y, w, h
	}
	
	public void draw() {
		surface.pushStyle();
		
		surface.background(255,255,255);
		surface.rect(10, 10, 780, 500);	// x = 10, y = 10, w = 780, h = 500
		surface.fill(0);
		surface.textSize(40);
		String str = "YOU WIN";
		float w = surface.textWidth(str);
		surface.text(str, 400 - w/2, 65);
		
		
		surface.textSize(30);
		surface.popStyle();

	}
	
	public void mousePressed() {		
	}

}
