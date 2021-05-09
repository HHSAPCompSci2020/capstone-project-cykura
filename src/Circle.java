
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;

/**
 * The Circle class represents a double precision Circle. It can perform common calculations
 * as well as drawing a representation of the circle to a Processing PApplet.
 *  
 * @author vicram_vijayakumar
 * @version 11/1
 */
public class Circle {
	protected double extent;
	protected double x, y;
	private int strokeWidth;
	private Color strokeColor;
	private Color fillColor;
	private boolean filled;

	/**
	 * Creates a default instance of a Circle object with all dimensions
	 * set to zero.
	**/
	public Circle() {
		strokeColor = Color.BLUE;
		fillColor = Color.BLUE;
		filled = true;
		strokeWidth = 1;
		x = 0;
		y = 0;
		
		extent = 0;
	}

	/**
	 * Creates a new instance of a Circle object having a diameter equal to
	 * the inputed extent with the center of the Circle at (x, y).
	 * 
	 * @param x X value of the Circle's center point.
	 * @param y Y value of the Circle's center point.
	 * @param extent The diameter of the Circle.
	**/
	public Circle(double x, double y, double radius) {
		strokeColor = Color.BLUE;
		fillColor = Color.BLUE;
		filled = true;
		strokeWidth = 1;
		this.x = 0;
		this.y = 0;		
		this.extent = radius;
	}

	/**
	 * Calculates and returns the perimeter of the Circle.
	 * 
	 * @return The perimeter of the Circle.
	**/
	public double getPerimeter() {
		double perimeter = Math.PI*extent;
		return perimeter;
	}

	/**
	 * Calculates and returns the area of the Circle.
	 * 
	 * @return The area of the Circle.
	**/
	public double getArea() {
		double area = Math.PI*(Math.pow((extent/2), 2));
		return area;
	}

	/**
	 * Determines whether the point (x, y) is contained inside the Circle.
	 * 
	 * @param x The X coordinate of the point which is being checked if it is inside the Circle.
	 * @param y The Y coordinate of the point which is being checked if it is inside the Circle.
	 * @return true if the point (x, y) is contained inside the Circle.
	**/
	public boolean isPointInside(double x, double y) {
		if (getLeftPointX() < x && x < getRightPointX() && (this.y - (extent/2)) < y && y < (this.y + (extent/2)))
			return true;
		else
			return false;
	}

	/**
	 * 
	 * Draws a new instance of a Circle object with
	 * the center at getX() and getY().
	 * 
	 * @param marker The Processing PApplet on which to draw the Circle.
	 * @pre The Circle will be drawn with attributes previously set on the given PApplet.
	 * @post The Processing PApplet on which the Circle is drawn is changed.
	**/
	public void draw(PApplet marker) {
		if (filled) {
			marker.fill(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue());
		} else {
			marker.noFill();
		}
		marker.strokeWeight(strokeWidth);
		marker.stroke(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue());
		marker.circle((float) x, (float) y, (float) extent);
	}
	
	/**
	 * Gets the X coordinate of the left most point of the Circle.
	 * 
	 * @return The X coordinate of the left most point of the Circle is the point is not null.
	**/
	public double getLeftPointX() {
		double leftX = x - (extent/2);
		
		if (!(Double.isFinite(leftX)))
			return (Double) null;
		
		return leftX;
	}
	
	/**
	 * Gets the X coordinate of the right most point of the Circle.
	 * 
	 * @return The right most point of the Circle is the point is not null.
	**/
	public double getRightPointX() {
		double rightX = x + (extent/2);
		
		if (!(Double.isFinite(rightX)))
			return (Double) null;
		
		return rightX;
	}
	
	/**
	 * Sets the diameter of the Circle to it's current diameter combined with the inputed value.
	 * 
	 * @param value The value to be added to the diameter of the Circle.
	**/
	public void addToDiameter(int value) {
		this.extent = this.extent + value;
	}
	
	/**
	 * Gets the diameter of the Circle.
	 * 
	 * @return The diameter of the Circle.
	**/
	public double getDiameter() {
		return this.extent;
	}
	
	/**
	 * Sets the diameter of the Circle.
	 *
	 * @param extent The new value for the diameter of the Circle.
	**/
	public void setDiameter(double extent) {
		this.extent = extent;
	}

	/**
	 * Gets the x-Coordinate of the center of the Circle.
	 * 
	 * @return The x-Coordinate of the center of the Circle.
	**/
	public double getCenterX() {
		return this.x;
	}


	/**
	 * Gets the y-Coordinate of the center of the Circle.
	 * 
	 * @return The y-Coordinate of the center of the Circle.
	**/
	public double getCenterY() {
		return this.y;
	}
	
	protected boolean intersects(Rectangle2D img) {
		return filled;
	}
	
}
