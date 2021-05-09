

import java.awt.Color;

import processing.core.PApplet;

/**
 * The Shape class represents a Shape. It can perform common calculations
 * as well as drawing a representation of the shape to a Processing PApplet.
 *  
 * @author vicram_vijayakumar
 * @version 10/14
 */
public abstract class Shape {

	
	// FIELDS
	protected double x, y;
	private int strokeWidth;
	private Color strokeColor;
	private Color fillColor;
	private boolean filled;
	
	
	// CONSTRUCTORS
	/**
	 * Creates a new instance of a Shape object and sets the X and Y coordinates
	 * of the Shape. It sets the stroke color of the Shape to black, the fill color
	 * to white, and the stroke width to one.
	 * 
	 * @param x The x-Coordinate of the Shape.
	 * @param y The y-Coordinate of the Shape.
	**/
	public Shape(double x, double y) {
		strokeColor = Color.BLACK;
		fillColor = Color.WHITE;
		filled = true;
		strokeWidth = 1;
		this.x = x;
		this.y = y;
	}
	
	
	// METHODS
	
	/**
	 * Calculates and returns the area of the Shape.
	 * 
	 * @return The area of the Shape.
	**/
	public abstract double getArea();
	
	/**
	 * Calculates and returns the perimeter of the Shape.
	 * 
	 * @return The perimeter of the Shape.
	**/
	public abstract double getPerimeter();
	
	/**
	 * 
	 * @return The x-Coordinate of the Shape.
	**/
	public double getX() {
		return x;
	}
	
	/**
	 * 
	 * @return The y-Coordinate of the Shape.
	**/
	public double getY() {
		return y;
	}
	
	/**
	 * Sets the x-Coordinate of the Shape.
	 * 
	 * @param x The x-Coordinate of the Shape.
	**/
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Sets the y-Coordinate of the Shape.
	 * 
	 * @param y The y-Coordinate of the Shape.
	**/
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Translate the Shape horizontally and/or vertically.
	 *
	 * @param x The amount of units horizontally to be translated.
	 * @param y The amount of units vertically to be translated.
	**/
	public void translate(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	/**
	 * Moves the Shape to a new point.
	 *
	 * @param x The new x-Coordinate of the Shape.
	 * @param y The new y-Coordinate of the Shape.
	**/
	public void moveTo(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x-Coordinate of the center of the Shape.
	 *
	 * @return The x-Coordinate of the center of the Shape.
	**/
	public abstract double getCenterX();
	
	/**
	 * Gets the y-Coordinate of the center of the Shape.
	 *
	 * @return The y-Coordinate of the center of the Shape.
	**/
	public abstract double getCenterY();
	
	/**
	 * Draws a new instance of a Shape object with the appropriate stroke width and color.
	 * If the Shape is filled, it is filled with the fill color. If not, it is not filled.
	 * 
	 * @param marker The Processing PApplet on which to draw the Rectangle.
	 * @pre The Rectangle will be drawn with attributes previously set on the given PApplet.
	 * @post The Processing PApplet on which the Rectangle is drawn is changed.
	**/
	public void draw(PApplet marker) {
		if (filled) {
			marker.fill(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue());
		} else {
			marker.noFill();
		}
		marker.strokeWeight(strokeWidth);
		marker.stroke(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue());
	}
	
	/**
	 * Sets the stroke color of the Shape.
	 * 
	 * @param c The stroke color of the Shape.
	**/
	public void setStrokeColor(Color c) {
		this.strokeColor = c;
	}
	
	/**
	 * Sets the fill color of the Shape.
	 * 
	 * @param c The fill color of the Shape.
	**/
	public void setFillColor(Color c) {
		this.fillColor = c;
	}
	
	/**
	 * Sets the stroke width of the Shape.
	 * 
	 * @param strokeWidth The stroke width of the Shape.
	**/
	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	
	/**
	 * Set the Shape to be filled or not.
	 * 
	 * @param f true if the Shape is filled, false if not.
	**/
	public void setFilled(boolean f) {
		filled = f;
	}
	
	
	/**
	 * Determines whether the point (x, y) is contained inside the Shape.
	 * 
	 * @param x The X coordinate of the point which is being checked if it is inside the Shape.
	 * @param y The Y coordinate of the point which is being checked if it is inside the Shape.
	 * @return true if the point (x, y) is contained inside the Shape.
	**/
	public abstract boolean isPointInside(double x, double y);

	
}
