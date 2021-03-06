
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import processing.core.PApplet;

/**
 * The Circle class represents a double precision Circle. It can perform common calculations
 * as well as drawing a representation of the circle to a Processing PApplet.
 *  
 * @author vicram_vijayakumar
 * @version 5.23.21
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
	public Circle(double x, double y, double extent) {
		strokeColor = Color.BLUE;
		fillColor = Color.BLUE;
		filled = true;
		strokeWidth = 1;
		this.x = x;
		this.y = y;		
		this.extent = extent;
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
		/*if (filled) {
			marker.fill(fillColor.getRed(), fillColor.getGreen(), fillColor.getBlue());
		} else {
			marker.noFill();
		}*/
		marker.noFill();
		marker.strokeWeight(strokeWidth);
		marker.stroke(strokeColor.getRed(), strokeColor.getGreen(), strokeColor.getBlue());
		marker.circle((float) x, (float) y, (float) extent);
		//System.out.println(extent);
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
	
	/**
	 * Checks if a Rectangle2D object intersects with the Circle.
	 * 
	 * @param img The Rectangle2D object to be checked.
	 * @return true if the Rectangle2D object intersects with the Circle. Otherwise, it returns false.
	 */
	protected boolean intersects(Rectangle2D img) {
		double radius = extent/2;
		Point p = new Point((int)x,(int)y);	// Center of Circle Point
		if(img.contains(x, y)) {	// If the center of the circle is in the rectangle;
			return true;
		}
		double d1 = p.distance(new Point((int)img.getX(),(int)img.getY()));	// Distance from center of circle to top left corner of img
		if(d1<=radius)return true;
		double d2 = p.distance(new Point((int)(img.getX()+img.getWidth()),(int)img.getY()));	// Distance from center of circle to top right corner of img
		if(d2<=radius)return true;
		double d3 = p.distance(new Point((int)(img.getX()+img.getWidth()),(int)(img.getY()+img.getHeight()))); // Distance from center of circle to bottom right corner of img
		if(d3<=radius)return true;
		double d4 = p.distance(new Point((int)img.getX(),(int)(img.getY()+img.getHeight())));	// Distance from center of circle to bottom left corner of img
		if(d4<=radius)return true;
		return false;	// Otherwise returns false (Not one of the corners of the rectangle are in the circle)
	}
	
}
