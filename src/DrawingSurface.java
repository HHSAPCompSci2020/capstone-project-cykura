import processing.core.PApplet;

public class DrawingSurface extends PApplet{
	public static final int DRAWING_WIDTH = 800;
	public static final int DRAWING_HEIGHT = 600;
	private World world;
	
	public DrawingSurface() {
		super();
		world = new World();
	}
	
	public void draw() {
		world.draw(this);
	}
}
