import processing.core.PImage;

/**
 * The Boss class represents the final enemy which the Hero can defeat with all of the same abilities as the Hero.
 * 
 * @version 5.6.21
 */
public class Boss extends Enemy {
	private int invertTime;
	/**
	 * Creates a new instance of a Boss object having its left
	 * corner at the inputed (x, y) coordinates.
	 * 
	 * @param img The PImage which the Boss will look like in the game (sprite).
	 * @param x The X value of the Boss' top left corner.
	 * @param y The Y value of the Boss' top left corner.
	**/
	public Boss(PImage img, int x, int y) {
		super(img, x, y);
	}
	
	public void act() {
		if(invertTime>0) {
			invertTime--;
		}
		else {
			invertTime = 0;
			GameScreen.invertControls = false;
		}
	}

	private void invertControls(int duration) {
		GameScreen.invertControls = true;
		invertTime = duration;
	}
}
